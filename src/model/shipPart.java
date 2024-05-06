package model;

import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

import java.lang.annotation.ElementType;

public class shipPart extends GameElement {

    private int number;
    private int colors;
    private int X;
    private int Y;

    public shipPart(int number, int color, GameStageModel gameStageModel ) {
        super(gameStageModel);
        // name = "ship" id = 50
        ElementType.register("shippart",50);
        type = ElementType.getType("shippart");
        this.number = number;
        this.colors = color;
    }

    public int getNumber() {
        return number;
    }

    public int getColor() {
        return colors;
    }

    public void setCordoner(int x, int y) {
        X = x;
        Y = y;
    }
}