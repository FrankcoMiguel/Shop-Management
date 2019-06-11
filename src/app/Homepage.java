package app;

import data.SqliteConnector;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.AdminModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Homepage extends Functionalities {


    //Buttons

    public VBox cashier_button, stock_button ,customers_button;
    public HBox settings_button;
    public ImageView close_button;

    public Label shop_name;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        SqliteConnector sqliteConnector = new SqliteConnector();
        AdminModel admin = new AdminModel();

        try {
            sqliteConnector.selectAdmin(admin);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //shop_name.setText(admin.getShop());

        Propierties defaultProperties = new Propierties(); // Instance
        defaultProperties.newStage("FullExit",close_button);

        // cashier action
        defaultProperties.newStage("MakeSell",cashier_button);


        // stock action
        defaultProperties.newStage("PasswordStock",stock_button);

        // customer action
        defaultProperties.newStage("Customers",customers_button);

        //settings action
        defaultProperties.newStage("Password",settings_button);


    }
}
