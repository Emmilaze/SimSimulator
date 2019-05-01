package sample.Controllers;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import sample.Main;
import sample.Person.Hero;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import static sample.Controllers.Controller.makeFadeOut;

public class MainMenuController implements Initializable {

    ObservableList<Hero> list = FXCollections.observableArrayList(Main.heroes);
    @FXML
    private Label continueLabel;
    @FXML
    private Label newGameLabel;
    @FXML
    private Label exitLabel;
    @FXML
    private AnchorPane mainMenu;
    @FXML
    private StackPane stackPane;

    public void continueEntered(MouseEvent mouseEvent) {
        continueLabel.setScaleX(1.5);
        continueLabel.setScaleY(1.5);
    }

    public void newGameEntered(MouseEvent mouseEvent) {
        newGameLabel.setScaleX(1.5);
        newGameLabel.setScaleY(1.5);
    }

    public void exitExited(MouseEvent mouseEvent) {
        exitLabel.setScaleX(1);
        exitLabel.setScaleY(1);
    }

    public void exitEntered(MouseEvent mouseEvent) {
        exitLabel.setScaleX(1.5);
        exitLabel.setScaleY(1.5);
    }

    public void newGameExited(MouseEvent mouseEvent) {
        newGameLabel.setScaleX(1);
        newGameLabel.setScaleY(1);
    }

    public void continueExited(MouseEvent mouseEvent) {
        continueLabel.setScaleX(1);
        continueLabel.setScaleY(1);
    }

    public void exitClick(MouseEvent mouseEvent) {
        makeFadeOut(mainMenu, null);
        TimerTask task = new TimerTask() {
            public void run() {
                System.exit(0);
            }
        };
        Timer timer = new Timer("Timer");
        long delay = 1000L;
        timer.schedule(task, delay);
    }

    public void newGameClick(MouseEvent mouseEvent) {
        Parent newGame;
        {
            try {
                newGame = FXMLLoader.load(getClass().getResource("/sample/filesFXML/CAS.fxml"));
                makeFadeOut(mainMenu, newGame);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void continueClick(MouseEvent mouseEvent) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Choose the save"));
        JFXListView listView = new JFXListView();
        listView.setItems(list);

        JFXButton cont = new JFXButton("Continue");
        content.setBody(listView, cont);

        content.setActions(cont);
        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
        cont.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = listView.getSelectionModel().getSelectedItem().toString();
                for (int i = 0; i < Main.heroes.size(); i++) {
                    if (Main.heroes.get(i).toString().equals(name)) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/filesFXML/Game.fxml"));

                        Parent play;
                        try {
                            play = loader.load();
                            makeFadeOut(mainMenu, play);

                            GameController game = loader.getController();
                            game.setHero(Main.heroes.get(i));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                   dialog.close();
            }
        });
        dialog.setLayoutX(222);
        dialog.setLayoutY(122);
        dialog.setPrefHeight(256);
        dialog.setPrefWidth(454);
        dialog.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Main.heroes.isEmpty())
            mainMenu.getChildren().remove(continueLabel);
    }
}
