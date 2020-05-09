package ui;

import domain.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EventPopupController {
    
    private Event currEvent;

    @FXML
    private ImageView poster;

    @FXML
    private Label titleField;

    @FXML
    private Label ratingField;

    @FXML
    private Label genreField;

    @FXML
    private TextArea synopsisField;

    public void setEvent(Event newEvent) {
        this.currEvent = newEvent;
        titleField.setText(currEvent.getName());
        ratingField.setText("Rating: " + currEvent.getRating());
        genreField.setText("Genre: " + currEvent.getGenre());
        synopsisField.setText(currEvent.getSynopsis());
        Image img = new Image(currEvent.getImageUrl());
        poster.setImage(img);
    }
}
