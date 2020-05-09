package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
       // Parent root = FXMLLoader.load(getClass().getResource("theaterLogin.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setTitle("Odeum login Screen");
        stage.getIcons().add(new Image("Alduin.png"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public static void main(String args[]) {
        launch(args);
    }
}
