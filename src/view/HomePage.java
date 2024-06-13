package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomePage {
    private Button start;
    private Button quit;
    private Button options;

    public HomePage() {
        initWidgets();
    }

    private void initWidgets() {
        start = new Button("Start Game");
        quit = new Button("Quit Game");
        options = new Button("Options");
    }

    public void placeWidgets(ShipRootPane root) {
        HBox h = new HBox();
        VBox v = new VBox();
        v.getChildren().addAll(start, options, quit);
        h.getChildren().add(v);
        h.setAlignment(Pos.CENTER);
        v.setAlignment(Pos.CENTER);
        v.setStyle("-fx-padding: 200px");
        root.getChildren().add(h);
    }

    public void setButtonListener(EventHandler<ActionEvent> handler) {
        start.setOnAction(handler);
        quit.setOnAction(handler);
        options.setOnAction(handler);
    }

    public Button getStart() {
        return start;
    }

    public Button getQuit() {
        return quit;
    }

    public Button getOptions() {
        return options;
    }
}