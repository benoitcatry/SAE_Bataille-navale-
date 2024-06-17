package view;

import boardifier.model.GameElement;
import boardifier.view.SpriteImageLook;
import boardifier.view.SpriteLook;
import boardifier.view.ElementLook;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import model.shipPart;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;


public class ShipPartLook extends ElementLook {

    private Rectangle square;
    private int dimension;



    public ShipPartLook(int dimension,GameElement element) {
        super(element);
        this.dimension = dimension;
        render();
    }

    @Override
    public void onSelectionChange() {
        shipPart shipPart = (shipPart)getElement();
        if (shipPart.isSelected()) {
            square.setStrokeWidth(3);
            square.setStrokeMiterLimit(10);
            square.setStrokeType(StrokeType.CENTERED);
            square.setStroke(Color.valueOf("0x333333"));

        }
        else {
            square.setStrokeWidth(0);
        }
    }

    public void render(){
        shipPart shipPart = (shipPart)element;
        square = new Rectangle();
        square.setHeight(dimension);
        square.setWidth(dimension);
        if (shipPart.getColor() == 1 && shipPart.getIdplayer() ==0){ // neutre
            square.setFill(Color.RED);
        } else if (shipPart.getColor() == 1 && shipPart.getIdplayer() ==1) {
            square.setFill(Color.BLUE);
        } else if (shipPart.getColor() == 2){ // COULER
            square.setFill(Color.GREEN);

        } else {
            square.setFill(Color.BLACK);

        }
        System.out.println(square.getX());
        square.setX(-24);
        square.setY(-24);
        addShape(square);
    }
}
