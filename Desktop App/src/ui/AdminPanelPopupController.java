package ui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdminPanelPopupController {

    @FXML
    private Button manageUserButton;

    @FXML
    private Button manageTriviaButton;

    @FXML
    private Button manageArticleButton;

    @FXML
    void manageArticleButtonClicked(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("articlePopup.fxml"));
            Scene newScene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Manage Articles");
            stage.setScene(newScene);
            stage.getIcons().add(new Image("Alduin.png"));
            stage.show();
        } catch (IOException e) {e.printStackTrace();}  
    }

    @FXML
    void manageTriviaButtonClicked(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("triviaPopup.fxml"));
            Scene newScene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Manage Trivias");
            stage.setScene(newScene);
            stage.getIcons().add(new Image("Alduin.png"));
            stage.show();
        } catch (IOException e) {e.printStackTrace();}  
    }

    @FXML
    void manageUserButtonClicked(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("adminPortal.fxml"));
            Scene newScene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Manage Users");
            stage.setScene(newScene);
            stage.getIcons().add(new Image("Alduin.png"));
            stage.show();
        } catch (IOException e) {e.printStackTrace();}  
    }
}
