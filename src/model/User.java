package model;
import java.util.ArrayList;
import java.util.Calendar;

public abstract class User {
    private String name; 
    private String id;
    private Calendar vinculation;

    private ArrayList<BibliographicProduct> bp;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
        this.vinculation = Calendar.getInstance();
        this.bp = new ArrayList<BibliographicProduct>();
    } 

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Calendar getVinculation() {
        return vinculation;
    }
    public void setVinculation(Calendar vinculation) {
        this.vinculation = vinculation;
    }

    public String toString(){
        return "Name: " + name + "\nId: " + id + "\nType: " + this.getClass().getSimpleName(); 
    }
}
