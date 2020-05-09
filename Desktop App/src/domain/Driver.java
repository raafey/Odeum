package domain;

import java.sql.SQLException;
import java.util.ArrayList;

public class Driver {
    public static void main(String args[]) throws ClassNotFoundException, SQLException
    {
        TriviaDBClient d = new TriviaDBClient("jdbc:mysql://localhost:3306/odeum", "root", "");
        ArrayList<Trivia> list = d.getTrivias();
        
        for (int i = 0; i < list.size(); ++i) {
            System.out.println(list.get(i).getName());
        }
    }
    
}