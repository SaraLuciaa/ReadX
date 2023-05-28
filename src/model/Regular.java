package model;

public class Regular extends User{
    private int maxBook;
    private int maxMagazine;

    /**
     * Constructor for creating a Regular user.
     *
     * <br>pre:<br> The name and id parameters must not be null.
     * <br>post:<br> A new Regular user is created with the specified name and id.
     *
     * @param name The name of the Regular user.
     * @param id The ID of the Regular user.
     */
    public Regular(String name, String id) {
        super(name, id);
        this.maxBook = 5;
        this.maxMagazine = 2;
    }

    /**
     * Buys a bibliographic product using the provided payment method.
     *
     * <br>pre:<br> The newP and newBP parameters must not be null.
     * <br>post:<br> The bibliographic product is purchased using the payment method.
     *
     * @param newP The payment method to use for the purchase.
     * @param newBP The bibliographic product to buy.
     * @return A message indicating the success or failure of the purchase.
     */
    public String buyBP(Payment newP, BibliographicProduct newBP) {
        String message = super.buyBP(newP, newBP);
        if (newBP instanceof Book) {
            maxBook--;
        } else if (newBP instanceof Magazine) {
            maxMagazine--;
        }
        return message;
    }

    /**
     * Checks if the user can buy a bibliographic product of the specified type.
     *
     * <br>pre:<br> The type parameter must be either 1 (book) or 2 (magazine).
     * <br>post:<br> Returns true if the user can buy a bibliographic product of the specified type, false otherwise.
     *
     * @param type The type of bibliographic product to check (1 for book, 2 for magazine).
     * @return True if the user can buy a bibliographic product of the specified type, false otherwise.
     */
    public boolean canBuy(int type) {
        boolean canB = false;
        if (type == 1 && maxBook > 0) {
            canB = true;
        } else if (type == 2 && maxMagazine > 0) {
            canB = true;
        }
        return canB;
    }

    /**
     * Cancels the subscription to a magazine and increases the available magazine slots.
     *
     * <br>pre:<br> The idM parameter must not be null.
     * <br>post:<br> The subscription to the magazine with the specified ID is canceled and the available slots for magazines are increased.
     *
     * @param idM The ID of the magazine subscription to cancel.
     * @return A message indicating the success or failure of the subscription cancellation.
     */
    public void cancelSuscription(String idM) {
        maxMagazine++;
        super.cancelSuscription(idM);
    }
}