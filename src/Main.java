import data.SqliteConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.File;

public class Main extends Application {

    private double x, y;

    @Override
    public void start(Stage primaryStage) throws Exception{

        File f = new File("storage.sqlite");
        if(!f.exists()) {

            SqliteConnector sqlite = new SqliteConnector();
            sqlite.createAll();

        }


        Parent Homepage = FXMLLoader.load(getClass().getResource("app/Products.fxml"));
        primaryStage.setTitle("Shop Management");
        primaryStage.initStyle(StageStyle.UNDECORATED);

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

        primaryStage.setScene(new Scene(Homepage, x, y));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
