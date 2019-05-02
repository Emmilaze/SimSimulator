package sample.Core.Common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import sample.Controllers.Controller;

import java.io.IOException;
import java.util.function.Consumer;

import static sample.Controllers.Controller.makeFadeOut;

public class SceneCreator {

    public static <T> void newWithFadeOut(AnchorPane pane, String path, Consumer<T> filling) {
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource(path));

        Parent scene;
        try {
            scene = loader.load();
            makeFadeOut(pane, scene);

            T controller = loader.getController();
            filling.accept(controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
