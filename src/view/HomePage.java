package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePage {
    static Button start,home,quit,options;
    private static void initWidgets(){
        start = new Button("Start Game");
        home = new Button("Main Menu");
        quit = new Button("Quit Game");
        options = new Button("Options");

    }

    private static void placeWidgets(ShipRootPane root){
        HBox h = new HBox();
        VBox v = new VBox();
        v.getChildren().addAll(start,options,quit);
        h.getChildren().add(v);
        h.setAlignment(Pos.CENTER);
        v.setAlignment(Pos.CENTER);
        v.setStyle("-fx-padding: 200px");

        root.getChildren().add(h);
    }

    public static void totalHome(ShipRootPane root){
        initWidgets();
        placeWidgets(root);
    }


    public void setButtonListener(EventHandler<ActionEvent> handler){
        start.setOnAction(handler);
        quit.setOnAction(handler);
        options.setOnAction(handler);
        home.setOnAction(handler);
    }
}
