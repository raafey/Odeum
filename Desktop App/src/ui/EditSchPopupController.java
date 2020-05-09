package ui;

import domain.EventDBClient;
import domain.Schedule;
import domain.Theater;
import domain.TheaterDBClient;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditSchPopupController {
    private Schedule schedule;
    private TheaterDBClient theaterClient;
    private EventDBClient eventClient;
    private Theater theater;
    
    @FXML
    private TextField eventPriceField;
    
    @FXML
    private ComboBox eventBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox hallBox;

    @FXML
    private ComboBox hoursBox;

    @FXML
    private ComboBox minutesBox;

    @FXML
    private Button saveButton;
    
    @FXML
    private Label warningLabel;

    @FXML
    void saveButtonClicked(MouseEvent event) throws SQLException {
        if (eventBox.getValue() == null || hallBox.getValue() == null || datePicker.getValue() == null || hoursBox.getValue() == null || minutesBox.getValue() == null || eventPriceField.getText() == null) {
            warningLabel.setText("Please select all fields!");
        } else {
            try {
                double rating = Double.valueOf(eventPriceField.getText());  
                String time = hoursBox.getValue().toString() + ":" + minutesBox.getValue().toString();
                String hallId = theaterClient.getHallId(theater.getTheaterId(), hallBox.getValue().toString());
                String date = datePicker.getValue().toString();
                String eventId = eventClient.getEventId(fixSQLFormat(eventBox.getValue().toString()), theater.getTheaterId());
                String price = eventPriceField.getText();
            
                Schedule sch = new Schedule(schedule.getId(), date, time, price, eventId, hallId);
                
                if (theaterClient.isSlotAvailable(date, time, hallId)) {
                    theaterClient.updateSchedule(sch);

                    warningLabel.setText("");

                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();    
                    stage.close();
                } else {
                    warningLabel.setText(hallBox.getValue() + " is unavailable on " + date + " " + time);
                }
            } catch (NumberFormatException e) {
                warningLabel.setText("Please enter the price in float");
            }
        }
    }
    
    public EditSchPopupController() throws ClassNotFoundException, SQLException {
        eventClient = new EventDBClient("jdbc:mysql://localhost:3306/odeum", "root", "");
        theaterClient = new TheaterDBClient("jdbc:mysql://localhost:3306/odeum", "root", "");
    }

    void setSchedule(Schedule sch, Theater theater) throws SQLException {
        this.schedule = sch;       
        this.theater = theater;
        
        constructComboInput();
    }
    
    public void constructComboInput() throws SQLException {        
        ObservableList<String> opt2 = FXCollections.observableArrayList("01", "02", "03", "04", "05", "06"
                ,"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24");
        hoursBox.setItems(opt2);
        
        String time[] = schedule.getTime().split(":");
        
        int idx = opt2.indexOf((String) time[0]);
        hoursBox.getSelectionModel().select(idx);
        
        ArrayList<String> opt3 = new ArrayList<>();
        
        for (int i = 0; i < 60; ++i) {
            if (i < 10) {
                opt3.add("0" + Integer.toString(i));
            } else {
                opt3.add(Integer.toString(i));
            }
        }
        
        ObservableList<String> opt4 = FXCollections.observableArrayList();
        opt4.addAll(opt3);
        minutesBox.setItems(opt4);
        
        idx = opt3.indexOf((String) time[1]);
        minutesBox.getSelectionModel().select(idx);
        
        loadEventHallComboBoxes();
        
        eventPriceField.setText(schedule.getPrice());
    }
        
    void loadEventHallComboBoxes() throws SQLException {
        ObservableList<String> opt5 = FXCollections.observableArrayList();
        opt5.addAll(theaterClient.getHalls(theater.getTheaterId()));
        hallBox.setItems(opt5);
        
        int idx = opt5.indexOf((String) schedule.getHallName());
        hallBox.getSelectionModel().select(idx);
        
        ObservableList<String> opt6 = FXCollections.observableArrayList();
        ArrayList<String> events = eventClient.getEventNames(theater.getTheaterId());
        
        for (int i = 0; i < events.size(); ++i) {
            opt6.add(events.get(i));
        }
        eventBox.setItems(opt6);
        
        idx = opt6.indexOf((String) schedule.getEventName());
        eventBox.getSelectionModel().select(idx);
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