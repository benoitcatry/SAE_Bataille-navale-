package view;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;

public class OptionPage {
    private Button valid,mainMenu;


    public OptionPage() {
        initWidget();
    }

    private void initWidget() {
        valid = new Button("Toggle Music");
        mainMenu = new Button("Main Menu");


    }

    public void placeWidgets(ShipRootPane root) {
        root.setStyle("-fx-alignment: center");
        Label l = new Label("");
        //select mode de jeux:
        HBox hboxmode = new HBox();
        Label labelmode = new Label("Game Option ");
        hboxmode.setSpacing(20);
        labelmode.setStyle("-fx-font-size: 20;" + "-fx-font-weight: bold;"+ "-fx-text-fill: #F0FFF0;");


        valid.setStyle(
                "-fx-font-size: 24;" +
                        "-fx-background-color: lightgrey;" +
                        "-fx-font-weight: bold;" + "-fx-text-fill: Black;"
        );

        VBox vboxtotal = new VBox();
        vboxtotal.getChildren().addAll(l,valid,mainMenu);
        vboxtotal.setMinSize(900,600);
        vboxtotal.setSpacing(50);
        vboxtotal.setStyle("-fx-background-image: url('/Images/background/test4.png');" + "-fx-padding: 0px 0px 0px 15px;" );

        root.getChildren().add(vboxtotal);
    }

    public void setButtonListener(EventHandler<ActionEvent> handler) {
        valid.setOnAction(handler);
        mainMenu.setOnAction(handler);
    }


    public Button getToggleMusic() {
        return valid;
    }

    public Button getMainMenu() {
        return mainMenu;
    }
}
