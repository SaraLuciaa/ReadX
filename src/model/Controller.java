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

    public String createBP(String id, String name, String url, int pages, Calendar publication, double value, int pagesRead,String review, int copiesSold, int genre){
        String message = "";
        if(searchBP(id)==null){
            Book newB = new Book(id, name, url, pages, publication, value, pagesRead, review, copiesSold, Genre.values()[genre-1]);
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
