package app;

import data.SqliteConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CustomerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListCustomers extends Functionalities {


    //Table
    public TableView<CustomerModel> tableCustomer;
    public TableColumn<CustomerModel,Integer> col_id;
    public TableColumn<CustomerModel,String> col_name;
    public TableColumn<CustomerModel,String> col_dni;
    public TableColumn<CustomerModel,String> col_sex;
    public TableColumn<CustomerModel,String> col_phone;
    public TableColumn<CustomerModel,String> col_address;

    //Objects
    private SqliteConnector sqlite;

    private ObservableList<CustomerModel> customer;
    public ComboBox<Integer> choose_code;
    public ComboBox<String> choose_column, changesCB;
    public TextField changes;
    public VBox edit,delete;
    public HBox changesCont, changesCbCont;
    public Label status;

    private ObservableList<Integer> customers_code;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        sqlite = new SqliteConnector();

        col_id.setCellValueFactory(new PropertyValueFactory<CustomerModel, Integer>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<CustomerModel, String>("name"));
        col_dni.setCellValueFactory(new PropertyValueFactory<CustomerModel, String>("dni"));
        col_sex.setCellValueFactory(new PropertyValueFactory<CustomerModel, String>("sex"));
        col_phone.setCellValueFactory(new PropertyValueFactory<CustomerModel, String>("phone"));
        col_address.setCellValueFactory(new PropertyValueFactory<CustomerModel, String>("address"));

        customers_code = FXCollections.observableArrayList();

        try {
            sqlite.selectCustomerCodes(customers_code);
        } catch (Exception e){
            System.out.println("Error al cargar los codigos de los clientes");
        }

        choose_code.getItems().setAll(customers_code);

        choose_column.getItems().addAll("Nombre","Cédula","Sexo","Telefono","Dirección");
        changesCB.getItems().addAll("M","F");

        Propierties defaultProperties = new Propierties(); // Instance

        defaultProperties.newStage("Customers",back_button);

        customer = FXCollections.observableArrayList();

        try {
            sqlite.selectCustomer(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tableCustomer.setItems(customer);


        edit.setOnMouseClicked(event -> {

            try {

                String column = null;
                String finalChanges = null;

                switch (choose_column.getValue()){

                    case "Nombre":
                        column = "name";
                        break;

                    case "Cédula":
                        column = "dni";
                        break;

                    case "Sexo":
                        column = "sex";
                        break;

                    case "Telefono":
                        column = "phone";
                        break;

                    case "Dirección":
                        column = "address";
                        break;


                    default:
                        break;


                }

                if (changesCbCont.isVisible()){

                    finalChanges = changesCB.getValue();

                } else if (changesCont.isVisible()) {

                    finalChanges = changes.getText();

                }


                sqlite.updateCustomer(column,finalChanges,choose_code.getValue().toString());

                tableCustomer.getItems().clear();
                sqlite.selectCustomer(customer);
                tableCustomer.setItems(customer);

                status.setText("El cliente ha sido actualizado!");
                status.setVisible(true);
                changes.clear();


            } catch (Exception e){

                System.out.println("Error al actualizar cliente" + e);
                status.setText("Error al actualizar");
                status.setVisible(true);

            }


        });

        delete.setOnMouseClicked(event -> {

            try {

                sqlite.deleteCustomer(choose_code.getValue().toString());
                tableCustomer.getItems().clear();
                sqlite.selectCustomer(customer);
                tableCustomer.setItems(customer);

                choose_column.setDisable(true);
                customers_code.clear();

                if (changesCont.isVisible()){

                    changesCont.setVisible(false);

                } else if (changesCbCont.isVisible()){

                    changesCbCont.setVisible(false);

                }

                try{

                    sqlite.selectCustomerCodes(customers_code);
                    choose_code.getItems().setAll(customers_code);


                } catch (Exception e){

                    status.setText("No hay clientes");
                    status.setVisible(true);

                }

                status.setText("El cliente ha sido eliminado!");
                status.setVisible(true);

                delete.setDisable(true);



            } catch (Exception e){

                status.setText("Error al eliminar cliente");
                status.setVisible(true);

            }

        });

    }

    public void checkCode(){

        if (status.isVisible()){

            status.setVisible(false);

        }

        if (!choose_code.getItems().isEmpty()){

            delete.setDisable(false);
            choose_column.setDisable(false);


        } else {

            delete.setDisable(true);
            choose_column.setDisable(true);

        }

    }

    public void checkColumn(){

        if (status.isVisible()){

            status.setVisible(false);

        }

        if (!choose_column.getValue().equals("Sexo")){

            changesCont.setVisible(true);
            changesCbCont.setVisible(false);

        } else {

            changesCont.setVisible(false);
            changesCbCont.setVisible(true);

        }

    }

    public void checkChanges(){

        if (!changes.getText().isEmpty()){

            edit.setDisable(false);

        } else {

            edit.setDisable(true);

        }

    }

    public void checkChangesCB(){

        if (!changesCB.getValue().isEmpty()){

            edit.setDisable(false);

        } else {

            edit.setDisable(true);

        }

    }

}
