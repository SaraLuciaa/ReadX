package model;

public class Regular extends User{
    private int maxBook;
    private int maxMagazine;

   
    public Regular(String name, String id) {
        super(name, id);
        this.maxBook = 5;
        this.maxMagazine =2;
    }

    public String buyBP(Payment newP, BibliographicProduct newBP){
        String message = super.buyBP(newP, newBP);
        if(newBP instanceof Book){maxBook--;}
        else if(newBP instanceof Magazine){maxMagazine--;}
        return message;
    }

    public boolean canBuy(int type){
        boolean canB = false;
        if(type==1&&maxBook>0){
            canB = true;
        } else if (type==2&&maxMagazine>0){
            canB = true;
        }
        return canB;
    }

    public String cancelSuscription(String idM){
        maxMagazine++;
        return super.cancelSuscription(idM);
    }
}
