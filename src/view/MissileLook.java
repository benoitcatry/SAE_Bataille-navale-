package view;

import boardifier.model.GameElement;
import boardifier.view.ElementLook;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.shape.TriangleMesh;
import model.Missille;

public class MissileLook extends ElementLook {

    private Circle circle;
    private int radius;




    public MissileLook( int radius, GameElement element) {
        super(element);
        this.radius = radius;
        render();

    }

    @Override
    public void onSelectionChange() {
        Missille missille = (Missille)getElement();
        if (missille.isSelected()) {
            circle.setStrokeWidth(3);
            circle.setStrokeMiterLimit(10);
            circle.setStrokeType(StrokeType.CENTERED);

        }
        else {
            circle.setStrokeWidth(0);
        }
    }

    @Override
    public void onFaceChange() {
        Missille missille = (Missille) element;
        circle = new Circle(radius);
        if (missille.getColor() == 1) {
            circle.setFill(Color.GREY);
        } else if (missille.getColor() == 2) {
            circle.setFill(Color.RED);
        }
        addShape(circle);

    }

    protected void render() {
        System.out.println("cc renter");
        Missille missille = (Missille) element;
        circle = new Circle();
        circle.setRadius(radius);
        if (missille.getColor() == 1) {
            circle.setFill(Color.GREY);
        } else if (missille.getColor() == 2) {
            circle.setFill(Color.RED);
        }
        addShape(circle);

    }

}
