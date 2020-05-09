package ui;

import java.io.IOException;
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

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField pwdField;

    @FXML
    private TextField userNameField;

    @FXML
    private Label outputLabel;

    @FXML
    void loginButtonClicked(MouseEvent event){
        if (userNameField.getText().equals("admin") && pwdField.getText().equals("admin")) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("AdminPopup.fxml"));
                Scene newScene = new Scene(parent);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setTitle("Administrator panel");
                stage.setScene(newScene);
                stage.getIcons().add(new Image("Alduin.png"));
                stage.show();
            } catch (IOException e) {e.printStackTrace();}            
        } 
        else {
            outputLabel.setText("Invalid login credentials");
        }
    }
}
