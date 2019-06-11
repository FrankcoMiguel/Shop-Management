package app;


import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class FullExit extends Functionalities {

    public ImageView closeExit;
    public Button exit, saveExit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        Propierties defaultProperties = new Propierties(); // Instance
        defaultProperties.newStage("Homepage",closeExit);

        defaultProperties.fullExit(exit);
        defaultProperties.fullExit(saveExit);

    }
}
