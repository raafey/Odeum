package ui;

import domain.Event;
import domain.Theater;
import domain.EventDBClient;
import domain.Schedule;
import domain.Seat;
import domain.SeatDBClient;
import domain.TheaterDBClient;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TheaterPortalController {
    private Theater theater;
    private EventDBClient eventClient;
    private TheaterDBClient theaterClient;   
    private SeatDBClient seatClient;
    private ArrayList<ImageView> seats;
    
    @FXML
    private TilePane tilePane;
    
    @FXML
    private TabPane tabPane;
    
    @FXML
    private Label theaterNameLabel;

    @FXML
    private TextField nameField;

    @FXML
    private TextField imageField;

    @FXML
    private TextField ratingField;

    @FXML
    private TextField trailerField;

    @FXML
    private TextArea castField;

    @FXML
    private TextArea synopsisField;

    @FXML
    private Button saveButton;
    
    @FXML
    private Button signoutButton;

    @FXML
    private Label createLabel;
    
    @FXML
    private ComboBox genreComboBox;

    @FXML
    private Button refreshButton;
    
    @FXML
    private TableView scheduleTable;

    @FXML
    private Button refereshButtonTwo;

    @FXML
    private ComboBox eventBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox hallBox;

    @FXML
    private Label warningLabel;

    @FXML
    private ComboBox hoursBox;

    @FXML
    private ComboBox minutesBox;

    @FXML
    private Button saveButtonTwo;
    
    @FXML
    private Button eventDeleteButton;

    @FXML
    private Button eventEditButton;
    
    @FXML
    private Button schDeleteButton;

    @FXML
    private Button schEditButton;
    
    @FXML
    private Tab manageSeatingPane;
    
    @FXML
    private Tab manageSchPane;
    
    @FXML
    private Tab manageEventPane;
    
    @FXML
    private Tab createEventPane;    
       
    @FXML
    private Tab homePane;

    @FXML
    private ImageView tabIcon;
    
    @FXML
    private ListView eventList;
    
    @FXML
    private ImageView eventImage;
    
    @FXML
    private Label eventRatingLabel;

    @FXML
    private Label eventNameLabel;

    @FXML
    private Label eventGenreLabel;

    @FXML
    private TextArea eventSynopsisField;
    
    @FXML
    private TextField eventPriceField;
    
    @FXML
    private TableView schEventTable;
    
    @FXML
    private ComboBox selectHallCombobox;

    @FXML
    private ComboBox seatRowBox;

    @FXML
    private ComboBox seatColBox;

    @FXML
    private Button releaseSeatButton;

    @FXML
    private Button viewSeatButton;

    @FXML
    private Button bookSeatButton;

    @FXML
    private Button loadHallButton;
   
    @FXML
    private Button seatRefreshButton;
    
    @FXML
    void seatRefreshButtonClicked(MouseEvent event) throws SQLException {
        try {
            Schedule sch = (Schedule)schEventTable.getSelectionModel().getSelectedItem();
            String plan[][] = seatClient.getSeatingPlan(sch.getId());
            loadSeatingPlan(plan);            
        } catch (NullPointerException e) {}
    }

    @FXML
    void bookSeatButtonClicked(MouseEvent event) throws SQLException, IOException {
        try {
            Schedule sch = (Schedule)schEventTable.getSelectionModel().getSelectedItem();
            
            if (!seatRowBox.getValue().toString().equals("") && !seatColBox.getValue().toString().equals("")) {
                String row = seatRowBox.getValue().toString();
                String col = seatColBox.getValue().toString();
                
                if (seatClient.isAvailable(row, col, sch.getId())) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("bookSeatPopup.fxml"));
                    Parent parent = loader.load();
                    BookSeatPopupController controller = loader.getController();
                    controller.setSeat(row, col, sch.getId());
                    Stage stage = new Stage();
                    stage.setTitle("Book Seat");
                    stage.getIcons().add(new Image("Alduin.png"));
                    stage.setScene(new Scene(parent));
                    stage.show();
                } else {
                    openWarningPopup("Seat: " + row + "-" + col + " is not available!");                    
                }
            }
            
        } catch (NullPointerException e) {}
    }
    
    @FXML
    void releaseSeatButtonClicked(MouseEvent event) throws SQLException, IOException {
        try {
            Schedule sch = (Schedule)schEventTable.getSelectionModel().getSelectedItem();
            
            if (!seatRowBox.getValue().toString().equals("") && !seatColBox.getValue().toString().equals("")) {
                String row = seatRowBox.getValue().toString();
                String col = seatColBox.getValue().toString();
                
                if (seatClient.releaseSeat(row, col, sch.getId()) == false) {
                    openWarningPopup("Seat: " + row + "-" + col + " is already free!");
                } else {
                    seatClient.releaseSeat(row, col, sch.getId());
                    Seat seat = seatClient.getSeat(row, col, sch.getId());
                    seatClient.deleteTicket(seat.getSeatId());
                    String plan[][] = seatClient.getSeatingPlan(sch.getId());
                    loadSeatingPlan(plan);
                }
            }
            
        } catch (NullPointerException e) {}
    }
    
    @FXML
    void schEventTableClicked(MouseEvent event) throws SQLException {
        try {
            Schedule sch = (Schedule)schEventTable.getSelectionModel().getSelectedItem();
            String plan[][] = seatClient.getSeatingPlan(sch.getId());
            loadSeatingPlan(plan);
        } catch (NullPointerException e) {}
    }
   
    @FXML
    void viewSeatButtonClicked(MouseEvent event) throws IOException, SQLException {
        try {
            Schedule sch = (Schedule)schEventTable.getSelectionModel().getSelectedItem();
            
            if (!seatRowBox.getValue().toString().equals("") && !seatColBox.getValue().toString().equals("")) {
                String row = seatRowBox.getValue().toString();
                String col = seatColBox.getValue().toString();
                String hall = sch.getHallName();
                String location = row + "-" + col;
                Seat seat = seatClient.getSeat(row, col, sch.getId());
                
                String status = seat.getIsBooked();
                
                if (status.equals("T")) {
                    status = "Booked";
                } else {
                    status = "Free";
                }
                
                String user = seat.getUserId();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("viewSeatPopup.fxml"));
                Parent parent = loader.load();
                ViewSeatPopupController controller = loader.getController();
                controller.setLabels(hall, location, status, user);
                Stage stage = new Stage();
                stage.setTitle("Seat Details!");
                stage.getIcons().add(new Image("Alduin.png"));
                stage.setScene(new Scene(parent));
                stage.show();                                
            }
            
        } catch (NullPointerException e) {}
    }
    
    @FXML
    void loadHallButtonClicked(MouseEvent event) throws SQLException {
        try {
            String hall = selectHallCombobox.getValue().toString();

            if (hall != null) {
                String hallId = theaterClient.getHallId(theater.getID(), hall);
                populateEventScheduleTable(hallId);
            }
        } catch (NullPointerException e) {}
    }
    
    @FXML
    void eventDeleteButtonClicked(MouseEvent event) throws SQLException {
        try {
            String e = (String)eventList.getSelectionModel().getSelectedItem();
            Event selectedEvent = eventClient.getSelectedEvent(theater.getID(), fixSQLFormat(e));
            String eventId = eventClient.getEventId(fixSQLFormat(selectedEvent.getName()), theater.getTheaterId());
            
            seatClient.deleteEventRow(eventId);
            theaterClient.deleteEventRow(eventId);
            eventClient.deleteRow(eventId);  
            
            populateEventList();
            loadEventHallComboBoxes();
            populateScheduleTable();
            
        } catch (NullPointerException e) {}
    }

    @FXML
    void eventEditButtonClicked(MouseEvent event) throws SQLException, IOException {
        try {
            String e = (String)eventList.getSelectionModel().getSelectedItem();
            Event selectedEvent = eventClient.getSelectedEvent(theater.getID(), fixSQLFormat(e));

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("editEventPopup.fxml"));
            Parent parent = loader.load();
            EditEventPopupController controller = loader.getController();
            controller.setEvent(selectedEvent, theater.getID());
            Stage stage = new Stage();
            stage.setTitle("Edit Event Details");
            stage.getIcons().add(new Image("Alduin.png"));
            stage.setScene(new Scene(parent));
            stage.show();
            
            populateEventList();
            loadEventHallComboBoxes();
            populateScheduleTable();
        } catch (NullPointerException e) {}
    }
    
    @FXML
    void scheduleDeleteButtonClicked(MouseEvent event) throws SQLException {
        try {
            Schedule sch = (Schedule)scheduleTable.getSelectionModel().getSelectedItem();
            seatClient.deleteRow(sch.getId());
            theaterClient.deleteRow(sch.getId());
            populateScheduleTable();
        } catch (NullPointerException e) {}
    }

    @FXML
    void scheduleEditButtonClicked(MouseEvent event) throws SQLException, IOException {
        try {
            Schedule sch = (Schedule)scheduleTable.getSelectionModel().getSelectedItem();
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("editSchPopup.fxml"));
            Parent parent = loader.load();
            EditSchPopupController controller = loader.getController();
            controller.setSchedule(sch, theater);
            Stage stage = new Stage();
            stage.setTitle("Edit Schedule Details");
            stage.getIcons().add(new Image("Alduin.png"));
            stage.setScene(new Scene(parent));
            stage.show();
            
            populateScheduleTable();            
        } catch (NullPointerException e) {}
    }
    
    @FXML
    void createEventPaneSelected(javafx.event.Event event) {
        Image image = new Image(getClass().getResource("Images/scene.png").toExternalForm()); 
        tabIcon.setImage(image);
    }

    @FXML
    void homePaneSelected(javafx.event.Event event) {
        try {
            Image image = new Image(getClass().getResource("Images/cinema.png").toExternalForm()); 
            tabIcon.setImage(image);
        } catch (NullPointerException e) {}
    }

    @FXML
    void manageEventPaneSelected(javafx.event.Event event) {
        Image image = new Image(getClass().getResource("Images/drama.png").toExternalForm()); 
        tabIcon.setImage(image);
    }

    @FXML
    void manageSchPaneSelected(javafx.event.Event event) {
        Image image = new Image(getClass().getResource("Images/calendar.png").toExternalForm()); 
        tabIcon.setImage(image);  
    }

    @FXML
    void manageSeatingPaneSelected(javafx.event.Event event) {
        Image image = new Image(getClass().getResource("Images/seats.png").toExternalForm()); 
        tabIcon.setImage(image);
    }
    
    @FXML
    void refreshButtonTwoClicked(MouseEvent event) throws SQLException {
        populateScheduleTable();
    }

    @FXML
    void saveButtonTwoClicked(MouseEvent event) throws SQLException {
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
                
                if (theaterClient.isSlotAvailable(date, time, hallId)) {
                    theaterClient.insertSchedule(eventId, hallId, price, date, time);
                    String schId = theaterClient.getSchID(date, time, hallId);
                    seatClient.createSeatingPlan(schId);

                    warningLabel.setText("");
                    populateScheduleTable();
                } else {
                    warningLabel.setText(hallBox.getValue() + " is unavailable on " + date + " " + time);
                }
            }
            catch (NumberFormatException e) {
                warningLabel.setText("Please enter the price in float");
            }
        }
    }
    
    @FXML
    void refreshButtonClicked(MouseEvent event) throws SQLException {
        populateEventList();
        loadEventHallComboBoxes();
        populateScheduleTable();
    }
    
    @FXML
    void saveButtonClicked(MouseEvent event) throws SQLException {
        System.out.println("ID: " + theater.getID());
        if (nameField.getText().equals("") || imageField.getText().equals("") || ratingField.getText().equals("") || trailerField.getText().equals("") || castField.getText().equals("") || synopsisField.getText().equals("") || genreComboBox.getValue() == null) {
            createLabel.setText("Please fill all fields!");
        }
        else {
            if (!eventClient.isEventPresent(fixSQLFormat(nameField.getText()), theater.getID())) {
                try {
                    float rating = Float.valueOf(ratingField.getText());  

                    Event newEvent = new Event("", fixSQLFormat(nameField.getText()), genreComboBox.getValue().toString(), ratingField.getText(), "", fixSQLFormat(synopsisField.getText()), fixSQLFormat(trailerField.getText()), fixSQLFormat(castField.getText()) ,theater.getID() ,fixSQLFormat(imageField.getText())); 

                    eventClient.insertEvent(newEvent);
                    nameField.setText("");
                    createLabel.setText("");
                    imageField.setText("");
                    ratingField.setText("");
                    trailerField.setText("");
                    castField.setText("");
                    castField.setWrapText(true);
                    synopsisField.setText("");
                    synopsisField.setWrapText(true);
                    genreComboBox.getSelectionModel().clearSelection();


                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("eventPopup.fxml"));
                    Parent parent = loader.load();
                    EventPopupController controller = loader.getController();
                    controller.setEvent(newEvent);
                    Stage stage = new Stage();
                    stage.setTitle("Event Successfully Created!");
                    stage.getIcons().add(new Image("Alduin.png"));
                    stage.setScene(new Scene(parent));
                    stage.show();
                    constructComboInput();
                    populateEventList();
                } catch (NumberFormatException ex) {
                    createLabel.setText("Please enter a number in Rating Field!");                
                } catch (IOException ex) {}
            } else {
                createLabel.setText(nameField.getText() + " already exists!");
            }
        }
    } 
        
    @FXML
    void signoutButtonClicked(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("theaterLogin.fxml"));
            Scene newScene = new Scene(parent);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Sign Up form");
            stage.setScene(newScene);
            stage.getIcons().add(new Image("Alduin.png"));
            stage.show();
        } catch (IOException e) {}          
    }    
    
    @FXML
    void eventListClicked(MouseEvent event) throws SQLException {
        Object obj = eventList.getSelectionModel().getSelectedItem();
        if (obj != null) {
            String eName = (String)obj;
            Event e = eventClient.getSelectedEvent(theater.getTheaterId(), fixSQLFormat(eName));
            eventGenreLabel.setText(e.getGenre());
            eventSynopsisField.setWrapText(true);
            eventSynopsisField.setText(e.getSynopsis());
            eventNameLabel.setText(e.getName());
            eventRatingLabel.setText(e.getRating());
            Image image = new Image(e.getImageUrl()); 
            eventImage.setImage(image);   
        }
    }
    
    void populateEventScheduleTable(String hallId) throws SQLException {
        schEventTable.getColumns().clear();
        schEventTable.getItems().clear();
        
        ArrayList<String> colNames = new ArrayList<>();
        colNames.add("eventName");
        colNames.add("date");
        colNames.add("time");        

        TableColumn tabCols[] = new TableColumn[colNames.size()];
        ObservableList<Schedule> data = FXCollections.observableArrayList();
        ArrayList<Schedule> list = theaterClient.getTheaterSchedulesTwo(theater.getTheaterId(), hallId);
        
        if (list.size() > 0) {   
            for (int i = 0; i < list.size(); ++i) {
                Schedule sch = list.get(i);
                sch.setHallName(theaterClient.getHallName(list.get(i).getHallName()));
                sch.setEventName(eventClient.getEventName(list.get(i).getEventName()));
                list.set(i, sch);
            }


            data.addAll(list);

            for (int i = 0; i < colNames.size(); ++i) {
                tabCols[i] = new TableColumn(colNames.get(i));
                tabCols[i].setCellValueFactory(new PropertyValueFactory<>(colNames.get(i)));
                tabCols[i].setCellFactory(TextFieldTableCell.forTableColumn());        
            }

            schEventTable.getColumns().addAll((Object [])tabCols);        
            schEventTable.setItems(data);
        }
    }
    
    void populateScheduleTable() throws SQLException {
        scheduleTable.getColumns().clear();
        scheduleTable.getItems().clear();
        
        ArrayList<String> colNames = new ArrayList<>();
        colNames.add("Id");
        colNames.add("date");
        colNames.add("time");        
        colNames.add("price");
        colNames.add("eventName");
        colNames.add("hallName");
        TableColumn tabCols[] = new TableColumn[colNames.size()];
        ObservableList<Schedule> data = FXCollections.observableArrayList();
        ArrayList<Schedule> list = theaterClient.getTheaterSchedules(theater.getTheaterId());
        
        for (int i = 0; i < list.size(); ++i) {
            Schedule sch = list.get(i);
            sch.setHallName(theaterClient.getHallName(list.get(i).getHallName()));
            sch.setEventName(eventClient.getEventName(list.get(i).getEventName()));
            list.set(i, sch);
        }
        
        data.addAll(list);
        
        for (int i = 0; i < colNames.size(); ++i) {
            tabCols[i] = new TableColumn(colNames.get(i));
            tabCols[i].setCellValueFactory(new PropertyValueFactory<>(colNames.get(i)));
            tabCols[i].setCellFactory(TextFieldTableCell.forTableColumn());        
        }
        
        scheduleTable.getColumns().addAll((Object [])tabCols);        
        scheduleTable.setItems(data);        
    }
    
    public void setTheater(Theater theater) throws SQLException {
        this.theater = theater;
        theaterNameLabel.setText(theater.getName());
        populateEventList();
        populateScheduleTable();
        constructComboInput();
        
        seats = new ArrayList<>();
        
        String status[][] = new String[7][14];
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 14; ++j) {
                status[i][j] = "T";
            }
        }
        
        
        loadSeatingPlan(status);
        tilePane.setVgap(12);
        tilePane.setHgap(12);
        setHallComboBox();
    }

    public void constructComboInput() throws SQLException {
        ObservableList<String> options = FXCollections.observableArrayList("Horror", "Animated", "3D", "Adventure","Action",
        "Comedy",
        "Drama",
        "Science Fiction",
        "Historical"
        );
        genreComboBox.setItems(options);
        
        ObservableList<String> opt2 = FXCollections.observableArrayList("01", "02", "03", "04", "05", "06"
                ,"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24");
        hoursBox.setItems(opt2);
        
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
        
        loadEventHallComboBoxes();
        
        ObservableList<String> opt6 = FXCollections.observableArrayList("1", "2", "3", "5", "6", "7");
        seatRowBox.setItems(opt6);
        
        ObservableList<String> opt7 = FXCollections.observableArrayList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N");       
        seatColBox.setItems(opt7);
    }
    
    public void loadEventHallComboBoxes() throws SQLException {
        ObservableList<String> opt5 = FXCollections.observableArrayList();
        opt5.addAll(theaterClient.getHalls(theater.getTheaterId()));
        hallBox.setItems(opt5);
        
        ObservableList<String> opt6 = FXCollections.observableArrayList();
        ArrayList<String> events = eventClient.getEventNames(theater.getTheaterId());
        
        for (int i = 0; i < events.size(); ++i) {
            opt6.add(events.get(i));
        }
        eventBox.setItems(opt6);
    }
   
    public TheaterPortalController() throws ClassNotFoundException, SQLException {
        eventClient = new EventDBClient("jdbc:mysql://localhost:3306/odeum", "root", "");
        theaterClient = new TheaterDBClient("jdbc:mysql://localhost:3306/odeum", "root", "");
        seatClient = new SeatDBClient("jdbc:mysql://localhost:3306/odeum", "root", "");
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

    public void populateEventList() throws SQLException {
        ArrayList<String> eventNames = eventClient.getEventNames(theater.getTheaterId());
        ObservableList<String> items = FXCollections.observableArrayList(eventNames);
        eventList.setItems(items);
    }
    
    public void loadSeatingPlan(String status[][]) {       
        int nRows = 7;
        int nCols = 14;
        
        String[] letterImagePaths = {"Images/num_chars/a.png", "Images/num_chars/b.png", "Images/num_chars/c.png", "Images/num_chars/d.png", "Images/num_chars/e.png", "Images/num_chars/f.png", "Images/num_chars/g.png", "Images/num_chars/h.png", "Images/num_chars/i.png", "Images/num_chars/j.png", "Images/num_chars/k.png", "Images/num_chars/l.png", "Images/num_chars/m.png", "Images/num_chars/n.png"}; 
        
        ArrayList<Image> letters = new ArrayList<>();
        
        for (int i = 0; i < letterImagePaths.length; ++i) {
            Image img = new Image(getClass().getResource(letterImagePaths[i]).toExternalForm());
            letters.add(img);
        }
        
        tilePane.getChildren().clear();
        seats.clear();
        
        Image unbooked = new Image(getClass().getResource("Images/armchair.png").toExternalForm());
        Image booked = new Image(getClass().getResource("Images/armchair-mono.png").toExternalForm());
        
        for (int i = 0; i < nCols; ++i) {
            ImageView viewTwo = new ImageView(letters.get(i));
            viewTwo.setFitHeight(43);
            viewTwo.setFitWidth(43);
            viewTwo.setPreserveRatio(true);
            tilePane.getChildren().add(viewTwo);
        }
        
        for (int i = 0; i < nRows; ++i) {           
            for (int j = 0; j < nCols; ++j) {                
                ImageView newView = new ImageView();               
                
                if (status[i][j].equals("T")) {
                    newView.setImage(booked);
                } else if (status[i][j].equals("F")) {
                    newView.setImage(unbooked);
                }
                
                newView.setFitHeight(43);
                newView.setFitWidth(43);
                newView.setPreserveRatio(true);
                tilePane.getChildren().add(newView);
                seats.add(newView);         
            }
        }
    }
    
    public void setHallComboBox() throws SQLException {
        ObservableList<String> opt5 = FXCollections.observableArrayList();
        opt5.addAll(theaterClient.getHalls(theater.getTheaterId()));
        selectHallCombobox.setItems(opt5);
    }
    
    public void openWarningPopup(String msg) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("warningPopup.fxml"));
        Parent parent = loader.load();
        WarningPopupController controller = loader.getController();
        controller.setMessage(msg);
        Stage stage = new Stage();
        stage.setTitle("Warning!");
        stage.getIcons().add(new Image("Alduin.png"));
        stage.setScene(new Scene(parent));
        stage.show();        
    }
}
