package model;
import java.util.Calendar;

public class Book extends BibliographicProduct{
    private String review;
    private int copiesSold;
    private Genre genre;

    public Book(String id, String name, String url, int pages, Calendar publication, double value, int pagesRead, String review, int copiesSold, Genre genre) {
        super(id, name, url, pages, publication, value, pagesRead);
        this.review = review;
        this.copiesSold = copiesSold;
        this.genre = genre;
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
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return super.toString() + "\nReview: " + review + "\nNumber of copies sold: " + copiesSold + "\nGenre: " + genre;
    }
}
