package view;

import boardifier.view.RootPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ShipRootPane extends RootPane {
    public void createDefaultGroup() {
        Rectangle frame = new Rectangle(600, 100, Color.LIGHTGREY);
        Text text = new Text("Playing Battleship");
        text.setFont(new Font(15));
        text.setFill(Color.BLACK);
        text.setX(10);
        text.setY(50);
        // put shapes in the group
        group.getChildren().clear();
        group.getChildren().addAll(frame, text);
    }
}
