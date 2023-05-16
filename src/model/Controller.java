package model;
import java.util.ArrayList;
import java.util.Calendar;

public class Controller {
    private ArrayList<BibliographicProduct> products;
    private ArrayList<User> users;
    private ArrayList<Payment> payments;

    public Controller(){
        this.products = new ArrayList<BibliographicProduct>();
        this.users = new ArrayList<User>();
        this.payments = new ArrayList<Payment>();
    }

    // ------------ Bibliographic Products ------------
    public BibliographicProduct searchBP(String id){
        BibliographicProduct product = null;
        boolean search = true;
        for(int i=0; i<products.size()&&search; i++){
            if(products.get(i).getName().equalsIgnoreCase(id)){
                product = products.get(i);
                search = false;
            }
        }
        return product;
    }

    public String checkBP(String id){
        String message = "";
        BibliographicProduct product = searchBP(id);
        if(product!=null){
            if(product instanceof Book){
                message = "Book";
            } else if(product instanceof Magazine){
                message = "Magazine";
            }
        } else {
            message = "Non-existent bibliographic product.";
        }
        return message;
    }

    public String createBP(String id, String name, String url, int pages, Calendar publication, double value, int pagesRead,String review, int copiesSold, int genre){
        String message = "";
        if(searchBP(id)==null){
            Book newB = new Book(id, name, url, pages, publication, value, pagesRead, review, copiesSold, genre);
            products.add(newB);
            message = "\n----Book created successfully----\n" + newB.toString() + "\n---------------------------------";
        }
        else {
            message = "There is already a bibliographic product with the entered ID.";
        }
        return message;
    }

    public String createBP(String id, String name, String url, int pages, Calendar publication, double value, int pagesRead, int category, String periodicityEmission, int activeSubscriptions){
        String message = "";
        if(searchBP(id)==null){
            Magazine newM = new Magazine(id, name, url, pages, publication, value, pagesRead, Category.values()[category-1], periodicityEmission, activeSubscriptions);
            products.add(newM);
            message = "\n----Magazine created successfully----\n" + newM.toString() + "\n---------------------------------";
        }
        else {
            message = "There is already a bibliographic product with the entered ID.";
        }
        return message;
    }

    public String updateBP(String id, String name, String url, int pages, Calendar publication, double value, int pagesRead,String review, int copiesSold, int genre){
        Book product = (Book) searchBP(id);
        String message = "-----Initial data-----\n" + product.toString() + "\n------------------------\n";
        product.updateBook(name, url, pages, publication, value, pagesRead, review, copiesSold, genre);
        message += "-----Updated data-----" + product.toString() + "\n------------------------\n";
        return message;
    }

    public String updateBP(String id, String name, String url, int pages, Calendar publication, double value, int pagesRead, int category, String periodicityEmission, int activeSubscriptions){
        Magazine product = (Magazine) searchBP(id);
        String message = "-----Initial data-----\n" + product.toString() + "\n------------------------\n";
        product.updateMagazine(name, url, pages, publication, value, pagesRead, category, periodicityEmission, activeSubscriptions);
        message += "-----Updated data-----" + product.toString() + "\n------------------------\n";
        return message;
    }

    public String removeBP(String id){
        String message = "";
        if(searchBP(id)!=null){
            products.remove(searchBP(id));
            message = "---Bibliographic product removed successfully---";
        }
        else {
            message = "Non-existent bibliographic product.";
        }
        return message;
    }

    public String toString(){
        String message = "";
        for(int i=0; i<products.size(); i++){
            message += products.get(i).toString() + "\n---------------------------------\n";
        }
        for(int i=0; i<users.size(); i++){
            message += users.get(i).toString() + "\n---------------------------------\n";
        }
        return message;
    }

    public String showGenre(int i){
        return Genre.values()[i].toString();
    }

    public String showCategory(int i){
        return Category.values()[i].toString();
    }

    // --------------------- Users ---------------------
    public User searchUser(String id){
        User user = null;
        boolean search = true;
        for(int i=0; i<users.size()&&search; i++){
            if(users.get(i).getName().equalsIgnoreCase(id)){
                user = users.get(i);
                search = false;
            }
        }
        return user;
    }

    public String createUser(int type, String name, String id){
        String message = "";
        User newU = searchUser(id);
        if(newU==null){
            if(type==1){
                newU = new Regular(name, id);
            } else {
                newU = new Premium(name, id);
            }
            users.add(newU);
            message = "----User created successfully----\n" + newU.toString() + "\n---------------------------------";
        }
        else {
            message = "There is already a user with the entered ID.";
        }
        return message;
    }

    // ---Purchase of books or magazine subscriptions---
    public String buyBP(int type, String idUser, String idBP){
        User user = searchUser(null);
        String message = "";
        boolean buy = true;
        if(user instanceof Regular){
            Regular userR = (Regular) user;
            buy = userR.canBuy(type);
        } else {
            message = type==1?"Maximum number of books purchased":"Maximum number of active subscriptions";
        }
        BibliographicProduct bp = searchBP(idBP);
        if(buy&&bp!=null&&((type==1&&bp instanceof Book)||(type==2&&bp instanceof Magazine))){
            bp.sellBP();
            user.buyBP(bp.getValue(), bp);
        } else {
            message = type==1?"Book not found":"Magazine Product not found";
        }
        return message;
    }
}