package model;
import java.util.Calendar;

public class Magazine extends BibliographicProduct {
    private Category category; 
    private String periodicityEmission;
    private int activeSubscriptions;

    public Magazine(String id, String name, String url, int pages, Calendar publication, double value, int pagesRead, Category category, String periodicityEmission, int activeSubscriptions) {
        super(id, name, url, pages, publication, value, pagesRead);
        this.category = category;
        this.periodicityEmission = periodicityEmission;
        this.activeSubscriptions = activeSubscriptions;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public String getPeriodicityEmission() {
        return periodicityEmission;
    }
    public void setPeriodicityEmission(String periodicityEmission) {
        this.periodicityEmission = periodicityEmission;
    }
    public int getActiveSubscriptions() {
        return activeSubscriptions;
    }
    public void setActiveSubscriptions(int activeSubscriptions) {
        this.activeSubscriptions = activeSubscriptions;
    }

    @Override
    public String toString(){
        return super.toString() + "\nCategory: " + category + "\nPeriodicity of emission: " + periodicityEmission + "\nNumber of active subscriptions: " + activeSubscriptions;
    }

    public void updateMagazine(String name, String url, int pages, Calendar publication, double value, int pagesRead, int category, String periodicityEmission, int activeSubscriptions){
        super.updateBP(name, url, pages, publication, value, pagesRead);
        this.category = category==-1?this.category:Category.values()[category-1];
        this.periodicityEmission = periodicityEmission.equals("-1")|periodicityEmission.equals("")?this.periodicityEmission:periodicityEmission;
        this.activeSubscriptions = activeSubscriptions==-1?this.activeSubscriptions:activeSubscriptions;
    }

    @Override
    public void sellBP() {
        activeSubscriptions++;    
    }
}