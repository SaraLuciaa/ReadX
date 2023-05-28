package model;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class BibliographicProduct{
    private String id;
    private String name;
    private String url;
    private int pages;
    private Calendar publication;
    private double value;
    private int pagesRead;

    /**
     * Constructs a BibliographicProduct object with the given parameters.
     *
     * <br>pre:<br> None.
     * <br>post:<br> A BibliographicProduct object is created with the specified parameters.
     *
     * @param id          the ID of the product
     * @param name        the name of the product
     * @param url         the URL of the product
     * @param pages       the total number of pages
     * @param publication the publication date
     * @param value       the value of the product
     * @param pagesRead   the number of pages already read
     */
    public BibliographicProduct(String id, String name, String url,int pages, Calendar publication, double value, int pagesRead) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.pages = pages;
        this.publication = publication;
        this.value = value;
        this.pagesRead = pagesRead;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public int getPages() {
        return pages;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }
    public Calendar getPublication() {
        return publication;
    }
    public void setPublication(Calendar publication) {
        this.publication = publication;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    public int getPagesRead() {
        return pagesRead;
    }
    public void setPagesRead(int pagesRead) {
        this.pagesRead = pagesRead;
    }

    /**
     * Returns a string representation of the BibliographicProduct object.
     *
     * <br>pre:<br> None.
     * <br>post:<br> None.
     * 
     * @return a string representation of the object
     */
    public String toString() {
        SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MM-yyyy");
        return "Id: " + id + "\nName: " + name + "\nUrl: " + url + "\nPages: " + pages + "\nDate of publication: " + timeStamp.format(publication.getTime()) + "\nValue: " + value + "\nNumber of pages read: " + pagesRead;
    }

    /**
     * Updates the attributes of the BibliographicProduct object.
     *
     * <br>pre:<br> None.
     * <br>post:<br> None.
     * 
     * @param name       the new name of the product
     * @param url        the new URL of the product
     * @param pages      the new total number of pages
     * @param publication the new publication date
     * @param value      the new value of the product
     * @param pagesRead  the new number of pages already read
     */
    public void updateBP(String name, String url,int pages, Calendar publication, double value, int pagesRead){
        this.name = name.equals("-1")|name.equals("")?this.name:name;
        this.url = id.equals("-1")|url.equals("")?this.url:url;
        this.pages = pages==-1?this.pages:pages;
        this.publication = publication;
        this.value = value==-1?this.value:value;
        this.pagesRead = pagesRead==-1?this.pagesRead:pagesRead;
    }

    /**
     * Simulates a reading session for the product.
     *
     * <br>pre:<br> None.
     * <br>post:<br> None.
     * 
     * @param page the page being read
     * @return a string describing the reading session
     */
    public String simulateReadingSession(int page){
        pagesRead++;
        return "Reading " + name + "\n\nReading page " + page + " of " + pages;
    }

    /**
     * Abstract method for selling the bibliographic product.
     * Subclasses must implement this method.
     */
    public abstract void sellBP();
}