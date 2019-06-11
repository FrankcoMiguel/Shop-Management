package app;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Settings extends Functionalities {



    //Buttons

    public VBox admin_button, backup_button;

    //Objects


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        Propierties defaultProperties = new Propierties(); // Instance
        defaultProperties.newStage("Homepage",back_button);
        defaultProperties.newStage("AdminSettings",admin_button);


    }
}
