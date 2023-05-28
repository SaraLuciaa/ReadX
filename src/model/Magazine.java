package model;
import java.util.Calendar;

public class Magazine extends BibliographicProduct {
    private Category category; 
    private String periodicityEmission;
    private int activeSubscriptions;

    /**
     * Constructs a Magazine object with the specified parameters.
     *
     * <br>pre:<br>
     * The id, name, url, pages, publication, value, and pagesRead parameters must not be null.
     * The category parameter must be a valid index corresponding to a Category value.
     * The activeSubscriptions parameter must be a non-negative integer.
     *
     * <br>post:<br>
     * A new Magazine object is created with the given parameters.
     *
     * @param id                  the ID of the magazine
     * @param name                the name of the magazine
     * @param url                 the URL of the magazine
     * @param pages               the total number of pages in the magazine
     * @param publication         the publication date of the magazine
     * @param value               the value of the magazine
     * @param pagesRead           the number of pages already read in the magazine
     * @param category            the category of the magazine
     * @param periodicityEmission the periodicity of the magazine's emission
     * @param activeSubscriptions the number of active subscriptions for the magazine
     */
    public Magazine(String id, String name, String url, int pages, Calendar publication, double value, int pagesRead, int category, String periodicityEmission, int activeSubscriptions) {
        super(id, name, url, pages, publication, value, pagesRead);
        this.category = Category.values()[category-1];
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

    /**
     * Returns a string representation of the Magazine object.
     *
     * <br>pre:<br> None.
     * <br>post:<br> None.
     *
     * @return a string representation of the Magazine object.
     */
    @Override
    public String toString(){
        return super.toString() + "\nCategory: " + category + "\nPeriodicity of emission: " + periodicityEmission + "\nNumber of active subscriptions: " + activeSubscriptions;
    }

    /**
     * Updates the properties of the Magazine object.
     *
     * <br>pre:<br> None.
     * <br>post:<br> The Magazine object is updated with the provided values.
     *
     * @param name                 the name of the magazine
     * @param url                  the URL of the magazine
     * @param pages                the number of pages in the magazine
     * @param publication          the publication date of the magazine
     * @param value                the value of the magazine
     * @param pagesRead            the number of pages read in the magazine
     * @param category             the category of the magazine (-1 if unchanged)
     * @param periodicityEmission  the periodicity of emission of the magazine ("-1" or empty string if unchanged)
     * @param activeSubscriptions  the number of active subscriptions (-1 if unchanged)
     */
    public void updateBP(String name, String url, int pages, Calendar publication, double value, int pagesRead, int category, String periodicityEmission, int activeSubscriptions){
        super.updateBP(name, url, pages, publication, value, pagesRead);
        this.category = category==-1?this.category:Category.values()[category-1];
        this.periodicityEmission = periodicityEmission.equals("-1")|periodicityEmission.equals("")?this.periodicityEmission:periodicityEmission;
        this.activeSubscriptions = activeSubscriptions==-1?this.activeSubscriptions:activeSubscriptions;
    }

    /**
     * Increases the number of active subscriptions for the Magazine object.
     *
     * <br>pre:<br> None.
     * <br>post:<br> The number of active subscriptions is incremented by 1.
     */
    @Override
    public void sellBP() {
        activeSubscriptions++;    
    }
}