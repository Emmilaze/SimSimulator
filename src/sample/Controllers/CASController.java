package sample.Controllers;

import com.jfoenix.controls.JFXColorPicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.Core.Common.SceneCreator;
import sample.Person.Hero;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;

import static sample.Controllers.Controller.makeFadeOut;
import static sample.Person.HeroNeeds.*;
import static sample.Person.RandomCharacter.*;


public class CASController implements Initializable {

    ObservableList<String> sexBox = FXCollections.observableArrayList("Male", "Female");
    ObservableList<String> ageBox = FXCollections.observableArrayList("Young", "Adult", "Old");
    ObservableList<String> bodyBox = FXCollections.observableArrayList("Slim", "Fat", "Muscular", "Fit");

    @FXML private TextField nameField;
    @FXML private TextField lastNameField;
    @FXML private ChoiceBox sexChoiceBox;
    @FXML private ChoiceBox ageChoiceBox;
    @FXML private JFXColorPicker eyesColor;
    @FXML private JFXColorPicker hairColor;
    @FXML private JFXColorPicker skinColor;
    @FXML private ChoiceBox bodyChoiceBox;
    @FXML private AnchorPane cas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sexChoiceBox.setItems(sexBox);
        sexChoiceBox.setValue("Male");
        ageChoiceBox.setItems(ageBox);
        ageChoiceBox.setValue("Young");
        bodyChoiceBox.setItems(bodyBox);
        bodyChoiceBox.setValue("Fit");
    }

    public void randomDataClick(MouseEvent mouseEvent) throws IOException {
        if (sex()) {
            nameField.setText(randomNameMale());
            sexChoiceBox.setValue("Male");
        } else {
            nameField.setText(randomNameFemale());
            sexChoiceBox.setValue("Female");
        }
        lastNameField.setText(randomLastName());
        ageChoiceBox.setValue(age());
    }

    public void donebtn(MouseEvent mouseEvent) {
        if (!(nameField.getText().isEmpty()) && !(lastNameField.getText().isEmpty())) {

            Calendar calendar = new GregorianCalendar(LocalDateTime.now().getYear(),
                    LocalDateTime.now().getMonthValue(), LocalDateTime.now().getDayOfMonth(),
                    LocalDateTime.now().getHour(), LocalDateTime.now().getMinute());

            Hero hero = new Hero(nameField.getText(), lastNameField.getText(), ageChoiceBox.getValue().toString(),
                    sexChoiceBox.getValue().toString(), calendar);

            hero.newAppearance(eyesColor.getValue(), hairColor.getValue(), skinColor.getValue(),
                    bodyChoiceBox.getValue().toString());

            hero.newHeroNeeds(MONEY_START, HUNGER_START, BLADDER_START, HYGIENE_START, ENERGY_START);

            hero.newSkills();

            SceneCreator.<GameController>newWithFadeOut(cas, "/sample/filesFXML/Game.fxml",
                    controller -> controller.setHero(hero));
        } else {
            Alert alert = new Alert(Alert.AlertType.NONE);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/sample/style.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.setTitle("Ops...");
            alert.setHeaderText("Please fill all fields");

            ButtonType ok = new ButtonType("Ok");

            alert.getButtonTypes().clear();

            alert.getButtonTypes().addAll(ok);

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ok) {
                alert.close();
            } else {
                alert.close();
            }
        }
    }

    public void cancelbtn(MouseEvent mouseEvent) {
        Parent mainMenu;
        {
            try {
                mainMenu = FXMLLoader.load(getClass().getResource("/sample/filesFXML/MainMenu.fxml"));
                makeFadeOut(cas, mainMenu);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void randomAppearanceClick(MouseEvent mouseEvent) {
        eyesColor.setValue(randomColor());
        hairColor.setValue(randomColor());
        skinColor.setValue(randomColor());
        bodyChoiceBox.setValue(randomBody());
    }

//    public void randomAppEntered(MouseEvent mouseEvent) {
//        randomApp.setX(1.5);
//        randomApp.setY(1.5);
//    }
//
//    public void randomAppExited(MouseEvent mouseEvent) {
//        randomApp.setX(1);
//        randomApp.setY(1);
//    }
//
//    public void doneEntered(MouseEvent mouseEvent) {
//        done.setX(1.5);
//        done.setY(1.5);
//    }
//
//    public void cancelEntered(MouseEvent mouseEvent) {
//        cancel.setX(1.5);
//        cancel.setY(1.5);
//    }
//
//    public void doneExited(MouseEvent mouseEvent) {
//        done.setX(1);
//        done.setY(1);
//    }
//
//    public void cancelExited(MouseEvent mouseEvent) {
//        cancel.setX(1);
//        cancel.setY(1);
//    }
//
//    public void randomDataEntered(MouseEvent mouseEvent) {
//        randomData.setFitHeight(1.5);
//        randomData.setFitWidth(1.5);
//    }
//
//    public void randomDataExited(MouseEvent mouseEvent) {
//        randomData.setFitHeight(1);
//        randomData.setFitWidth(1);
//    }
}
