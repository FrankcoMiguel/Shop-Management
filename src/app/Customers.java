package app;


import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;

public class Customers extends Functionalities {

    //Buttons

    public VBox new_customer_button, customers_fee_button, list_customers_button;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        Propierties defaultProperties = new Propierties(); // Instance
        defaultProperties.newStage("Homepage",back_button);
        defaultProperties.newStage("NewCustomer",new_customer_button);
        defaultProperties.newStage("ListCustomers",list_customers_button);
        defaultProperties.newStage("PasswordCustomers",customers_fee_button);

    }
}
