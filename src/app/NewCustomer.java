package app;

import data.SqliteConnector;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CustomerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewCustomer extends Functionalities {



    //Buttons
    public Button save;


    //TextFields and ComboBox
    public TextField name, dni, address, phone;
    public ComboBox<String> sex;
    public Label status;

    //Objects
    private SqliteConnector sqlite;
    private CustomerModel customer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        Propierties defaultProperties = new Propierties(); // Instance
        defaultProperties.newStage("Customers",back_button);

        sex.getItems().setAll("M","F");

        save.setOnMouseClicked(event -> {


            if (!name.getText().isEmpty() && !dni.getText().isEmpty() && !sex.getValue().isEmpty() && !address.getText().isEmpty() && !phone.getText().isEmpty()){

                save.setDisable(true);
                customer = new CustomerModel(name.getText(),dni.getText(),sex.getValue(), address.getText(), phone.getText());
                sqlite = new SqliteConnector();

                try {
                    sqlite.insertCustomer(customer);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                name.clear();
                dni.clear();
                address.clear();
                phone.clear();

                status.setText("Customer added successfully");
                status.setVisible(true);

                save.setDisable(false);



            } else {

                status.setText("Complete all fields");
                status.setVisible(true);

            }

        });


    }

    public void statusGone(){

        if (status.isVisible()){

            status.setVisible(false);

        }

    }




}
