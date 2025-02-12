package models;

public class Account {
    private int id;
    private String lastname;
    private String firstname;
    private String email;
    private String password;

    // Constructeur
    public Account(String lastname, String firstname, String email, String password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{id=" + id + ", lastname='" + lastname + "', firstname='" + firstname + "', email='" + email + "'}";
    }
}
