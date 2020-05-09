package ui;

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

public class TheaterLoginController {
    private TheaterDBClient theaterClient;
    
    @FXML
    private TextField usernameField;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    private PasswordField pwdField;

    @FXML
    private Label label;

    @FXML
    void signInClicked(MouseEvent event) throws SQLException {
        String msg = theaterClient.checkLoginCreds(usernameField.getText(), pwdField.getText());
        
        if (msg.equals("Pass_OK | User_OK")) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("theaterPortal.fxml"));
                Parent parent = loader.load();
                TheaterPortalController controller = loader.getController();
                
                controller.setTheater(theaterClient.getTheaterDetails(usernameField.getText()));
                
                Scene newScene = new Scene(parent);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setTitle("Theater Portal");
                stage.setScene(newScene);
                stage.getIcons().add(new Image("Alduin.png"));
                stage.show();
            } catch (IOException e) {e.printStackTrace();}  
        } else if (msg.equals("Pass_NOT_OK | User_OK")) {
            label.setText("Invalid password entered!");
        } else if (msg.equals("Pass_NOT_OK | User_NOT_OK")) {
            label.setText("Invalid password and username entered!");
        }
    }

    @FXML
    void signUpClicked(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("theaterSignUp.fxml"));
            Scene newScene = new Scene(parent);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Sign Up form");
            stage.setScene(newScene);
            stage.getIcons().add(new Image("Alduin.png"));
            stage.show();
        } catch (IOException e) {e.printStackTrace();}  
    }
    
    public TheaterLoginController() throws ClassNotFoundException, SQLException {
        theaterClient = new TheaterDBClient("jdbc:mysql://localhost:3306/odeum", "root", "");
    }
}
