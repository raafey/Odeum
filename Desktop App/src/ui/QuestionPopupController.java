package ui;

import domain.Question;
import domain.Trivia;
import domain.TriviaDBClient;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class QuestionPopupController {
    private TriviaDBClient triviaClient;
    private ArrayList<Question> questions;
    private String triviaName;
    private int numQs;
    private int count;

    @FXML
    private TextArea questionField;

    @FXML
    private TextArea aField;

    @FXML
    private TextArea bField;

    @FXML
    private TextArea cField;

    @FXML
    private TextArea dField;

    @FXML
    private ComboBox optBox;

    @FXML
    private Button nextButton;

    @FXML
    private Label qnumLabel;

    @FXML
    void nextButtonClicked(MouseEvent event) throws IOException, SQLException {
        try {
            if (count < numQs) {
                if (!questionField.getText().equals("") || !aField.getText().equals("") || !bField.getText().equals("") || !optBox.getValue().toString().equals("")) {
                    String question = fixSQLFormat(questionField.getText());
                    String ans = optBox.getValue().toString();
                    String a = fixSQLFormat(aField.getText());
                    String b = fixSQLFormat(bField.getText());
                    String c = fixSQLFormat(cField.getText());
                    String d = fixSQLFormat(dField.getText());
                    
                    Question q = new Question("", question, a, b, c, d, ans, "");
                    questions.add(q);
                    
                    questionField.clear();
                    aField.clear();
                    bField.clear();
                    cField.clear();
                    dField.clear();
                    optBox.getSelectionModel().clearSelection();
                    
                    count++;
                    
                    if (count != numQs)
                        qnumLabel.setText("Q:" + (count + 1) + "/" + numQs);
                    else
                    {
                        triviaClient.createTrivia(triviaName, questions);
                        openWarningPopup(triviaName + " created!");
                        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        stage.close();
                    }
                } else
                    openWarningPopup("Please Fill all mandatory fields!");
            } else {}
        } 
        catch (NullPointerException e) {}
    }
    
    public void setTrivia(String triviaName, int numQs) {
        this.triviaName = fixSQLFormat(triviaName);
        this.numQs = numQs;
        qnumLabel.setText("Q:" + (count + 1) + "/" + numQs);
        ObservableList<String> options = FXCollections.observableArrayList("A", "B", "C", "D");
        optBox.setItems(options);
        questionField.setWrapText(true);
        aField.setWrapText(true);
        bField.setWrapText(true);
        cField.setWrapText(true);
        dField.setWrapText(true);        
    }
    
    public QuestionPopupController() throws ClassNotFoundException, SQLException {
        triviaClient = new TriviaDBClient("jdbc:mysql://localhost:3306/odeum", "root", "");
        questions = new ArrayList<>();
        this.count = 0;
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
