package sample.Controllers;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sample.Core.Common.SceneCreator;
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

    @FXML private TableView skillsTableView;
    @FXML private TableColumn<Skill, String> nameColumn;
    @FXML private TableColumn<Skill, String> levelColumn;
    @FXML private AnchorPane game;
    @FXML private StackPane stackPane;
    @FXML private JFXButton saveButton;
    @FXML private JFXButton exitButton;
    @FXML private JFXButton continueButton;
    @FXML private TextField time;
    @FXML private JFXProgressBar hungerBar;
    @FXML private JFXProgressBar energyBar;
    @FXML private JFXProgressBar bladderBar;
    @FXML private JFXProgressBar hygieneBar;
    @FXML private AnchorPane noWork;
    @FXML private AnchorPane haveWork;
    @FXML private Label positionLabel;
    @FXML private Label salaryLabel;
    @FXML private Label workingDaysLabel;
    @FXML private ImageView workImage;

    private ObservableList<Skill> listOfSkills = FXCollections.observableArrayList();
    private JFXDialog dialog;
    private IHeroNeeds needs;
    private Hero hero;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        saveButton.setVisible(false);
        continueButton.setVisible(false);
        exitButton.setVisible(false);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        skillsTableView.setPlaceholder(new Label("You haven`t any skills"));
    }

    public void setHero(Hero person) {
        hero = person;
        needs = hero.needs();

        refreshList();
        refreshBars();

        time.setText(String.valueOf(hero.getCalendar().getTime()));

        if (hero.getWork() == null) {
            noWork.toFront();
            noWork.setVisible(true);
            haveWork.setVisible(false);
            haveWork.toBack();
        } else {
            noWork.toBack();
            noWork.setVisible(false);
            haveWork.toFront();
            haveWork.setVisible(true);

            positionLabel.setText(hero.getWork().getPosition());
            salaryLabel.setText(hero.getWork().getSalary() + "$");
            workingDaysLabel.setText(hero.getWork().getWorkingDays());
            workingDaysLabel.setTextFill(Color.RED);
            setWorkImage();
        }
    }

    public void setWorkImage() {
        switch (hero.getWork().getPosition()) {
            case ("Cartoons artist"):
                workImage.setImage(new Image("/sample/Resources/Images/Palette.png"));
                break;
            case ("Junior developer"):
                workImage.setImage(new Image("/sample/Resources/Images/ComputerKey.png"));
                break;
            case ("Bacterium Scientist"):
                workImage.setImage(new Image("/sample/Resources/Images/ChessPawn2.png"));
                break;
            case ("Children's photographer"):
                workImage.setImage(new Image("/sample/Resources/Images/Camera.png"));
                break;
            case ("Beetle sprayer"):
                workImage.setImage(new Image("/sample/Resources/Images/SproutSoil.png"));
                break;
            case ("Stooge"):
                workImage.setImage(new Image("/sample/Resources/Images/GuitarAcoustic.png"));
                break;
            case ("Twerker"):
                workImage.setImage(new Image("/sample/Resources/Images/DiscoBall.png"));
                break;
            case ("Fan Fiction writer"):
                workImage.setImage(new Image("/sample/Resources/Images/WritingPaper.png"));
                break;
        }
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

            stackPane.toFront();
            stackPane.toFront();
            stackPane.toFront();

            dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
            dialog.show();
        }
    }

    public void eatClick(ActionEvent actionEvent) {
        needs.eating(hero.getCalendar());
        time.setText(String.valueOf(hero.getCalendar().getTime()));

        refreshBars();
    }

    public void sleepClick(ActionEvent actionEvent) {
        needs.sleeping(hero.getCalendar());
        time.setText(String.valueOf(hero.getCalendar().getTime()));

        refreshBars();
    }

    public void peeClick(ActionEvent actionEvent) {
        needs.goToPee(hero.getCalendar());
        time.setText(String.valueOf(hero.getCalendar().getTime()));

        refreshBars();
    }

    public void bathClick(ActionEvent actionEvent) {
        needs.takingShower(hero.getCalendar());
        time.setText(String.valueOf(hero.getCalendar().getTime()));

        refreshBars();
    }

    public void refreshBars() {
        hungerBar.setProgress((double) hero.getHeroNeeds().hunger / 100.0);
        energyBar.setProgress((double) hero.getHeroNeeds().energy / 100.0);
        bladderBar.setProgress((double) hero.getHeroNeeds().bladder / 100.0);
        hygieneBar.setProgress((double) hero.getHeroNeeds().hygiene / 100.0);
    }

    public void saveGame(ActionEvent actionEvent) {
        save(hero);

        JFXButton cont = new JFXButton("Continue");
        cont.getStyleClass().add("button-raised");

        JFXDialogLayout content = new JFXDialogLayout();
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

        long delay = 1000L;
        new Timer("Timer").schedule(task, delay);
    }

    public void refreshList() {
        listOfSkills.clear();
        if (hero.getSkill().getPainting().getLevel() != 0)
            listOfSkills.add(new Skill("Painting", hero.getSkill().getPainting().getLevel()));
        if (hero.getSkill().getDancing().getLevel() != 0)
            listOfSkills.add(new Skill("Dancing", hero.getSkill().getDancing().getLevel()));
        if (hero.getSkill().getWriting().getLevel() != 0)
            listOfSkills.add(new Skill("Writing", hero.getSkill().getWriting().getLevel()));
        if (hero.getSkill().getProgramming().getLevel() != 0)
            listOfSkills.add(new Skill("Programming", hero.getSkill().getProgramming().getLevel()));
        if (hero.getSkill().getGardening().getLevel() != 0)
            listOfSkills.add(new Skill("Gardening", hero.getSkill().getGardening().getLevel()));
        if (hero.getSkill().getLogic().getLevel() != 0)
            listOfSkills.add(new Skill("Logic", hero.getSkill().getLogic().getLevel()));
        if (hero.getSkill().getPhotography().getLevel() != 0)
            listOfSkills.add(new Skill("Photography", hero.getSkill().getPhotography().getLevel()));
        if (hero.getSkill().getPlayingTheGuitar().getLevel() != 0)
            listOfSkills.add(new Skill("Playing the guitar", hero.getSkill().getPlayingTheGuitar().getLevel()));

        skillsTableView.setItems(listOfSkills);
    }

    public void findJob(ActionEvent actionEvent) {
        SceneCreator.<JobController>newWithFadeOut(game, "/sample/filesFXML/Job.fxml",
                controller -> controller.setHero(hero));
    }
}
