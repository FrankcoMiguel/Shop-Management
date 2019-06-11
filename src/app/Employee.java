package app;

import data.SqliteConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerModel;
import model.EmployeeModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Employee extends Functionalities {



    //Buttons
    public Button save;

    //TextFields and ComboBox
    public TextField name, dni, employment, address, phone;
    public ComboBox<String> sex;
    public Label status;

    public TableView<EmployeeModel> tableEmployee;
    public TableColumn<EmployeeModel, String> col_name;
    public TableColumn<EmployeeModel, String> col_sex;
    public TableColumn<EmployeeModel, String> col_employment;



    //Objects
    private EmployeeModel employee;
    private SqliteConnector sqlite;

    private ObservableList<EmployeeModel> employee_list;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        Propierties defaultProperties = new Propierties(); // Instance

        defaultProperties.newStage("Stock",back_button);

        sex.getItems().setAll("M","F");

        col_name.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("name"));
        col_employment.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employment"));
        col_sex.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("sex"));

        employee_list = FXCollections.observableArrayList();

        sqlite = new SqliteConnector();

        try {
            sqlite.selectEmployee(employee_list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tableEmployee.setItems(employee_list);



        save.setOnMouseClicked(event -> {

            if (!name.getText().isEmpty() && !dni.getText().isEmpty() && !sex.getValue().isEmpty() && !address.getText().isEmpty() && !phone.getText().isEmpty() && !employment.getText().isEmpty()){


                if (sqlite == null){

                    sqlite = new SqliteConnector();

                }

                try {

                    employee = new EmployeeModel(name.getText(),dni.getText(),sex.getValue(),employment.getText(),address.getText(),phone.getText());
                    tableEmployee.getItems().clear();
                    sqlite.insertEmployee(employee);

                    save.setDisable(true);

                    name.clear();
                    dni.clear();
                    address.clear();
                    employment.clear();
                    phone.clear();
                    status.setText("Employee added successfully");
                    status.setVisible(true);

                    sqlite.selectEmployee(employee_list);
                    tableEmployee.setItems(employee_list);
                    save.setDisable(false);

                } catch (Exception e){

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
