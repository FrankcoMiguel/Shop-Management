package app;

import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Functionalities implements Initializable {

    // Buttons
    public ImageView close_button, minimize_button, back_button;


    // Main Pane
    public BorderPane main_pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Propierties defaultProperties = new Propierties(); //Instance

        defaultProperties.dragApp(main_pane);
        defaultProperties.minimizeApp(minimize_button);




    }
}
