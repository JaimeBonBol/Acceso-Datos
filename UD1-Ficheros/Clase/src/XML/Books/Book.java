package XML.Books;

import java.util.ArrayList;

public class Book {
    public String id;
    public String title;
    public String author;
    public ArrayList<String> authors  = new ArrayList<>();
    public int year;
    public double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String mostrarAutores(){
        return String.join(", ", getAuthors() );
    }

    @Override
    public String toString() {
        return String.format(
                "Book{id=%s; title=%s; authors=%s; year=%d; price=%.2f}",
                id, title, mostrarAutores(), year, price
        );
    }
}
