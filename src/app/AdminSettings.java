package app;

import data.SqliteConnector;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.AdminModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminSettings extends Functionalities {



    //Buttons
    public Button save;

    //TextField
    public TextField name, dni, address, shop, phone, password;
    public Label status;

    //Objects
    private SqliteConnector sqlite;
    private AdminModel adminGet;
    private AdminModel adminSet;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        Propierties defaultProperties = new Propierties(); // Instance

        defaultProperties.newStage("Settings",back_button);


        sqlite = new SqliteConnector();
        adminSet = new AdminModel();

        try {
            sqlite.selectAdmin(adminSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        name.setText(adminSet.getName());
        dni.setText(adminSet.getDni());
        address.setText(adminSet.getAddress());
        shop.setText(adminSet.getShop());
        phone.setText(adminSet.getPhone());
        password.setText(adminSet.getPassword());


        save.setOnMouseClicked(event -> {

            if (!name.getText().isEmpty() && !dni.getText().isEmpty() && !address.getText().isEmpty() && !shop.getText().isEmpty() && !phone.getText().isEmpty() && !password.getText().isEmpty()){

                save.setDisable(true);

                adminGet  = new AdminModel(name.getText(),dni.getText(),address.getText(),shop.getText(),phone.getText(), password.getText());
                try {
                    sqlite.updateAdmin(adminGet);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                status.setText("The settings has been changed!");
                status.setVisible(true);


            } else {

                status.setText("Complete all fields");
                status.setVisible(true);

            }

        });



    }

    @FXML
    protected void statusGone(){

        if (status.isVisible()){

            status.setVisible(false);
            save.setDisable(false);


        }

    }

}
