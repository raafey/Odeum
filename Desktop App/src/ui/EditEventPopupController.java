package ui;

import domain.Event;
import domain.EventDBClient;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditEventPopupController {
    
    private Event selectedEvent;
    private EventDBClient eventClient;
    private String theaterId;

    @FXML
    private TextField nameField;

    @FXML
    private TextField imageField;

    @FXML
    private TextField ratingField;

    @FXML
    private TextField trailerField;

    @FXML
    private ComboBox genreComboBox;

    @FXML
    private TextArea castField;

    @FXML
    private TextArea synopsisField;

    @FXML
    private Button saveButton;
    
    @FXML
    private Label createLabel;

    @FXML
    void saveButtonClicked(MouseEvent event) throws SQLException {
        if (nameField.getText().equals("") || imageField.getText().equals("") || ratingField.getText().equals("") || trailerField.getText().equals("") || castField.getText().equals("") || synopsisField.getText().equals("") || genreComboBox.getValue() == null) {
            createLabel.setText("Please fill all fields!");
        }
        else {
            try {
                float rating = Float.valueOf(ratingField.getText());  
              
                Event newEvent = new Event(selectedEvent.getEventId(), fixSQLFormat(nameField.getText()), genreComboBox.getValue().toString(), ratingField.getText(), "", fixSQLFormat(synopsisField.getText()), fixSQLFormat(trailerField.getText()), fixSQLFormat(castField.getText()) ,theaterId ,fixSQLFormat(imageField.getText())); 
                
                eventClient.updateEvent(newEvent);
               
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                
                stage.close();
                
                createLabel.setText("");
            } catch (NumberFormatException ex) {
                createLabel.setText("Please enter a number in Rating Field!");                
            }
        }        
    }
    
    void setEvent(Event event, String theaterID) {
        this.theaterId = theaterID;
        selectedEvent = event;
        nameField.setText(selectedEvent.getName());
        imageField.setText(selectedEvent.getImageUrl());
        ratingField.setText(selectedEvent.getRating());
        trailerField.setText(selectedEvent.getTrailerUrl());
        castField.setText(selectedEvent.getCast());
        synopsisField.setText(selectedEvent.getSynopsis());
        castField.setWrapText(true);
        synopsisField.setWrapText(true);
        
        ObservableList<String> options = FXCollections.observableArrayList("Horror", 
        "Animated", 
        "3D", 
        "Adventure",
        "Action",
        "Comedy",
        "Drama",
        "Science Fiction",
        "Historical"
        );
        
        int idx = options.indexOf((String) selectedEvent.getGenre());
        genreComboBox.setItems(options);

        genreComboBox.getSelectionModel().select(idx);
    }
    
    public EditEventPopupController() throws ClassNotFoundException, SQLException {
        eventClient = new EventDBClient("jdbc:mysql://localhost:3306/odeum", "root", "");
    }
    
    String fixSQLFormat(String str) {        
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