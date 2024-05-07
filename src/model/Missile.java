package model;

import boardifier.model.ElementTypes;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

public class Missile extends GameElement {

    private int number;
    private int color;
    private int idPlayer;


    public Missile(int number, int color,int idPlayer, GameStageModel gameStageModel) {
        super(gameStageModel);
        // registering element types defined especially for this game
        ElementTypes.register("Missile",60);
        type = ElementTypes.getType("Missile");
        this.number = number;
        this.color = color;
        this.idPlayer = idPlayer;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {this.number  = number;}

    public int getColor() {
        return color;
    }
    pu


    public void setColor(int color) {
        this.color = color;
    }
}

