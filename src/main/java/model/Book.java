package model;

import java.sql.Date;

public class Book {
    private int bookId;
    private int userId;
    private String bookName;
    private String author;
    private Date releaseDate;

    public Book(int bookId, int userId, String bookName, String author, Date releaseDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.bookName = bookName;
        this.author = author;
        this.releaseDate = releaseDate;
    }

    public Book(int userId, String bookName, String author, Date releaseDate) {
        this.userId = userId;
        this.bookName = bookName;
        this.author = author;
        this.releaseDate = releaseDate;
    }



    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
