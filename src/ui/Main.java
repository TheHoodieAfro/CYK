package ui;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class for the CYK project
 * @author Usuario
 * @extends Application
 */
public class Main extends Application {
   
    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
   
   
    /**
     * Method to initialize the GUI
     * @param stage
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CYK.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Machine");
        stage.setScene(scene);
        
        stage.show();
    }
}
