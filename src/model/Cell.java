package model;

import boardifier.model.ElementTypes;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

public class Cell extends GameElement {

    private int number;
    private int color;
    public static int PAWN_BLACK = 0;
    public static int PAWN_RED = 1;

    public Cell(int number, int color, GameStageModel gameStageModel) {
        super(gameStageModel);
        // registering element types defined especially for this game
        ElementTypes.register("Cell",60);
        type = ElementTypes.getType("Cell");
        this.number = number;
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public int getColor() {
        return color;
    }


    public void setColor(int color) {
        this.color = color;
    }
}

