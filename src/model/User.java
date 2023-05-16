package model;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public abstract class User {
    private String name; 
    private String id;
    private Calendar vinculation;

    private ArrayList<BibliographicProduct> bp;
    private ArrayList<Payment> payments; 

    public User(String name, String id) {
        this.name = name;
        this.id = id;
        this.vinculation = Calendar.getInstance();
        this.bp = new ArrayList<BibliographicProduct>();
        this.payments = new ArrayList<Payment>();
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

    public ArrayList<BibliographicProduct> getBp() {
        return bp;
    }

    public void setBp(ArrayList<BibliographicProduct> bp) {
        this.bp = bp;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    public String buyBP(Payment newP, BibliographicProduct newBP){
        SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MM-yyyy");
        bp.add(newBP);
        payments.add(newP);
        return "----------- Bill -----------\n" + newBP.getName() + "\nOperation date: " + timeStamp.format(newP.getDateOperation().getTime()) + "\nAmount paid: " + newP.getPay() + "\n----------------------------";
    }

    public BibliographicProduct searchBP(String id){
        BibliographicProduct product = null;
        boolean search = true;
        for(int i=0; i<bp.size()&&search; i++){
            if(bp.get(i).getId().equalsIgnoreCase(id)){
                product = bp.get(i);
                search = false;
            }
        }
        return product;
    }
}
