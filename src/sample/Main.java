package sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import sample.Person.Hero;

public class Main extends Application {

    public final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static ArrayList<Hero> heroes = new ArrayList<Hero>();

    public static ArrayList<Hero> readAllCharacters() throws IOException {
        File folder = new File("./Characters");
        ArrayList<Hero> allHeroes = new ArrayList<Hero>();
        for (File file : folder.listFiles()) {
            if (file.getName().contains(".txt")) {
                Hero person = CharacterStorage.read_hero(file, GSON);
                allHeroes.add(person);
            }
        }
        return allHeroes;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent mainMenu = FXMLLoader.load(getClass().getResource("filesFXML/MainMenu.fxml"));
        primaryStage.setTitle("SimSimulator");
        primaryStage.setScene(new Scene(mainMenu, 898, 500));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        heroes.addAll(readAllCharacters());
        launch(args);
    }
}
