package view;

import boardifier.model.GameElement;
import boardifier.view.SpriteImageLook;
import boardifier.view.SpriteLook;
import boardifier.view.ElementLook;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.shipPart;


public class ShipPartLook extends ElementLook {

    private Circle circle;

    public ShipPartLook(int radius,GameElement element) {
        super(element);
        shipPart shipPart = (shipPart) element;
        circle = new Circle();
    }

    public void render(){
        shipPart shipPart = (shipPart)element;
        if (shipPart.getColor() == 1 && shipPart.getIdplayer() ==0){ // neutre
            circle.setFill(Color.BLACK);
        } else if (shipPart.getColor() == 1 && shipPart.getIdplayer() ==1) {
            circle.setFill(Color.RED);
        } else if (shipPart.getColor() == 2){ // COULER
            circle.setFill(Color.GREEN);

        } else {
            circle.setFill(Color.BLACK);

        }

    }
}
