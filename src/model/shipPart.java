package model;

import boardifier.model.ElementTypes;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

public class shipPart extends GameElement {

    private int number;
    private int colors;
    private int X;
    private int Y;
    private boolean toucher = false;

    public shipPart(int number, int color, GameStageModel gameStageModel ) {
        super(gameStageModel);
        // name = "ship" id = 50
        ElementTypes.register("shippart",50);
        type = ElementTypes.getType("shippart");
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

    public void setToucher(boolean toucher) {
        this.toucher = toucher;
    }
    public boolean esttoucher(){return toucher;}
}