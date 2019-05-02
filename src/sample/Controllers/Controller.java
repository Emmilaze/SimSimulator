package sample.Controllers;

import com.google.gson.GsonBuilder;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Person.Hero;

import java.io.FileWriter;
import java.io.IOException;

public class Controller {

    public static void makeFadeOut(AnchorPane scene, Parent parent) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(scene);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished((ActionEvent event) -> {
            loadNextScene(parent, scene);
        });
        fadeTransition.play();
    }

    private static void loadNextScene(Parent parent, AnchorPane pane) {
        if(parent==null)
            return;
        Scene scene = new Scene(parent);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
    }

    public static void save(Hero hero){
        try (FileWriter writer = new FileWriter("./Characters/" + hero.toString() + ".txt")) {
            hero.save(hero, writer, new GsonBuilder().setPrettyPrinting().create());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
