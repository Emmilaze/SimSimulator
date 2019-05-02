package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import sample.Core.Common.SceneCreator;
import sample.Person.Hero;

import java.net.URL;
import java.util.ResourceBundle;


public class JobController implements Initializable {

    @FXML private Label paintlvl;
    @FXML private Label dancelvl;
    @FXML private Label writelvl;
    @FXML private Label proglvl;
    @FXML private Label gardenlvl;
    @FXML private Label photolvl;
    @FXML private Label logiclvl;
    @FXML private Label playlvl;
    @FXML private Label paintItem;
    @FXML private Label danceItem;
    @FXML private Label playItem;
    @FXML private Label writeItem;
    @FXML private Label progItem;
    @FXML private Label gardenItem;
    @FXML private Label logicItem;
    @FXML private Label photoItem;
    @FXML private JFXButton dancebtn;
    @FXML private JFXButton photobtn;
    @FXML private JFXButton gardenbtn;
    @FXML private JFXButton progbtn;
    @FXML private JFXButton paintbtn;
    @FXML private JFXButton writebtn;
    @FXML private JFXButton logicbtn;
    @FXML private JFXButton playbtn;
    @FXML private AnchorPane job;

    private Hero hero;

    public void setHero(Hero person) {
        hero = person;
        if (hero.getSkill().getDancing().getLevel() < 1) {
            dancelvl.setTextFill(Color.RED);
            dancebtn.setDisable(true);
        }
        if (hero.getSkill().getGardening().getLevel() < 1) {
            gardenlvl.setTextFill(Color.RED);
            gardenbtn.setDisable(true);
        }
        if (hero.getSkill().getLogic().getLevel() < 2) {
            logiclvl.setTextFill(Color.RED);
            logicbtn.setDisable(true);
        }
        if (hero.getSkill().getPhotography().getLevel() < 1) {
            photolvl.setTextFill(Color.RED);
            photobtn.setDisable(true);
        }
        if (hero.getSkill().getPainting().getLevel() < 3) {
            paintlvl.setTextFill(Color.RED);
            paintbtn.setDisable(true);
        }
        if (hero.getSkill().getPlayingTheGuitar().getLevel() < 4) {
            playlvl.setTextFill(Color.RED);
            playbtn.setDisable(true);
        }
        if (hero.getSkill().getProgramming().getLevel() < 3) {
            proglvl.setTextFill(Color.RED);
            progbtn.setDisable(true);
        }
        if (hero.getSkill().getWriting().getLevel() < 2) {
            writelvl.setTextFill(Color.RED);
            writebtn.setDisable(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void paint(ActionEvent actionEvent) {
        hero.newWork("Cartoons artist", 60, "Tu Th Fr Sa");
        back();
    }

    public void prog(ActionEvent actionEvent) {
        hero.newWork("Junior developer", 90, "Mo Tu Wed Th Fr");
        back();
    }

    public void logic(ActionEvent actionEvent) {
        hero.newWork("Bacterium Scientist", 80, "Mo Tu We Th Fr");
        back();
    }

    public void photo(ActionEvent actionEvent) {
        hero.newWork("Children's photographer", 70, "Tu We Fr Sa Su");
        back();
    }

    public void garden(ActionEvent actionEvent) {
        hero.newWork("Beetle sprayer", 60, "Mo Tu We Th Fr");
        back();
    }

    public void play(ActionEvent actionEvent) {
        hero.newWork("Stooge", 80, "Tu We Fr Sa Su");
        back();
    }

    public void dance(ActionEvent actionEvent) {
        hero.newWork("Twerker", 80, "Tu We Fr Sa Su");
        back();
    }

    public void write(ActionEvent actionEvent) {
        hero.newWork("Fan Fiction writer", 40, "Mo Tu We Th Fr");
        back();
    }

    public void back() {
        SceneCreator.<GameController>newWithFadeOut(job, "/sample/filesFXML/Game.fxml",
                controller -> controller.setHero(hero));
    }

    public void cancel(ActionEvent actionEvent) {
        SceneCreator.<GameController>newWithFadeOut(job, "/sample/filesFXML/Game.fxml",
                controller -> controller.setHero(hero));
    }
}
