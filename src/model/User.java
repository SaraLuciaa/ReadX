package model;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public abstract class User {
    private String name; 
    private String id;
    private Calendar vinculation;
    String[][][] library;

    private ArrayList<BibliographicProduct> bp;
    private ArrayList<Payment> payments; 

    /**
     * Constructs a User object with the specified name and ID.
     *
     * <br>pre:<br> The name and ID are not null.
     * <br>post:<br> A User object is created with the provided name, ID, and current date of vinculation.
     *
     * @param name The name of the user.
     * @param id The ID of the user.
     */
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
    
    /**
     * Adds a new bibliographic product and payment to the user's lists and generates a bill.
     *
     * <br>pre:<br> The newP and newBP are not null.
     * <br>post:<br> The newBP and newP are added to the user's bibliographic products and payments lists respectively.
     *
     * @param newP The payment for the bibliographic product.
     * @param newBP The bibliographic product to be added.
     * @return A string representation of the bill for the purchase.
     */
    public String buyBP(Payment newP, BibliographicProduct newBP){
        SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MM-yyyy");
        bp.add(newBP);
        payments.add(newP);
        return "----------- Bill -----------\n" + newBP.getName() + "\nOperation date: " + timeStamp.format(newP.getDateOperation().getTime()) + "\nAmount paid: " + newP.getPay() + "\n----------------------------";
    }

    /**
     * Searches for a bibliographic product in the user's list based on its ID.
     *
     * <br>pre:<br> The ID is not null.
     * <br>post:<br> If a bibliographic product with the specified ID is found, it is returned; otherwise, null is returned.
     *
     * @param id The ID of the bibliographic product to search for.
     * @return The bibliographic product with the specified ID, or null if it is not found.
     */
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

    /**
     * Orders the user's bibliographic products based on the publication date.
     *
     * <br>pre:<br> -
     * <br>post:<br> The user's bibliographic products are ordered based on the publication date in ascending order.
     */
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

    /**
     * Generates a library structure with the user's bibliographic products.
     *
     * <br>pre:<br> -
     * <br>post:<br> A library structure is generated with the user's bibliographic products ordered by publication date.
     *
     * @return A three-dimensional array representing the library structure.
     */
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

    /**
     * Searches for a bibliographic product in the library structure based on its coordinates.
     *
     * <br>pre:<br> The coordinates (x, y, z) are within the valid range of the library structure.
     * <br>post:<br> If a bibliographic product is found at the specified coordinates, its ID is returned; otherwise, "___" is returned.
     *
     * @param x The x-coordinate of the bibliographic product in the library structure.
     * @param y The y-coordinate of the bibliographic product in the library structure.
     * @param z The z-coordinate of the bibliographic product in the library structure.
     * @return The ID of the bibliographic product at the specified coordinates, or "___" if it is not found.
     */
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

    /**
     * Cancels the subscription for a bibliographic product.
     *
     * <br>pre:<br> The IDM is not null.
     * <br>post:<br> If a bibliographic product with the specified ID is found, it is removed from the user's list; otherwise, no changes are made.
     *
     * @param idM The ID of the bibliographic product to cancel the subscription for.
     */
    public void cancelSuscription(String idM){
        boolean cancel = false;
        for(int i=0; i<bp.size(); i++){
            if(bp.get(i).getId().equalsIgnoreCase(idM)&&!cancel){
                bp.remove(i);
                cancel = true;
            }          
        }
    }
}