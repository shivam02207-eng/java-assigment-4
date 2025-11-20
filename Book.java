import java.io.*;

public class Book implements Comparable<Book> {
    private int bookId;
    private String title;
    private String author;
    private String category;
    private boolean isIssued;

    public Book(int bookId, String title, String author, String category, boolean isIssued) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isIssued = isIssued;
    }

    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public boolean getIsIssued() { return isIssued; }

    public void markAsIssued() { isIssued = true; }
    public void markAsReturned() { isIssued = false; }

    public String toString() {
        return bookId + "," + title + "," + author + "," + category + "," + isIssued;
    }

    public static Book fromString(String s) {
        String[] p = s.split(",");
        return new Book(Integer.parseInt(p[0]), p[1], p[2], p[3], Boolean.parseBoolean(p[4]));
    }

    public void displayBookDetails() {
        System.out.println("ID: " + bookId + " | " + title + " | " + author + " | " + category + " | Issued: " + isIssued);
    }

    @Override
    public int compareTo(Book o) {
        return this.title.compareToIgnoreCase(o.title);
    }
}

