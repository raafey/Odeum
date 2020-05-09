package ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ArticlePopupController {
    private Connection con;
    
    @FXML
    private Button addButton;

    @FXML
    private TextField linkField;

    @FXML
    private Button refreshButton;

    @FXML
    private ListView linkList;

    @FXML
    private Button viewButton;

    @FXML
    void ViewButtonClicked(MouseEvent event) {
        try {
            String link = (String)linkList.getSelectionModel().getSelectedItem();
            Desktop desk = Desktop.getDesktop();
            desk.browse(new URI(link));
        } catch (Exception e) {}
    }

    @FXML
    void addButtonClicked(MouseEvent event) throws SQLException {
        if (!linkField.getText().equals("")) {
            insertArticle(linkField.getText());
            linkField.clear();
        }
    }

    @FXML
    void refreshButtonClicked(MouseEvent event) throws SQLException {
        populateList();
    }
    
    private void insertArticle(String link) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.executeUpdate("Insert into article (link) values ( '" + fixSQLFormat(link) + "');");
    }
    
    private void populateList() throws SQLException {
        ArrayList<String> eventNames = getLinks();
        ObservableList<String> items = FXCollections.observableArrayList(eventNames);
        linkList.setItems(items);
    }
    
    public ArticlePopupController() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");           
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/odeum", "root", "");    
    }
    
    public void initialize() throws SQLException {
        populateList();
    }
    
    private String fixSQLFormat(String str) {
        String current = "";
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == '\'') {
                current += "''";
            }
            else { 
                current += str.charAt(i);
            }
        } 
        return current;
    }

    private ArrayList<String> getLinks() throws SQLException {
        Statement stmt = con.createStatement();
        ArrayList<String> links = new ArrayList<>();
        
        ResultSet rs = stmt.executeQuery("select * from article");
        
        while (rs.next()) {
            links.add(rs.getString(2));
        }
        
        return links;
    }
}
