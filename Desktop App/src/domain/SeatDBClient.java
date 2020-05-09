package domain;

import java.sql.*;
import java.util.ArrayList;

public class SeatDBClient {
    private Connection con;
    
    public SeatDBClient(String url, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");           
        con = DriverManager.getConnection(url, username, password);        
    }
    
    public String getBookedUser(String row, String col, String schId) throws SQLException {
        Statement stmt = con.createStatement();
        
        ResultSet rs = stmt.executeQuery("select userId from seating_plan where row = '" + row + "' and col = '" + col + "' and schId = '" + schId + "';");
        
        rs.next();
        
        String result = "";
        
        try {
            result = rs.getString(1);
        } catch (NullPointerException e) {
            result = null;
        }
        
        return result;
    }
    
    public void createSeatingPlan(String schId) throws SQLException {
        Statement stmt = con.createStatement();
        
        int row = 7;
        int col = 14;
        
        ArrayList<String> colNames = new ArrayList<>();
        
        for (int i = 0; i < col; ++i)
            colNames.add(Character.toString ((char) (65 + i)));
        
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                String query = "Insert into seating_plan (row, col, schId, isBooked) values ('" + (i + 1) + "','" + 
                        colNames.get(j) + "','" + schId + "','F');";
                stmt.executeUpdate(query);
            }
        }
    }
    
    public void removeSeatingPlan(String schId) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.executeUpdate("Delete from seating_plan where schId = '" + schId + "';");
    }
    
    public String bookSeat(String row, String col, String schId, String userId) throws SQLException, ClassNotFoundException {
        String code = "";
        
        if (isAvailable(row, col, schId)) {
            if (new UserDBClient("jdbc:mysql://localhost:3306/odeum", "root", "").isUserPresent(userId)) {
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Update seating_plan set userId = '" + userId + "', isBooked = 'T' where row = '" +row+ "' and col = '" + col + "' and schId = '" + schId + "';");
                code = "seat free + username correct";
            } else {
                code = "username incorrect";
            }
        } else {
            code = "seat not free";
        }
        
        return code;
    }
    
    public boolean releaseSeat(String row, String col, String schId) throws SQLException {
        boolean flag = true;

        if (!isAvailable(row, col, schId)) {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("Update seating_plan set userId = NULL, isBooked = 'F' where row = '" +row+ "' and col = '" + col + "' and schId = '" + schId + "';");            
        } else {
            flag = false;
        }
        
        return flag;
    }
    
    public boolean isAvailable(String row, String col, String schId) throws SQLException {
        boolean flag = false;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select userId from seating_plan where col = '" + col + "' and row = '" + row + "' and schId = '" + schId + "';");
        
        rs.next();
        
        try {
            String str = rs.getString(1);
            
            if (str == null) {
                flag = true;
            }
            
        } catch (NullPointerException e) {}        
        
        return flag;
    }
    
    public String[][] getSeatingPlan(String schId) throws SQLException {
        Statement stmt = con.createStatement();
        String seatStatus[][] = new String[7][14];
        
        ResultSet rs = stmt.executeQuery("Select isBooked from seating_plan where schId = '" + schId + "';");
        
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 14; ++j) {
                if (rs.next())
                    seatStatus[i][j] = rs.getString(1);
            }
        }
        return seatStatus;
    }
    
    public Seat getSeat(String row, String col, String schId) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from seating_plan where row = '" +row+"' and col='" + col + "' and schId = '" + schId + "';");        
        rs.next();
        return new Seat(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
    }
    
    public void createTicket(String seatId, String eventId) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.executeUpdate("Insert into ticket (seatId, eventId) values ('" + seatId +"','" + eventId + "');");
    }
    
    public void deleteTicket(String seatId) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.executeUpdate("delete from ticket where seatId = '" + seatId + "';");
    }
    
    public String getEventId(String seatId) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select eventId from schedule where schId in (select schId from seating_plan where seatId = ' " + seatId + "');");
        rs.next();
        return rs.getString(1);
    }
    
    public String getBookedSeatIds(String schId) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select seatId from seating_plan where schId = '" + schId + "' and isBooked = 'T';");
        
        String result = "";
        while (rs.next()) {
            result += "'" + rs.getString(1) + "',";
        }
        if (result.length() > 0)
            result = result.substring(0, result.length() - 1);
        return result;    
    }
    
    public void deleteRow(String schId) throws SQLException {
        Statement stmt = con.createStatement();
        String seats = getBookedSeatIds(schId);
        stmt.executeUpdate("Delete from ticket where seatId in ("+ seats+");");
    }

    public String deleteEventRow(String eventId) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.executeUpdate("delete from ticket where eventId = '" + eventId + "';");         return null;
 }
}