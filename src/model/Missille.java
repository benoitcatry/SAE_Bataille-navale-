package model;

import boardifier.model.ElementTypes;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

public class Missille extends GameElement {

    private int number;
    private int color;
    private int idjoueur;

    public Missille(int number, int color, GameStageModel gameStageModel) {
        super(gameStageModel);
        // registering element types defined especially for this game
        System.out.println("arrive à register");
        ElementTypes.register("Missile",60);
        System.out.println("register ok");
        type = ElementTypes.getType("Cell");
        this.number = number;
        this.color = color;
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
