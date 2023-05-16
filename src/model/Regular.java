package model;

public class Regular extends User{
    private int maxBook;
    private int maxMagazine;

   
    public Regular(String name, String id) {
        super(name, id);
        this.maxBook = 5;
        this.maxMagazine =2;
    }

    public String buyBP(double pay, BibliographicProduct newBP){
        String message = super.buyBP(pay, newBP);
        if(newBP instanceof Book){maxBook--;}
        else if(newBP instanceof Magazine){maxMagazine--;}
        return message;
    }

    public boolean canBuy(int type){
        boolean canB = false;
        if(type==1&&maxBook>0){
            canB = true;
        } else if (type==1&&maxMagazine>0){
            canB = true;
        }
        return canB;
    }
}
