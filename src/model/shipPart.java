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
        this.Y = -10;
        this.X = -10;

    }

    //set et get NUmber et color
    public void setNumber(int number){this.number = number;}
    public int getNumber() {
        return number;
    }
    public void setColors(int colors){this.colors = colors;}
    public int getColor() {
        return colors;
    }


    //set les cordonner pour la partie du bateau
    public void setCordoner(int x, int y) {
        X = x;
        Y = y;
    }


    //set et get des cordonné
    public void setcordonneX(int X) {this.X = X;}
    public void setcordonneY(int Y) {this.Y = Y;}
    public int getcordonneX(){return X;}
    public int getcordonneY() {return Y;}


    //permet de set si toucher ou pas
    public void setToucher(boolean toucher) {this.toucher = toucher;}

    // savoir si toucher true = toucher false = pas toucher
    public boolean esttoucher(){return toucher;}
}