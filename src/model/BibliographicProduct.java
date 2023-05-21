package model;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class BibliographicProduct implements Comparable<BibliographicProduct>{
    private String id;
    private String name;
    private String url;
    private int pages;
    private Calendar publication;
    private double value;
    private int pagesRead;

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

    public String toString() {
        SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MM-yyyy");
        return "Id: " + id + "\nName: " + name + "\nUrl: " + url + "\nPages: " + pages + "\nDate of publication: " + timeStamp.format(publication.getTime()) + "\nValue: " + value + "\nNumber of pages read: " + pagesRead;
    }

    public void updateBP(String name, String url,int pages, Calendar publication, double value, int pagesRead){
        this.name = name.equals("-1")|name.equals("")?this.name:name;
        this.url = id.equals("-1")|url.equals("")?this.url:url;
        this.pages = pages==-1?this.pages:pages;
        this.publication = publication;
        this.value = value==-1?this.value:value;
        this.pagesRead = pagesRead==-1?this.pagesRead:pagesRead;
    }

    public String simulateReadingSession(int page){
        return "Reading " + name + "\n\nReading page " + page + " of " + pages;
    }

    public abstract void sellBP();

    @Override
    public int compareTo(BibliographicProduct bp) {
        return this.publication.compareTo(bp.getPublication());
    }
}