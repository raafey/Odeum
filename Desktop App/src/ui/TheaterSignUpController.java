package ui;

import domain.Theater;
import domain.TheaterDBClient;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TheaterSignUpController {
    
    private TheaterDBClient client;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField pwdField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField hallField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField contactField;

    @FXML
    private Button signupButton;

    @FXML
    private Label label;

    @FXML
    void signupClicked(MouseEvent event) throws SQLException, IOException {
        if (nameField.getText().equals("") || usernameField.getText().equals("") || pwdField.getText().equals("") || addressField.getText().equals("") || hallField.getText().equals("") || cityField.getText().equals("") || contactField.getText().equals("")) {
            label.setText("Please fill all fields!");
        } else {
            try {
                int numHalls = Integer.parseInt(hallField.getText());
                
                if (!client.isUsernameTaken(usernameField.getText())) {
                    Parent parent = FXMLLoader.load(getClass().getResource("theaterLoginSuccess.fxml"));
                    Scene newScene = new Scene(parent);
                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stage.setTitle("Sign Up successful");
                    stage.setScene(newScene);
                    stage.getIcons().add(new Image("Alduin.png"));
                    stage.show();
                    
                    Theater theater = new Theater("", nameField.getText(), hallField.getText(), addressField.getText(), cityField.getText(), contactField.getText(), usernameField.getText(), pwdField.getText());
                    client.insertTheater(theater);
                    client.insertHall(usernameField.getText());
                }
                else
                    label.setText("Username not available!"); 
            
            } catch (NumberFormatException ex) {
                label.setText("Please enter an integer value for Number of Halls!");
            }
        }
    }
    
    public TheaterSignUpController() throws ClassNotFoundException, SQLException {
        client = new TheaterDBClient("jdbc:mysql://localhost:3306/odeum", "root", "");
    }
}
