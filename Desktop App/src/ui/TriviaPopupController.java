package ui;

import domain.Trivia;
import domain.TriviaDBClient;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TriviaPopupController {
    private TriviaDBClient triviaClient;

    @FXML
    private TextField nameField;

    @FXML
    private Button createTriviaButton;

    @FXML
    private TextField numQField;

    @FXML
    private TableView triviaTable;

    @FXML
    private Button viewTriviaButton;

    @FXML
    private Button deleteButton;
    
    @FXML
    private Label warningLabel;
    
    @FXML
    private Button refreshButton;

    @FXML
    void createTriviaButtonClicked(MouseEvent event) throws SQLException {
        if (numQField.getText().equals("") || nameField.getText().equals("")) {
            warningLabel.setText("Please fill all fields!");
        } else {
            try {
                int n = Integer.valueOf(numQField.getText());
                
                if (!triviaClient.isTriviaNameTaken(fixSQLFormat(nameField.getText()))) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("questionPopup.fxml"));
                    Parent parent = loader.load();
                    QuestionPopupController controller = loader.getController();
                    controller.setTrivia(nameField.getText(), n);

                    numQField.clear();
                    warningLabel.setText("");
                    nameField.clear();

                    Stage stage = new Stage();
                    stage.setTitle("Make Trivia");
                    stage.getIcons().add(new Image("Alduin.png"));
                    stage.setScene(new Scene(parent));
                    stage.show();                    
                }
                else {
                    warningLabel.setText(nameField.getText() + " is taken!");
                }
                
            } catch (NumberFormatException e) {
                warningLabel.setText("Invalid input entered!");
            } catch (IOException e) {e.printStackTrace();}
        }
    }

    @FXML
    void deleteButtonClicked(MouseEvent event) throws SQLException {  
        try {
            Trivia trivia = (Trivia)triviaTable.getSelectionModel().getSelectedItem();
            
            if (trivia != null)
                triviaClient.deleteTrivia(trivia.getId());
            
            buildTable();
        } catch (NullPointerException e) {}
    }
    
    @FXML
    void refreshButtonClicked(MouseEvent event) throws SQLException {
        buildTable();
    }

    @FXML
    void viewTriviaButtonClicked(MouseEvent event) {
        
    }
    
    public TriviaPopupController() throws ClassNotFoundException, SQLException {
        triviaClient = new TriviaDBClient("jdbc:mysql://localhost:3306/odeum", "root", "");
    }
    
    public void initialize() throws SQLException {
        buildTable();
    }
    
    public void buildTable() throws SQLException {
        triviaTable.getColumns().clear();
        triviaTable.getItems().clear();
        
        ArrayList<String> colNames = new ArrayList<>();
        colNames.add("name");
        TableColumn tabCols[] = new TableColumn[colNames.size()];
        ObservableList<Trivia> data = FXCollections.observableArrayList();

        ArrayList<Trivia> list = triviaClient.getTrivias();
        data.addAll(list);
        
        for (int i = 0; i < colNames.size(); ++i) {
            tabCols[i] = new TableColumn(colNames.get(i));
            tabCols[i].setCellValueFactory(new PropertyValueFactory<>(colNames.get(i)));
            tabCols[i].setCellFactory(TextFieldTableCell.forTableColumn());        
        }
        
        triviaTable.getColumns().addAll((Object [])tabCols);        
        triviaTable.setItems(data);   
    }
    
    public String fixSQLFormat(String str) {
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
}
