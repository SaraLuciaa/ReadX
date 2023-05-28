package model;
import java.util.Calendar;

public class Book extends BibliographicProduct{
    private String review;
    private long copiesSold;
    private Genre genre;

    /**
     * Represents a book, which is a type of bibliographic product.
     * 
     * <br>pre: The id, name, url, pages, publication, value, pagesRead, review, copiesSold, and genre parameters are valid.
     * <br>post: A Book object is created with the specified attributes.
     * 
     * @param id The unique identifier of the book.
     * @param name The name of the book.
     * @param url The URL of the book.
     * @param pages The number of pages in the book.
     * @param publication The publication date of the book.
     * @param value The value or price of the book.
     * @param pagesRead The number of pages already read in the book.
     * @param review The review of the book.
     * @param copiesSold The number of copies sold for the book.
     * @param genre The genre of the book (represented as an integer).
    */
    public Book(String id, String name, String url, int pages, Calendar publication, double value, long pagesRead, String review, long copiesSold, int genre) {
        super(id, name, url, pages, publication, value, pagesRead);
        this.review = review;
        this.copiesSold = copiesSold;
        this.genre = Genre.values()[genre-1];
    }

    public String getReview() {
        return review;
    }
    public void setReview(String review) {
        this.review = review;
    }
    public long getCopiesSold() {
        return copiesSold;
    }
    public void setCopiesSold(long copiesSold) {
        this.copiesSold = copiesSold;
    }
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(int genre) {
        this.genre = Genre.values()[genre-1];
    }

    /**
     * Returns a string representation of the object, including the superclass's toString output
     * along with additional information such as the review, number of copies sold, and genre.
     *
     * <br>pre:<br> None.
     * <br>post:<br> None.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString() + "\nReview: " + review + "\nNumber of copies sold: " + copiesSold + "\nGenre: " + genre;
    }

    /**
     * Updates the book's information with the specified parameters. If a parameter is set to
     * a certain default value (-1 for integers, empty string for review), the corresponding
     * attribute of the book object remains unchanged.
     *
     * <br>pre:<br> None.
     * <br>post:<br> None.
     *
     * @param name          the new name of the book.
     * @param url           the new URL of the book.
     * @param pages         the new number of pages in the book.
     * @param publication   the new publication date of the book.
     * @param value         the new value of the book.
     * @param pagesRead     the new number of pages read in the book.
     * @param review        the new review of the book.
     * @param copiesSold    the new number of copies sold for the book.
     * @param genre         the new genre of the book.
     */
    public void updateBP(String name, String url, int pages, Calendar publication, double value, long pagesRead, String review, long copiesSold, int genre){
        super.updateBP(name, url, pages, publication, value, pagesRead);
        this.review = review.equals("-1")|review.equals("")?this.review:review;
        this.copiesSold = copiesSold==-1?this.copiesSold:copiesSold;
        this.genre = genre==-1?this.genre:Genre.values()[genre-1];
    }

    /**
     * Increases the number of copies sold for the book by one.
     * 
     * <br>pre:<br> None.
     * <br>post:<br> The number of copies sold for the book is increased by one.
     */
    @Override
    public void sellBP() {
        copiesSold++;    
    }
}
