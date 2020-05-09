package domain;

public class User {
    private String username;
    private String name;
    private String contact;
    private String dateOfBirth;
    private String email;
    private String address;
    private String password;
    private String userId;
    
    public User (String userId, String username, String name, String email, String address, String contact, String dateOfBirth, String password) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.userId = userId;
    }
    
    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
