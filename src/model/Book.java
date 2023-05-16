package model;
import java.util.Calendar;

public class Book extends BibliographicProduct{
    private String review;
    private int copiesSold;
    private Genre genre;

    public Book(String id, String name, String url, int pages, Calendar publication, double value, int pagesRead, String review, int copiesSold, int genre) {
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
    public int getCopiesSold() {
        return copiesSold;
    }
    public void setCopiesSold(int copiesSold) {
        this.copiesSold = copiesSold;
    }
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(int genre) {
        this.genre = Genre.values()[genre-1];
    }

    @Override
    public String toString() {
        return super.toString() + "\nReview: " + review + "\nNumber of copies sold: " + copiesSold + "\nGenre: " + genre;
    }

    public void updateBook(String name, String url, int pages, Calendar publication, double value, int pagesRead, String review, int copiesSold, int genre){
        super.updateBP(name, url, pages, publication, value, pagesRead);
        this.review = review.equals("-1")|review.equals("")?this.review:review;
        this.copiesSold = copiesSold==-1?this.copiesSold:copiesSold;
        this.genre = genre==-1?this.genre:Genre.values()[genre-1];
    }

    @Override
    public void sellBP() {
        copiesSold++;    
    }
}
