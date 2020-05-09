package ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WarningPopupController {

    @FXML
    private Button exitButton;

    @FXML
    private Label msgLabel;

    @FXML
    void exitButtonClicked(MouseEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    void setMessage(String msg) {
        msgLabel.setText(msg);
    }
}
