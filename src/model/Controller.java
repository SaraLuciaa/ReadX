package model;
import java.util.ArrayList;
import java.util.Calendar;

public class Controller {
    private ArrayList<BibliographicProduct> products;

    public Controller(){
        this.products = new ArrayList<BibliographicProduct>();
    }

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
        return message;
    }

    public String showGenre(int i){
        return Genre.values()[i].toString();
    }

    public String showCategory(int i){
        return Category.values()[i].toString();
    }
}
