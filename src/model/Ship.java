package model;

import boardifier.model.ContainerElement;
import boardifier.model.GameStageModel;

public class Ship extends ContainerElement {
    private int taille;
    private boolean sens ; // true = V et false = H
    private shipPart[] shipParts;
    private int shipID;
    private static int nbShip = 1;
    private int playerID;
    private boolean couler = false;


    public Ship(int x, int y, int taille, GameStageModel gameStageModel) {
        super("ship", x, y, 1, taille , gameStageModel);
        this.taille =taille;
        this.shipID = nbShip;
        nbShip++;
        setShipParts(gameStageModel);
    }



    public void setShipParts(GameStageModel gameStageModel){
        shipParts = new shipPart[taille];
        int idparts;
        for (int i = 0; i < taille; i++) {
            idparts = shipID*10+(i+1);
            shipParts[i] = new shipPart(idparts, 1,gameStageModel);
        }

    }

    public void setCordonnerShip(int y , int x, char sens){
        if(y+taille>10||x+taille>10){
            System.out.println("imposible de placer le bateau");
        }
        if(sens=='H'){
            for(int i = 0 ; i < taille ; i++){
                shipParts[i].setCordoner(x+i, y);
            }
        }
        if(sens=='V'){
            for(int i = 0 ; i < taille ; i++){
                shipParts[i].setCordoner(x, y+i);
            }
        }
    }

    public void verifcouler(){
        int verif = 0;
        for (int i = 0; i < taille; i++) {
            if( shipParts[i].esttoucher() == true){
                verif++;
            }
        }
        if(verif==taille){
            couler = true;
        }
    }

    public int nbdepartcouler(){
        int nb = 0;
        for (int i = 0; i < taille; i++) {
            if( shipParts[i].esttoucher() == true){
                nb++;
            }
        }
    return nb;
    }




    public void setidplayer(int id){
        playerID =id;
    }

    public int getPlayerID() {
        return playerID;
    }
}
