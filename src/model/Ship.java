package model;

import boardifier.model.ContainerElement;
import boardifier.model.GameStageModel;

public class Ship extends ContainerElement {
    private int taille;
    private boolean sens ; // true = V et false = H
    public shipPart[] shipParts;
    private int shipID;
    private static int nbShip = 1;
    private int playerID;
    private boolean couler = false;


    public Ship(int x, int y, int taille, GameStageModel gameStageModel) {
        super("ship", x, y, taille, 1 , gameStageModel);
        this.taille =taille;
        this.shipID = nbShip;
        nbShip++;

    }


    public void setSens(boolean sens) {
        this.sens = sens;
    }


    public void setShipParts(GameStageModel gameStageModel){
        this.shipParts = new shipPart[taille];
        int idparts;
        for (int i = 0; i < taille; i++) {
            idparts = shipID*10+(i+1);
            shipParts[i] = new shipPart(idparts, 1,gameStageModel);
            //addElement(this.shipParts[i],1,i);
            System.out.println("cree");

        }

    }

    public shipPart[] getshippart(){
        return shipParts;
    }

    public int getTaille() {return taille;}

    public boolean getSens() {return sens;}

    public boolean setCordonnerShip(int y , int x, char sens){
        if(y+taille>10||x+taille>10 || 0>y|| 0>x){
            System.out.println("imposible de placer le bateau");
            return false;
        }
        if(sens=='H'){
            for(int i = 0 ; i < taille ; i++){
                shipParts[i].setCordoner(x+i, y);
            }
            return true;
        }
        if(sens=='V'){
            for(int i = 0 ; i < taille ; i++){
                shipParts[i].setCordoner(x, y+i);
            }
            return true;
        }
        return false;

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

    public int getPartCordonneX(int numeroDeLaPart){
        return shipParts[numeroDeLaPart].getcordonneX();
    }

    public int getPartCordonneY(int numeroDeLaPart){
        return shipParts[numeroDeLaPart].getcordonneY();
    }

    public boolean getcouler() {return couler;}


    public void setidplayer(int id){
        playerID =id;
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getShipID() {
        return shipID;
    }
}
