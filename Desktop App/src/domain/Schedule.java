package domain;

public class Schedule {
    private String eventName;
    private String hallName;
    private String date;
    private String time;
    private String id;
    private String price;
    
    public Schedule(String id, String date, String time, String price, String eventName, String hallName) {
        this.eventName = eventName;
        this.hallName = hallName;
        this.date = date;
        this.time = time;
        this.id = id;
        this.price = price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
    
    public String getPrice() {
        return price;
    }
    
    public void setId(String Id) {
        this.id = Id;
    }
    
    public String getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
