package model;

import boardifier.model.ElementTypes;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

public class Misille extends GameElement {

    private int number;
    private int color;
    private int idjoueur;

    public Misille(int number, int color, GameStageModel gameStageModel) {
        super(gameStageModel);
        // registering element types defined especially for this game
        ElementTypes.register("Cell",50);
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

    public int getIdjoueur() {return idjoueur;}



}
