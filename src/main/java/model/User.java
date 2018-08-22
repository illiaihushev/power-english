package model;

import java.sql.Date;
import java.util.List;

public class User {
    private int userId;
    private String login;
    private String passphrase;
    private Date registrationDate;

    private List<Book> books;

    public User(){
    }

    public User(String login, String passphrase, Date registrationDate) {
        this.login = login;
        this.passphrase = passphrase;
        this.registrationDate = registrationDate;
    }

    public User(int userId, String login, String passphrase, Date registrationDate){
        this.userId = userId;
        this.login  = login;
        this.passphrase = passphrase;
        this.registrationDate = registrationDate;
    }

    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getLogin() {
        return login;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }
}
