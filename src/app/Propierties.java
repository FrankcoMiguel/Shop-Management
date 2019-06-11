package app;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;



public class Propierties {


    private double x, y;
    private double bound_x, bound_y;
    private Stage stage;

    void fullExit(Button close_button){

        close_button.setOnMouseClicked(event -> System.exit(0));

    }

    void fullExit(ImageView close_button){

        close_button.setOnMouseClicked(event -> System.exit(0));

    }

    void dragApp(BorderPane main_pane){

        main_pane.setOnMousePressed(event -> {

            x = event.getSceneX();
            y = event.getSceneY();

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            bound_x = screenSize.getWidth() + 30;
            bound_y = screenSize.getHeight() + 30;


        });


        main_pane.setOnMouseDragged(event -> {

            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            bound_x = screenSize.getWidth() + 30;
            bound_y = screenSize.getHeight() + 30;

            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);





        });



    }

    void minimizeApp(ImageView minimize_button){

        minimize_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.centerOnScreen();
                stage.setIconified(true);

            }
        });

    }

    void newStage(String path, ImageView function) {


        function.setOnMouseClicked((event) -> {
            Stage stageBack = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Parent funct = null;
            try {
                funct = FXMLLoader.load(getClass().getResource( path + ".fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            x = screenSize.getWidth();
            y = screenSize.getHeight();

            if (x > (y + 400)){

                // For screens with a width more long than height// by Frank

                x = screenSize.getWidth() - 250;
                y = screenSize.getHeight() - 80;

            } else {

                // For screens with a width and a height equally or not too difference// by Frank
                x = screenSize.getWidth() - 80;
                y = screenSize.getHeight() - 80;

            }

            stageBack.setScene(new Scene(funct, x, y));


        });

    }

    void newStage(String path, Button function) {


        function.setOnMouseClicked((event) -> {
            Stage stageBack = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Parent funct = null;
            try {
                funct = FXMLLoader.load(getClass().getResource( path + ".fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            x = screenSize.getWidth();
            y = screenSize.getHeight();

            if (x > (y + 400)){

                // For screens with a width more long than height// by Frank

                x = screenSize.getWidth() - 250;
                y = screenSize.getHeight() - 80;

            } else {

                // For screens with a width and a height equally or not too difference// by Frank
                x = screenSize.getWidth() - 80;
                y = screenSize.getHeight() - 80;

            }

            stageBack.setScene(new Scene(funct, x, y));


        });

    }

    void newStage(String path, VBox function) {


        function.setOnMouseClicked((event) -> {
            Stage stageBack = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Parent funct = null;
            try {
                funct = FXMLLoader.load(getClass().getResource( path + ".fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            x = screenSize.getWidth();
            y = screenSize.getHeight();

            if (x > (y + 400)){

                // For screens with a width more long than height// by Frank

                x = screenSize.getWidth() - 250;
                y = screenSize.getHeight() - 80;

            } else {

                // For screens with a width and a height equally or not too difference// by Frank
                x = screenSize.getWidth() - 80;
                y = screenSize.getHeight() - 80;

            }

            stageBack.setScene(new Scene(funct, x, y));


        });

    }

    void newStage(String path, HBox function) {


        function.setOnMouseClicked((event) -> {
            Stage stageBack = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Parent funct = null;
            try {
                funct = FXMLLoader.load(getClass().getResource( path + ".fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            x = screenSize.getWidth();
            y = screenSize.getHeight();

            if (x > (y + 400)){

                // For screens with a width more long than height// by Frank

                x = screenSize.getWidth() - 250;
                y = screenSize.getHeight() - 80;

            } else {

                // For screens with a width and a height equally or not too difference// by Frank
                x = screenSize.getWidth() - 80;
                y = screenSize.getHeight() - 80;

            }

            stageBack.setScene(new Scene(funct, x, y));


        });

    }

    /*
    void popUp(String path, ImageView popup_button){

        popup_button.setOnMouseClicked((event) -> {

            Parent funct = null;
            try {
                funct = FXMLLoader.load(getClass().getResource( path + ".fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }


            Stage stagePopUp = new Stage();
            stagePopUp.setScene(new Scene(funct,350,300));
            stagePopUp.initStyle(StageStyle.UNDECORATED);
            stagePopUp.centerOnScreen();
            stagePopUp.show();


        });

    }

    void popUpClose(ImageView close_button){

        close_button.setOnMouseClicked(event -> {

            Stage stageBack = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageBack.hide();

        });

    }
*/





}
