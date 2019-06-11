package app;

import data.SqliteConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ServicesModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Services extends Functionalities {



    //Buttons
    public Button save;

    //TextFields
    public TextField name, cost;
    public Label status;

    public TableView<ServicesModel> tableService;
    public TableColumn<ServicesModel, Integer> col_id;
    public TableColumn<ServicesModel, String> col_name;
    public TableColumn<ServicesModel, Double> col_cost;

    //Objects
    private SqliteConnector sqlite;
    private ServicesModel service;
    private ObservableList<ServicesModel> servicesList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        Propierties defaultProperties = new Propierties(); // Instance

        defaultProperties.newStage("Stock",back_button);
        servicesList = FXCollections.observableArrayList();

        col_id.setCellValueFactory(new PropertyValueFactory<ServicesModel,Integer>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<ServicesModel,String>("name"));
        col_cost.setCellValueFactory(new PropertyValueFactory<ServicesModel, Double>("cost"));

        if (sqlite == null){

            sqlite = new SqliteConnector();

        }

        try {
            sqlite.selectService(servicesList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tableService.setItems(servicesList);


        save.setOnMouseClicked(event -> {

            if (!name.getText().isEmpty() && !cost.getText().isEmpty()){


                if (sqlite == null){

                    sqlite = new SqliteConnector();

                }

                try {

                    String number =  sqlite.countService();
                    service = new ServicesModel("S"+number,name.getText(),Double.parseDouble(cost.getText()));

                    save.setDisable(true);
                    tableService.getItems().clear();

                    sqlite.insertService(service);
                    sqlite.selectService(servicesList);
                    tableService.setItems(servicesList);

                    name.clear();
                    cost.clear();

                    status.setText("Service added successfully");
                    status.setVisible(true);

                    save.setDisable(false);

                }catch (Exception e){

                    status.setText("Alguno de los campos estuvo incorrecto");
                    status.setVisible(true);

                }


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
