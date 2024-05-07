package model;

import boardifier.model.ElementTypes;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

public class Missile extends GameElement {

    private int number;
    private int color;
    private int idPlayer;
    private int[][] cordonnervisiter;
    private int nbdetire = 0;


    public Missile(int number, int color,int idPlayer, GameStageModel gameStageModel) {
        super(gameStageModel);
        // registering element types defined especially for this game
        ElementTypes.register("Missile",60);
        type = ElementTypes.getType("Missile");
        this.number = number;
        this.color = color;
        this.idPlayer = idPlayer;
        this.cordonnervisiter = new int[50][2];
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {this.number  = number;}

    public int getColor() {
        return color;
    }
    public void setColor(int color) {this.color = color;}

    public int getIdPlayer() {return idPlayer;}
    public void setIdPlayer(int idPlayer) {this.idPlayer = idPlayer;}

    public int[][] getCordonnervisiter() {return cordonnervisiter;}
    public void setCordonnervisiter(int[][] cordonnervisiter) {this.cordonnervisiter = cordonnervisiter;}
    public void afficheListeCordonner(){
        for(int i = 0; i < 50; i++){
                System.out.println(cordonnervisiter[i][0] + ' ' +cordonnervisiter[i][1] );
        }
    }


    public boolean estVisiter(int x, int y){
        for(int i = 0; i < 50; i++){
            if(cordonnervisiter[i][0] == x && cordonnervisiter[i][1] == y){
                return true;
            }
        }
        return false;
    }

    public void addcordonne(int x, int y){
        cordonnervisiter[nbdetire][0] = x;
        cordonnervisiter[nbdetire][1] = y;
    }


}

