package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ViewSeatPopupController {

    @FXML
    private Label hallLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label userLabel;
    
    void setLabels(String hall, String location, String status, String user) {
        hallLabel.setText("Hall: " + hall);
        locationLabel.setText("Location: " + location);
        statusLabel.setText("Status: " + status);
        if (status.equals("Free"))
            userLabel.setText("");
        else 
            userLabel.setText("User ID: " + user);
    }
}
