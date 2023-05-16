package model;
import java.util.Calendar;

public class Payment {
    private Calendar dateOperation;
    private double pay;

    public Payment(double pay) {
        this.dateOperation = Calendar.getInstance();
        this.pay = pay;
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
