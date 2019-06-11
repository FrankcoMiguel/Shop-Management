package app;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Stock extends Functionalities {


    //Buttons

    public VBox employee_button, services_button, product_button;

    //Objects



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        Propierties defaultProperties = new Propierties(); // Instance
        defaultProperties.newStage("Homepage",back_button);

        defaultProperties.newStage("Employee",employee_button);
        defaultProperties.newStage("Services",services_button);
        defaultProperties.newStage("Products",product_button);

    }
}
