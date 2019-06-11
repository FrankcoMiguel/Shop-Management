package app;

import data.SqliteConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ProductModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Products extends Functionalities {



    //Buttons
    public Button save;

    //TextFields
    public TextField name, cost, stock_in, provider;
    public Label status, statusChanges;


    public TableView<ProductModel> tableProduct;
    public TableColumn<ProductModel, Integer> col_id;
    public TableColumn<ProductModel, String> col_name;
    public TableColumn<ProductModel, Double> col_cost;
    public TableColumn<ProductModel, Integer> col_stock_in;
    public TableColumn<ProductModel, String> col_provider;

    public VBox delete, edit;
    public HBox changesCont;

    public ComboBox<String> choose_code;
    public ComboBox<String> choose_column;
    public TextField changes;

    //Objects
    private SqliteConnector sqlite;
    private ProductModel product;
    private ObservableList<ProductModel> product_list;
    private ObservableList<String> product_codes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        Propierties defaultProperties = new Propierties(); // Instance

        defaultProperties.newStage("Stock",back_button);

        col_id.setCellValueFactory(new PropertyValueFactory<ProductModel, Integer>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<ProductModel, String>("name"));
        col_cost.setCellValueFactory(new PropertyValueFactory<ProductModel, Double>("cost"));
        col_stock_in.setCellValueFactory(new PropertyValueFactory<ProductModel, Integer>("stock_in"));
        col_provider.setCellValueFactory(new PropertyValueFactory<ProductModel, String>("provider"));

        product_list = FXCollections.observableArrayList();
        product_codes = FXCollections.observableArrayList();

        choose_column.getItems().addAll("Producto","Stock","Costo","Proveedor");

        sqlite = new SqliteConnector();

        try {
            sqlite.selectProduct(product_list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {


            sqlite.selectProductCodes(product_codes);
            choose_code.getItems().setAll(product_codes);


        }catch (Exception e){

            System.out.println("Error \n"+
                    e);

        }


        tableProduct.setItems(product_list);


        delete.setOnMouseClicked(event -> {

            try {

                sqlite.deleteProduct(choose_code.getValue());
                product_list.clear();
                sqlite.selectProduct(product_list);
                tableProduct.setItems(product_list);

                choose_column.setDisable(true);
                product_codes.clear();


                if (changesCont.isVisible()){

                    changesCont.setVisible(false);

                }

                try{


                    sqlite.selectProductCodes(product_codes);
                    choose_code.getItems().setAll(product_codes);


                } catch (Exception e){

                    statusChanges.setText("No hay productos");
                    statusChanges.setVisible(true);

                }

                statusChanges.setText("El producto ha sido eliminado!");
                statusChanges.setVisible(true);

                delete.setDisable(true);



            } catch (Exception e){

                statusChanges.setText("Error al eliminar producto");
                statusChanges.setVisible(true);

            }

        });


        save.setOnMouseClicked(event -> {

            if (!name.getText().isEmpty() && !cost.getText().isEmpty() && !stock_in.getText().isEmpty() && !provider.getText().isEmpty()){




                try {

                    if (sqlite == null){

                        sqlite = new SqliteConnector();

                    }

                    String number =  sqlite.countProduct();

                    product = new ProductModel("P"+number,name.getText(),Double.parseDouble(cost.getText()),Integer.parseInt(stock_in.getText()),provider.getText());
                    save.setDisable(true);
                    tableProduct.getItems().clear();
                    sqlite.insertProduct(product);
                    sqlite.selectProduct(product_list);
                    tableProduct.setItems(product_list);
                    name.clear();
                    cost.clear();
                    stock_in.clear();
                    provider.clear();

                    save.setDisable(false);

                    product_codes.clear();
                    sqlite.selectProductCodes(product_codes);
                    choose_code.getItems().setAll(product_codes);


                } catch (Exception e){

                    status.setText("Hay campos que requieren números");
                    status.setVisible(true);

                }


            } else {

                status.setText("Complete all fields");
                status.setVisible(true);

            }




        });

        edit.setOnMouseClicked(event -> {

            try {

                String column = null;

                switch (choose_column.getValue()){

                    case "Producto":
                        column = "name";
                        break;

                    case "Stock":
                        column = "stock_in";
                        break;

                    case "Costo":
                        column = "cost";
                        break;

                    case "Proveedor":
                        column = "provider";
                        break;


                    default:
                        break;

                }

                sqlite.updateProduct(column,changes.getText(),choose_code.getValue());

                tableProduct.getItems().clear();
                sqlite.selectProduct(product_list);
                tableProduct.setItems(product_list);

                statusChanges.setText("El producto ha sido actualizado!");
                statusChanges.setVisible(true);
                changes.clear();


            } catch (Exception e){

                System.out.println("Error al actualizar producto" + e);
                status.setText("Error al actualizar");
                status.setVisible(true);

            }


        });


    }


    public void statusGone(){

        if (status.isVisible()){

            status.setVisible(false);


        }

    }


    public void checkCode(){

        if (statusChanges.isVisible()){

            statusChanges.setVisible(false);

        }

        if (!choose_code.getItems().isEmpty()){

            choose_column.setDisable(false);
            delete.setDisable(false);


        } else {

            choose_column.setDisable(true);
            delete.setDisable(true);

        }



    }

    public void checkColumn(){

        if (statusChanges.isVisible()){

            statusChanges.setVisible(false);

        }

        if (!choose_column.getValue().isEmpty()){

            changesCont.setVisible(true);

        } else {

            changesCont.setVisible(false);

        }

    }

    public void checkChanges(){

        if (!changes.getText().isEmpty()){

            edit.setDisable(false);

        } else {

            edit.setDisable(true);

        }

    }


}
