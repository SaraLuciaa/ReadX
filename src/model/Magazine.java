package model;
import java.util.Calendar;

public class Magazine extends BibliographicProduct {
    private Category category; 
    private String periodicityEmission;
    private int activeSubscriptions;

    public Magazine(String id, String name, String url, int pages, Calendar publication, double value, int pagesRead,
            Category category, String periodicityEmission, int activeSubscriptions) {
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
}