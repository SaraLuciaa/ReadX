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

    // -------- Automatically generate objects --------
    public String generateObjects(){
        // Bibliographic Products
        Calendar publication1 = Calendar.getInstance();
        publication1.set(2020, Calendar.AUGUST, 15);
        Calendar publication2 = Calendar.getInstance();
        publication2.set(2005, Calendar.NOVEMBER, 11);
        Calendar publication3 = Calendar.getInstance();
        publication3.set(2018, Calendar.MARCH, 22);

        Book book1 = new Book("2A9","The Great Gatsby", "https://example.com/the-great-gatsby", 300, publication1, 19.99, 200, "A captivating story set in the 1920s.", 1000, 3);
        products.add(book1);        
        Book book2 = new Book("B0E", "1984", "https://example.com/1984", 350, publication2, 14.99, 150, "A dystopian masterpiece.", 2000, 1);
        products.add(book2);
        Book book3 = new Book("7C1", "Pride and Prejudice", "https://example.com/pride-and-prejudice", 250, publication3, 9.99, 200, "A classic love story with witty dialogue.", 3000, 2);
        products.add(book3);

        Magazine magazine1 = new Magazine("M01", "Entertainment Weekly", "https://www.entertainmentweekly.com", 70, publication1, 8.99, 50, 1, "Weekly", 2000);
        products.add(magazine1);
        Magazine magazine2 = new Magazine("M02", "Architectural Digest", "https://www.architecturaldigest.com", 90, publication2, 10.99, 20, 2, "Monthly", 800);
        products.add(magazine2);
        Magazine magazine3 = new Magazine("M03", "Science Today", "https://www.sciencetoday.com", 60, publication3, 7.99, 40, 3, "Bi-monthly", 500);
        products.add(magazine3);

        // Users
        User user1 = new Regular("Alice", "123001");
        users.add(user1);
        User user2 = new Regular("Bob", "456002");
        users.add(user2);
        User user3 = new Premium("Carol", "789003");
        users.add(user3);
        User user4 = new Premium("David", "258004");
        users.add(user4);

        return toString();
    }

    // ------------ Bibliographic Products ------------
    public BibliographicProduct searchBP(String id){
        BibliographicProduct product = null;
        boolean search = true;
        for(int i=0; i<products.size()&&search; i++){
            if(products.get(i).getId().equalsIgnoreCase(id)){
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
            Magazine newM = new Magazine(id, name, url, pages, publication, value, pagesRead, category, periodicityEmission, activeSubscriptions);
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
            if(users.get(i).getId().equalsIgnoreCase(id)){
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
        User user = searchUser(idUser);
        String message = "";
        boolean buy = true;
        if(user instanceof Regular){
            Regular userR = (Regular) user;
            buy = userR.canBuy(type);
        }
        BibliographicProduct bp = searchBP(idBP);
        if(buy&&bp!=null&&((type==1&&bp instanceof Book)||(type==2&&bp instanceof Magazine))){
            bp.sellBP();
            message = user.buyBP(bp.getValue(), bp);
        } else if (!buy) {
            message = type==1?"Maximum number of books purchased":"Maximum number of active subscriptions";
        } else {
            message = type==1?"Book not found":"Magazine Product not found";
        }
        return message;
    }
}