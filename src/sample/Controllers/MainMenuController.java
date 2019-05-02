package sample.Controllers;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import sample.Core.Common.SceneCreator;
import sample.Core.Model.Game;
import sample.Person.Hero;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import static sample.Controllers.Controller.makeFadeOut;

public class MainMenuController implements Initializable {

    ObservableList<Hero> list;

    @FXML private Label continueLabel;
    @FXML private Label newGameLabel;
    @FXML private Label exitLabel;
    @FXML private AnchorPane mainMenu;
    @FXML private StackPane stackPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Hero> heroes = Game.getInstance().heroes;
        list = FXCollections.observableArrayList(heroes);

        if (heroes.isEmpty()) mainMenu.getChildren().remove(continueLabel);
    }

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

        JFXButton continueButton = new JFXButton("Continue");
        continueButton.getStyleClass().add("button-raised");
        content.setBody(listView, continueButton);

        content.setActions(continueButton);
        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);

        continueButton.setOnAction(event -> continueButtonClick(listView, dialog));

        dialog.setLayoutX(222);
        dialog.setLayoutY(122);
        dialog.setPrefHeight(256);
        dialog.setPrefWidth(454);
        dialog.show();
    }

    private void continueButtonClick(JFXListView listView, JFXDialog dialog) {
        if (listView.getSelectionModel() != null) {
            String name = listView.getSelectionModel().getSelectedItem().toString();

            Hero hero = Game.getInstance().heroByName(name);
            if (hero == null) return;

            SceneCreator.<GameController>newWithFadeOut(mainMenu, "/sample/filesFXML/Game.fxml",
                    controller -> controller.setHero(hero));

            dialog.close();
        }
    }
}
