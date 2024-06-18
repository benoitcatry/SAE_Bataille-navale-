package model;

import boardifier.control.Logger;
import boardifier.model.ElementTypes;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;
import boardifier.model.animation.Animation;
import boardifier.model.animation.AnimationStep;

public class Missille extends GameElement {

    private int number;
    private int color;
    private int idjoueur;

    public Missille(int number, int color, int idjoueur,GameStageModel gameStageModel) {
        super(gameStageModel);
        // registering element types defined especially for this game
        ElementTypes.register("Missile",60);
        type = ElementTypes.getType("Cell");
        this.number = number;
        this.color = color;
        this.idjoueur = idjoueur;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public int getColor() {
        return color;
    }
    public void setColor(int color) {this.color = color;}

    public int getIdjoueur() {return idjoueur;}
    public void setIdjoueur(int idjoueur) {this.idjoueur = idjoueur;}




}
