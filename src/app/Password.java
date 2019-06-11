package app;


import data.SqliteConnector;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.AdminModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Password extends Functionalities {

    public ImageView closeExit, next;
    public PasswordField password;
    private SqliteConnector sqlite;
    private AdminModel admin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        Propierties defaultProperties = new Propierties(); // Instance
        defaultProperties.newStage("Homepage",closeExit);

        sqlite = new SqliteConnector();
        admin = new AdminModel();

        try {
            sqlite.selectAdmin(admin);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        defaultProperties.newStage("Settings",next);

    }


    @FXML
    protected void checkPassword(){

        if (password.getText().equals(admin.getPassword())){

            next.setDisable(false);

        } else {

            next.setDisable(true);

        }

    }


}
