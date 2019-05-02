package sample.Controllers;

import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Core.Common.SceneCreator;
import sample.Person.Hero;

public class ShoppingModeController {

    @FXML private JFXTreeTableView shopTableView;
    @FXML private Label moneyLabel;
    @FXML private AnchorPane shop;

    private Hero hero;

    public void cancel(ActionEvent actionEvent) {
        SceneCreator.<GameController>newWithFadeOut(shop, "/sample/filesFXML/Game.fxml", controller -> controller.setHero(hero));

    }

    public void buyClick(ActionEvent actionEvent) {
    }
}
