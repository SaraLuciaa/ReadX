package model;
import java.util.Calendar;

public class Payment {
    private String typeBP;
    private String subtype;
    private Calendar dateOperation;
    private double pay;

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
