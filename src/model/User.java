package model;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public abstract class User {
    private String name; 
    private String id;
    private Calendar vinculation;
    String[][][] library;

    private ArrayList<BibliographicProduct> bp;
    private ArrayList<Payment> payments; 

    public User(String name, String id) {
        this.name = name;
        this.id = id;
        this.vinculation = Calendar.getInstance();
        this.bp = new ArrayList<BibliographicProduct>();
        this.payments = new ArrayList<Payment>();
        this.library = null;
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

    public void orderBP(){
        boolean change = false;
        for (int i=0; i<bp.size()-1&&!change; i++) {
            change = false;
            for (int j = 0; j <bp.size()-i-1; j++) {
                BibliographicProduct actualBP = bp.get(j);
                BibliographicProduct nextBP = bp.get(j + 1);

                if (actualBP.getPublication().compareTo(nextBP.getPublication()) > 0) {
                    bp.set(j, nextBP);
                    bp.set(j+1, actualBP);
                    change = true;
                }
            }
        }
    }

    public String[][][] generateLibrary(){
        orderBP();
        int cont = 0;
        int numL = (int) Math.floor(bp.size()/25);
        int row = 5;
        int column = 5;
        library = new String[numL+2][row][column]; 
        for(int k=0; k<library.length; k++){
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    library[k][i][j] = cont<bp.size()?bp.get(cont).getId():"___";
                    cont++;
                }
            }
        }
        return library;
    }

    public String searchBP(int x, int y, int z){
        String idBP = "___";
        boolean search = (z<library.length)&&(x<library[0].length)&&(y<library[0][0].length)?true:false;
        for(int k=0; k<library.length&&search; k++){
            for (int i = 0; i < library[k].length&&search; i++) {
                for (int j = 0; j < library[k][i].length&&search; j++) {
                    if(k==z&&i==x&&j==y){
                        idBP = library[k][i][j];
                        search = false;
                    }
                }
            }
        }
        return idBP;
    }

    public String cancelSuscription(String idM){
        String message = "-- Your bibliographic products --\n";
        boolean cancel = false;
        for(int i=0; i<bp.size(); i++){
            if(bp.get(i).getId().equalsIgnoreCase(idM)&&!cancel){
                bp.remove(i);
                cancel = true;
            } else {
                message += bp.get(i).getId() + "  " + bp.get(i).getName();
            }            
        }
        message += "---------------------------------";
        return message;
    }
}