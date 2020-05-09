package domain;
import java.sql.*;
import java.util.ArrayList;

public class TheaterDBClient {
    private Connection con;
    
    public TheaterDBClient(String url, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");           
        con = DriverManager.getConnection(url, username, password);        
    }
    
    public void updateValue(int rowNum, int colNum, String value) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Theater;");
        ResultSetMetaData rsmd = rs.getMetaData();

        rs.absolute(rowNum);
        rs.next();     
        String query = "Update Theater set " + rsmd.getColumnName(colNum + 1) + " = '" + value + "' Where " + rsmd.getColumnName(1) + " = '" + rs.getString(1) + "'";
        stmt.executeUpdate(query);
    }
    
    public ArrayList<Theater> getAllTheaters() throws SQLException {
        ArrayList<Theater> theaters = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Theater;");
        
        while (rs.next()) {
            theaters.add(new Theater(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
        }
        return theaters;
    }
    
    public String[] getColumnNames() throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Theater;");
        ResultSetMetaData rsmd = rs.getMetaData();
        String colNames[] = new String[rsmd.getColumnCount()];
        
        for (int i = 0; i < rsmd.getColumnCount(); ++i)
            colNames[i] = rsmd.getColumnName(i + 1);        
        
        return colNames;
    }
    
    public void insertHall(String username) throws SQLException {
        Theater theater = getTheaterDetails(username);
        Statement stmt = con.createStatement();
        
        for (int i = 0; i < Integer.parseInt(theater.getHalls()); ++i) {
            String str = "Hall-" + Integer.toString(i + 1);
            stmt.execute("INSERT INTO hall (name, theaterId, isFree) values ('" + str + "','" + theater.getID() +"','T')");
        }
    }
    
    public ArrayList<String> getHalls(String theaterId) throws SQLException {
        ArrayList<String> halls = new ArrayList<>();
        
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from hall where theaterId = '" + theaterId + "';");
        
        while (rs.next()) {
            halls.add(rs.getString(2));            
        }
        
        return halls;
    }
    
    public String getHallId(String theaterId, String hallName) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from hall where theaterId = '" + theaterId + "' and name = '" + hallName +"';");
        rs.next();
        return rs.getString(1);
    }
    
    public void insertSchedule(String eventId, String hallId, String price, String date, String time) throws SQLException {
        Statement stmt = con.createStatement();
        String values = "'" + date +"',";
        values += "'" + time +"',";
        values += "'" + price + "',";
        values += "'" + eventId +"',";
        values += "'" + hallId +"')";
        stmt.executeUpdate("Insert into schedule(date, time, price, eventId, hallId) values (" + values);
    }
    
    public String getHallName(String hallId) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from hall where hallId = '" + hallId+"';");
        rs.next();
        return rs.getString(2);
    }
    
    public ArrayList<Schedule> getTheaterSchedules(String theaterId) throws SQLException {
        ArrayList<Schedule> schedules = new ArrayList<>();
        Statement stmt = con.createStatement();   
        ResultSet rs = stmt.executeQuery("Select * from schedule where hallId in (select"
                + " hallId from hall where theaterId = '" + theaterId +"');");
        
        while (rs.next()) {
            schedules.add(new Schedule(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
        }
        
        return schedules;
    }
    
    public void insertTheater(Theater theater) throws SQLException {
        Statement stmt = con.createStatement();
        
        String values = "'" + theater.getName() + "',";
        values += "'" + theater.getHalls() + "',";              
        values += "'" + theater.getAddress() + "',";
        values += "'" + theater.getCity() + "',";
        values += "'" + theater.getContact() + "',";
        values += "'" + theater.getUsername() + "',";
        values += "'" + theater.getPassword() + "');";
        
        System.out.println(values);
        
        stmt.execute("INSERT INTO Theater(name, halls, address, city, contact, username, password) VALUES(" + values);
    }
    
    public Theater getTheaterDetails(String username) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Theater WHERE username = '" + username + "';");
        rs.next();
        return new Theater(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
    }
    
    public String checkLoginCreds(String username, String password) throws SQLException {
        String code = "";
        
        if (isUsernameTaken(username)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Theater WHERE password = '" + password + "';");            
            
            boolean flag = false;
            while (rs.next())
                flag = true;
            
            if (flag)
                code = "Pass_OK | User_OK";
            else
                code = "Pass_NOT_OK | User_OK";
        }
        else {
            code = "Pass_NOT_OK | User_NOT_OK";            
        }
                        
        return code;
    }
    
    public boolean isUsernameTaken(String username) throws SQLException {
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM Theater WHERE username = '" + username + "';");
        
        boolean flag = false;
        while (rs.next())
            flag = true;
     
        return flag;
    }

    public void deleteRow(String schId) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.executeUpdate("Delete from seating_plan where schId ='" + schId + "';");
        stmt.executeUpdate("Delete from schedule where schId ='" + schId + "';");  
    }
    
    public void updateSchedule(Schedule sch) throws SQLException {
        Statement stmt = con.createStatement();
        String query = "Update Schedule set ";
        query += "date='" + sch.getDate() + "',";
        query += "time='" + sch.getTime() + "',";
        query += "eventId='" + sch.getEventName() + "',";
        query += "price='" + sch.getPrice() + "',";
        query += "hallId='" + sch.getHallName() + "' where schId = '" + sch.getId() +"';";
        stmt.executeUpdate(query);        
    }

    public boolean isSlotAvailable(String date, String time, String hallId) throws SQLException {
        boolean flag = true;
        
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from schedule where date = '"+date+"' and time='" + time + "' and hallId = '" + hallId + "';");
        
        while (rs.next()) {
            flag = false;
        }
        
        return flag;
    }
    
    public String getSchID(String date, String time, String hallId) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from schedule where date = '"+date+"' and time='" + time + "' and hallId = '" + hallId + "';");

        rs.next();
        return rs.getString(1);
    }

    public ArrayList<Schedule> getTheaterSchedulesTwo(String theaterId, String hallId) throws SQLException {
        ArrayList<Schedule> schedules = new ArrayList<>();
        Statement stmt = con.createStatement();   
        ResultSet rs = stmt.executeQuery("Select * from schedule where hallId = '" + hallId + "';");
        
        while (rs.next()) {
            schedules.add(new Schedule(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
        }
        
        return schedules;   
    }
    
    public String getEventId(String schId) throws SQLException {
        Statement stmt = con.createStatement();   
        ResultSet rs = stmt.executeQuery("Select eventId from schedule where schId = '" + schId + "';"); 
        rs.next();
        return rs.getString(1);
    }
    
    public String getScheduleIds(String eventId) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select schId from schedule where eventId = '" + eventId + "';");
        String result = "";
        while (rs.next()) {
            result += "'" + rs.getString(1) + "',";
        }
        if (result.length() > 0)
            result = result.substring(0, result.length() - 1);
        return result;
    }

    public void deleteEventRow(String eventId) throws SQLException {
        Statement stmt = con.createStatement();
        String schIds = getScheduleIds(eventId);
        stmt.executeUpdate("Delete from seating_plan where schId in ( " + schIds + ");");
        stmt.executeUpdate("Delete from schedule where eventId ='" + eventId + "';");          
    }
}
