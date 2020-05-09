package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EventDBClient {
    private Connection con;
    
    public EventDBClient(String url, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");           
        con = DriverManager.getConnection(url, username, password);          
    }
    
    public void insertEvent(Event event) throws SQLException {
        Statement stmt = con.createStatement();
        String query = "Insert into Event (name, genre, rating, feedback, synopsis, trailerUrl, cast, theaterId, imageUrl) values(";
        query += "'" + event.getName() + "',";
        query += "'" + event.getGenre() + "',";
        query += "'" + event.getRating() + "',";
        query += "'" + event.getFeedback() + "',";
        query += "'" + event.getSynopsis() + "',";
        query += "'" + event.getTrailerUrl() + "',";
        query += "'" + event.getCast() + "',";
        query += "'" + event.getTheaterId() + "',";
        query += "'" + event.getImageUrl() + "');";
        stmt.executeUpdate(query);
    }
    
    public String getEventId(String eventName, String theaterId) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Event where theaterId = ' " + theaterId + "' and name = '"+ eventName  + "';");        
        rs.next();
        return rs.getString(1);
    }
    
    public String getEventName(String eventId) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from event where eventId = '" + eventId+ "';");        
        rs.next();
        return rs.getString(2);
    }
    
    public Event getSelectedEvent(String theaterId, String eventName) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Event where name = '" + eventName + "' and theaterId = '" + theaterId + "';"); 
        rs.next();
        return new Event(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
    }

    public void updateEvent(Event event) throws SQLException {
        Statement stmt = con.createStatement();
        String query = "Update Event set ";
        query += "name='" + event.getName() + "',";
        query += "genre='" + event.getGenre() + "',";
        query += "rating='" + event.getRating() + "',";
        query += "feedback='" + event.getFeedback() + "',";
        query += "synopsis='" + event.getSynopsis() + "',";
        query += "trailerUrl='" + event.getTrailerUrl() + "',";
        query += "cast='" + event.getCast() + "',";
        query += "imageURL='" + event.getImageUrl() + "' where eventId = '" + event.getEventId() + "';";
        System.out.println(query);
        stmt.executeUpdate(query);
    }

    public void deleteRow(String eventId) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.executeUpdate("delete FROM Event where eventId = '" + eventId + "';");       
    }

    public boolean isEventPresent(String text, String id) throws SQLException {
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM Event WHERE name = '" + text + "' and theaterId = '" + id + "';");
        
        boolean flag = false;
        while (rs.next())
            flag = true;
     
        return flag;
    }
    
    public ArrayList<String> getEventNames(String theaterId) throws SQLException {
        ArrayList<String> events = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Event where theaterId = ' " + theaterId + "';");
        
        while (rs.next()) {
            events.add(rs.getString(2));
        }
        return events;        
    }
}
