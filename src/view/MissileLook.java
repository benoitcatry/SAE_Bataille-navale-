package view;

import boardifier.model.GameElement;
import boardifier.view.ElementLook;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.TriangleMesh;
import model.Missille;

public class MissileLook extends ElementLook {

    Circle circle;

    public MissileLook(GameElement element) {
        super(element);
        circle = new Circle();
    }

    protected void render() {

        Missille missille = (Missille) element;
        if (missille.getColor() == 1) {
            circle.setFill(Color.BLACK);
            circle.setRadius(1.5);
        }
        if (missille.getColor() == 2) {
            circle.setFill(Color.RED);
            circle.setRadius(1.5);
        }

    }
}
