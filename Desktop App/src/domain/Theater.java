package domain;

public class Theater {
    private String theaterId;
    private String name;
    private String halls;
    private String address;
    private String city;
    private String contact;
    private String username;
    private String password;
    
    public Theater (String theaterId, String name, String halls, String address, String city, String contact, String username, String password) {
        this.theaterId = theaterId;
        this.name = name;
        this.halls = halls;
        this.address = address;
        this.city = city;
        this.contact = contact;
        this.username = username;
        this.password = password;
    }
    
    public String getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(String theaterId) {
        this.theaterId = theaterId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
    
    public String getHalls() {
        return halls;
    }

    public void setHalls(String halls) {
        this.halls = halls;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getID() {
        return theaterId;
    }
}
