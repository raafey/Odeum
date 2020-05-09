package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TriviaDBClient {
    private Connection con;
    
    public TriviaDBClient(String url, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");           
        con = DriverManager.getConnection(url, username, password);        
    }    
    
    public void insertQuestion(Question q) throws SQLException {
        String query = "Insert into question (question, opt_a, opt_b, opt_c, opt_d, answer, trivia_Id) values (";
        query += "'" + q.getQuestion() + "',";
        query += "'" + q.getA() + "',";
        query += "'" + q.getB() + "',";
        query += "'" + q.getC() + "',";
        query += "'" + q.getD() + "',";
        query += "'" + q.getAnswer() + "',";
        query += "'" + q.getTriviaId() + "');";

        Statement stmt = con.createStatement();
        stmt.executeUpdate(query);
    }
    
    public ArrayList<Question> getQuestions(String triviaId) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from question where triviaId = '" + triviaId + "';");
        ArrayList<Question> questions = new ArrayList<>();
        
        while (rs.next()) {
            Question newQ = new Question(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
            questions.add(newQ);
        }
        return questions;
    }
    
    public ArrayList<Trivia> getTrivias() throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from trivia");
        ArrayList<Trivia> trivia = new ArrayList<>();
        
        while (rs.next()) {
            Trivia newTrivia = new Trivia(rs.getString(1), rs.getString(2));
            trivia.add(newTrivia);
        }
        
        return trivia;
    }
    
    public boolean isTriviaNameTaken(String name) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Trivia where name = '" + name + "';");            

        boolean flag = false;
        while (rs.next())
            flag = true;
        
        return flag;
    }
    
    public String getTriviaId(String triviaName) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Trivia where name = '" + triviaName + "';");            
        
        rs.next();
        return rs.getString(1);
    }
    
    public void createTrivia(String triviaName, ArrayList<Question> questions) throws SQLException {
        Statement stmt = con.createStatement();   
        stmt.executeUpdate("Insert into trivia (name) values ('" + triviaName + "')");
        
        String triviaId = getTriviaId(triviaName);
        
        for (int i = 0; i < questions.size(); ++i) {
            Question q = questions.get(i);
            q.setTriviaId(triviaId);
            insertQuestion(q);
        }
    }
    
    public void deleteTrivia(String triviaId) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.executeUpdate("delete from question where trivia_id = '" + triviaId + "';");
        stmt.executeUpdate("Delete from trivia where trivia_id = '" + triviaId + "';");
    }
}