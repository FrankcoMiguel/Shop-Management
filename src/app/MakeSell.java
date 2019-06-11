package app;

import data.SqliteConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MakeSell extends Functionalities {



    //Combo-Box
    public ComboBox<String> customer;
    public ComboBox<String> employee;

    // Text-Fields
    public TextField cashier_code;
    public Label accounting;
    private double account = 0;


    //Buttons

    public ImageView allCodes;
    public GridPane keyboard;
    public VBox allCodesView;


    // Table
    public TableView<BillModel> tableBill;

    public TableColumn<BillModel,String> details;
    public TableColumn<BillModel,Double> cost;


    public TableView<ProductModel> tableCodes;

    public TableColumn<ProductModel, String> code;
    public TableColumn<ProductModel, String> name;



    //Objects
    private SqliteConnector sqlite;
    private ObservableList<ProductModel> productAndServices;
    private ObservableList<String> names;


    //Keyword button

    public VBox one, two, three, four, five, six, seven, eight, nine, zero, doneKey, erase, p, s;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        Propierties defaultProperties = new Propierties(); // Instance
        defaultProperties.newStage("Homepage",back_button);

        code.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));


        details.setCellValueFactory(new PropertyValueFactory<>("name"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));


        if (sqlite == null){

            sqlite = new SqliteConnector();

        }



        try {

            productAndServices = FXCollections.observableArrayList();
            sqlite.selectCodes(productAndServices);
            tableCodes.setItems(productAndServices);

            names = FXCollections.observableArrayList();
            sqlite.selectCustomerName(names);
            customer.getItems().setAll(names);

            names = FXCollections.observableArrayList();
            sqlite.selectEmployeeName(names);
            employee.getItems().setAll(names);

        } catch (Exception e){

            System.out.println("Hola");

        }

        //Customer)




        allCodes.setOnMouseClicked(event -> {

            if (keyboard.isVisible()){

                keyboard.setVisible(false);
                allCodesView.setVisible(true);

            } else if (allCodesView.isVisible()){

                allCodesView.setVisible(false);
                keyboard.setVisible(true);

            }

        });

        //Keyboard

        one.setOnMouseClicked(event -> {

            String code =  cashier_code.getText();
            code = code + "1";
            cashier_code.setText(code);


        });
        two.setOnMouseClicked(event -> {

            String code =  cashier_code.getText();
            code = code + "2";
            cashier_code.setText(code);


        });
        three.setOnMouseClicked(event -> {

            String code =  cashier_code.getText();
            code = code + "3";
            cashier_code.setText(code);


        });
        four.setOnMouseClicked(event -> {

            String code =  cashier_code.getText();
            code = code + "4";
            cashier_code.setText(code);

        });
        five.setOnMouseClicked(event -> {

            String code =  cashier_code.getText();
            code = code + "5";
            cashier_code.setText(code);


        });
        six.setOnMouseClicked(event -> {

            String code =  cashier_code.getText();
            code = code + "6";
            cashier_code.setText(code);


        });
        seven.setOnMouseClicked(event -> {

            String code =  cashier_code.getText();
            code = code + "7";
            cashier_code.setText(code);


        });
        eight.setOnMouseClicked(event -> {

            String code =  cashier_code.getText();
            code = code + "8";
            cashier_code.setText(code);


        });
        nine.setOnMouseClicked(event -> {

            String code =  cashier_code.getText();
            code = code + "9";
            cashier_code.setText(code);


        });
        zero.setOnMouseClicked(event -> {

            String code =  cashier_code.getText();
            code = code + "0";
            cashier_code.setText(code);


        });
        erase.setOnMouseClicked(event -> {

            if (!cashier_code.getText().isEmpty()){

                String code = cashier_code.getText();
                code = code.replaceFirst(String.copyValueOf(new char[]{code.charAt(code.length() - 1)}), "");
                cashier_code.setText(code);

            }

        });


        p.setOnMouseClicked(event -> {

            String code =  cashier_code.getText();
            code = code + "P";
            cashier_code.setText(code);


        });

        s.setOnMouseClicked(event -> {

            String code =  cashier_code.getText();
            code = code + "S";
            cashier_code.setText(code);


        });


        doneKey.setOnMouseClicked(event -> {

            if (sqlite == null){

                sqlite = new SqliteConnector();

            }


            try {

                BillModel bill = new BillModel();
                sqlite.searchProduct(bill,cashier_code.getText());

                if (bill.getName() != null){

                    account += bill.getCost();
                    accounting.setText( "RD$ " + account);
                    tableBill.getItems().add(bill);
                    cashier_code.clear();

                }


            } catch (Exception e) {

                System.out.println("XD");

            }


        });





    }



}