package ui;

import domain.Theater;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import domain.UserDBClient;
import domain.TheaterDBClient;
import domain.User;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn.CellEditEvent;

public class AdminPortalController {
    
    private UserDBClient userClient;
    private TheaterDBClient theaterClient;

    @FXML
    private TableView table = new TableView();

    @FXML
    private RadioButton radioUser;

    @FXML
    private ToggleGroup userType;

    @FXML
    private RadioButton radioTheater;

    public AdminPortalController() throws ClassNotFoundException, SQLException {
        this.userClient = new UserDBClient("jdbc:mysql://localhost:3306/odeum", "root", "");
        this.theaterClient = new TheaterDBClient("jdbc:mysql://localhost:3306/odeum", "root", "");
    }

    @FXML
    void theaterClicked(MouseEvent event) throws SQLException {
        table.setEditable(true);
        table.getColumns().clear();
        table.getItems().clear();
        
        String[] colNames = theaterClient.getColumnNames();
        TableColumn tabCols[] = new TableColumn[colNames.length];
        
        ObservableList<Theater> data = FXCollections.observableArrayList();
        data.addAll(theaterClient.getAllTheaters());
        
        for (int i = 0; i < colNames.length; ++i) {
            tabCols[i] = new TableColumn(colNames[i]);
            tabCols[i].setCellValueFactory(new PropertyValueFactory<>(colNames[i]));
            tabCols[i].setCellFactory(TextFieldTableCell.forTableColumn());        
            
            tabCols[i].setOnEditCommit(new EventHandler<CellEditEvent<Theater, String>>() {
                @Override
                public void handle(CellEditEvent<Theater, String> t) {
                    int row = t.getTablePosition().getRow();
                    int col = t.getTablePosition().getColumn();
                    try {
                        theaterClient.updateValue(row, col, t.getNewValue());
                    } catch (SQLException ex) {

                    }
                    System.out.println("(Row: " + row + ", Col: " + col + ")");
                }
            });
        }
        
        table.getColumns().addAll((Object [])tabCols);        
        table.setItems(data);
    }

    @FXML
    void userClicked(MouseEvent event) throws SQLException {
        table.setEditable(true);
        table.getColumns().clear();
        table.getItems().clear();
        
        String[] colNames = userClient.getColumnNames();
        TableColumn tabCols[] = new TableColumn[colNames.length];
        
        ObservableList<User> data = FXCollections.observableArrayList();
        data.addAll(userClient.getAllUsers());
        
        for (int i = 0; i < colNames.length; ++i) {
            tabCols[i] = new TableColumn(colNames[i]);
            tabCols[i].setCellValueFactory(new PropertyValueFactory<>(colNames[i]));
            tabCols[i].setCellFactory(TextFieldTableCell.forTableColumn());        
            
            tabCols[i].setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {
                @Override
                public void handle(CellEditEvent<User, String> t) {
                    int row = t.getTablePosition().getRow();
                    int col = t.getTablePosition().getColumn();
                    try {
                        userClient.updateValue(row, col, t.getNewValue());
                    } catch (SQLException ex) {
 
                    }
                    System.out.println("(Row: " + row + ", Col: " + col + ")");
                }
            });
        }
        
        table.getColumns().addAll((Object [])tabCols);        
        table.setItems(data);
    }
}

