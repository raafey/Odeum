package domain;

import java.sql.*;
import java.util.ArrayList;

public class UserDBClient {
    private Connection con;
    
    public UserDBClient(String url, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");           
        con = DriverManager.getConnection(url, username, password);        
    }
    
    public void updateValue(int rowNum, int colNum, String value) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM User;");
        ResultSetMetaData rsmd = rs.getMetaData();

        rs.absolute(rowNum);
        rs.next();     
        String query = "Update User set " + rsmd.getColumnName(colNum + 1) + " = '" + value + "' Where " + rsmd.getColumnName(1) + " = '" + rs.getString(1) + "'";
        stmt.executeUpdate(query);
    }
    
    public ArrayList<User> getAllUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM User;");
        
        while (rs.next()) {
            users.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
        }
        return users;
    }
    
    public String[] getColumnNames() throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM User;");
        ResultSetMetaData rsmd = rs.getMetaData();
        String colNames[] = new String[rsmd.getColumnCount()];
        
        for (int i = 0; i < rsmd.getColumnCount(); ++i)
            colNames[i] = rsmd.getColumnName(i + 1);        
        
        return colNames;
    }
    
    public void insertUser(User user) throws SQLException {
        Statement stmt = con.createStatement();
        
        String values = "'"  + user.getUsername() + "',";
        values += "'" + user.getName() + "',";
        values += "'" + user.getEmail() + "',";
        values += "'" + user.getAddress() + "',";
        values += "'" + user.getContact() + "',";
        values += "'" + user.getDateOfBirth() + "',";
        values += "'" + user.getPassword() + "');";
        
        stmt.execute("INSERT INTO User(username, name, email, address, contact, dateOfBirth, password) VALUES(" + values);
    }
    
    public String checkLoginCreds(String username, String password) throws SQLException {
        String code = "";
        
        if (isUsernameTaken(username)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE password = '" + password + "';");            
            
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

        ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE username = '" + username + "';");
        
        boolean flag = false;
        while (rs.next())
            flag = true;
     
        return flag;
    }

    public boolean isUserPresent(String userId) throws SQLException {
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE userId = '" + userId + "';");
        
        boolean flag = false;
        while (rs.next())
            flag = true;
     
        return flag; 
    }
}
