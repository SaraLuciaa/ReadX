package model;
import java.util.Calendar;

public class Payment {
    private String typeBP;
    private String subtype;
    private Calendar dateOperation;
    private double pay;

    /**
     * Creates a Payment object with the specified type, subtype, and payment amount.
     * <br>pre:<br> None
     * <br>post:<br> A Payment object is created with the specified type, subtype, and payment amount.
     *
     * @param typeBP   The type of the payment (e.g., credit card, cash, etc.).
     * @param subtype  The subtype of the payment (e.g., Visa, Mastercard, etc.).
     * @param pay      The payment amount.
     */
    public Payment(String typeBP, String subtype,double pay){
        this.subtype = subtype;
        this.typeBP = typeBP;
        this.dateOperation = Calendar.getInstance();
        this.pay = pay;
    }

    public String getTypeBP() {
        return typeBP;
    }
    public void setTypeBP(String typeBP) {
        this.typeBP = typeBP;
    }
    public String getSubtype() {
        return subtype;
    }
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }
    public Calendar getDateOperation() {
        return dateOperation;
    }
    public void setDateOperation(Calendar dateOperation) {
        this.dateOperation = dateOperation;
    }
    public double getPay() {
        return pay;
    }
    public void setPay(double pay) {
        this.pay = pay;
    }
}
