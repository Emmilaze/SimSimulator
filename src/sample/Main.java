package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent mainMenu = FXMLLoader.load(getClass().getResource("filesFXML/MainMenu.fxml"));
        primaryStage.setTitle("SimSimulator");
        primaryStage.getIcons().add(new Image("/sample/Resources/Images/icon.png"));
        primaryStage.setScene(new Scene(mainMenu, 898, 500));
        primaryStage.show();
    }
}
