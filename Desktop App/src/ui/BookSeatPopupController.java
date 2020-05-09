package ui;

import domain.Seat;
import domain.SeatDBClient;
import domain.UserDBClient;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BookSeatPopupController {
    private String row;
    private String col;
    private String schId;
    private SeatDBClient seatClient;
 
    @FXML
    private TextField userIDField;

    @FXML
    private Button bookButton;

    @FXML
    private Label warningLabel;

    @FXML
    void bookButtonClicked(MouseEvent event) throws ClassNotFoundException, SQLException, IOException {
        if (userIDField.getText().equals("")) {
            warningLabel.setText("Please enter a userID");
        } else {
            String userID = userIDField.getText();
            
            try {
                int value = Integer.valueOf(userID);
                
                if (new UserDBClient("jdbc:mysql://localhost:3306/odeum", "root", "").isUserPresent(userID)) {
                    seatClient.bookSeat(row, col, schId, userID);
                    Seat seat = seatClient.getSeat(row, col, schId);
                    String eventId = seatClient.getEventId(seat.getSeatId());
                    seatClient.createTicket(seat.getSeatId(), eventId);
                                     
                    openWarningPopup("Seat: " + row + "-" + col + " booked succesfully!");
                    
                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();    
                    stage.close();

                } else {
                    warningLabel.setText("UserID not found!");
                }
                                
            } catch (NumberFormatException e) {
                warningLabel.setText("Invalid userID entered!");
            }
        }
    }

    public void setSeat(String row, String col, String schId) {
        this.row = row;
        this.col = col;
        this.schId = schId;
    }
    
    public BookSeatPopupController() throws ClassNotFoundException, SQLException {
        seatClient = new SeatDBClient("jdbc:mysql://localhost:3306/odeum", "root", "");        
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
