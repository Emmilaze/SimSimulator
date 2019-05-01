package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import sample.Person.Hero;
import sample.Person.IHeroNeeds;
import sample.Person.Skills.Skill;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import static sample.Controllers.Controller.makeFadeOut;
import static sample.Controllers.Controller.save;

public class GameController implements Initializable {
    @FXML
    private TableView skillsTableView;
    @FXML
    private TableColumn<Skill, String> nameColumn;
    @FXML
    TableColumn<Skill, String> levelColumn;
    @FXML
    private AnchorPane game;
    @FXML
    private StackPane stackPane;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton exitButton;
    @FXML
    private JFXButton continueButton;
    @FXML
    private TextField time;

    private ObservableList<Skill> listOfSkills = FXCollections.observableArrayList();
    private JFXDialog dialog;
    private IHeroNeeds needs;
    private Hero hero;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        saveButton.setVisible(false);
        continueButton.setVisible(false);
        exitButton.setVisible(false);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Skill, String>("name"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<Skill, String>("level"));

        time.setText(String.valueOf(hero.getCalendar().getTime()));
    }

    public void setHero(Hero person){
        hero = person;
        needs = hero.needs();
        refreshList();
    }

    public void esc(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("ESCAPE")) {
            saveButton.setVisible(true);
            continueButton.setVisible(true);
            exitButton.setVisible(true);
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Pause"));
            content.setBody(saveButton, continueButton, exitButton);
            content.setActions(saveButton, continueButton, exitButton);
            dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
            stackPane.toFront();
            stackPane.toFront();
            stackPane.toFront();
            dialog.show();
        }
    }

    public void eatClick(MouseEvent mouseEvent) {
        needs.eating(hero.getCalendar());
        time.clear();
        time.setText(String.valueOf(hero.getCalendar().getTime()));
    }

    public void sleepClick(MouseEvent mouseEvent) {
        needs.sleeping(hero.getCalendar());
        time.setText(String.valueOf(hero.getCalendar().getTime()));
    }

    public void peeClick(MouseEvent mouseEvent) {
        needs.goToPee(hero.getCalendar());
        time.setText(String.valueOf(hero.getCalendar().getTime()));
    }

    public void bathClick(MouseEvent mouseEvent) {
        needs.takingShower(hero.getCalendar());
        time.setText(String.valueOf(hero.getCalendar().getTime()));
    }

    public void saveGame(ActionEvent actionEvent) {
        save(hero);
        JFXDialogLayout content = new JFXDialogLayout();

        JFXButton cont = new JFXButton("Continue");
        content.setBody(new Text("Save has done successfully"), cont);

        content.setActions(cont);
        JFXDialog alert = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
        cont.setOnAction(event -> alert.close());
        alert.show();
    }

    public void continueClick(ActionEvent actionEvent) {
        saveButton.setVisible(false);
        continueButton.setVisible(false);
        exitButton.setVisible(false);
        stackPane.toBack();
        stackPane.toBack();
        stackPane.toBack();
        dialog.close();
    }

    public void exitClick(ActionEvent actionEvent) {
        saveButton.setVisible(false);
        continueButton.setVisible(false);
        exitButton.setVisible(false);
        stackPane.toBack();
        stackPane.toBack();
        stackPane.toBack();
        dialog.close();
        makeFadeOut(game, game);
        TimerTask task = new TimerTask() {
            public void run() {
                System.exit(0);
            }
        };
        Timer timer = new Timer("Timer");
        long delay = 1000L;
        timer.schedule(task, delay);
    }

    public void refreshList(){
        listOfSkills.clear();

        listOfSkills.add(new Skill("Painting", hero.getSkill().getPainting().getLevel()));
        listOfSkills.add(new Skill("Dancing", hero.getSkill().getDancing().getLevel()));
        listOfSkills.add(new Skill("Writing", hero.getSkill().getWriting().getLevel()));
        listOfSkills.add(new Skill("Programming", hero.getSkill().getProgramming().getLevel()));
        listOfSkills.add(new Skill("Gardening", hero.getSkill().getGardening().getLevel()));
        listOfSkills.add(new Skill("Logic", hero.getSkill().getLogic().getLevel()));
        listOfSkills.add(new Skill("Photography", hero.getSkill().getPhotography().getLevel()));
        listOfSkills.add(new Skill("Playing the guitar", hero.getSkill().getPlayingTheGuitar().getLevel()));

        skillsTableView.setItems(listOfSkills);
    }
}
