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

public class TheaterLoginSuccessController {

    @FXML
    private Button returnButton;

    @FXML
    void returnButtonClicked(MouseEvent event) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("TheaterLogin.fxml"));
                Scene newScene = new Scene(parent);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setTitle("Theater Portal");
                stage.setScene(newScene);
                stage.getIcons().add(new Image("Alduin.png"));
                stage.show();
            } catch (IOException e) {e.printStackTrace();}  
    }
}
