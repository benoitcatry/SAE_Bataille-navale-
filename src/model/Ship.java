package model;

import boardifier.model.ContainerElement;
import boardifier.model.GameStageModel;

public class Ship extends ContainerElement {
    private int taille;
    private boolean sens ; // true = V et false = H
    public shipPart[] shipParts;
    private int shipID;
    private static int nbShip = 0;
    private int playerID;
    private boolean couler = false;


    public Ship(int x, int y, int taille, GameStageModel gameStageModel) {
        super("ship"+nbShip, x, y, taille, 1 , gameStageModel);
        this.taille =taille;
        this.shipID = nbShip;
        nbShip++;

    }


    public void setSens(boolean sens) {
        this.sens = sens;
    }


    public void setShipParts(GameStageModel gameStageModel){
        this.shipParts = new shipPart[taille];


    }

    public shipPart[] getshippart(){
        return shipParts;
    }

    public int getTaille() {return taille;}

    public boolean getSens() {return sens;}

    public boolean setCordonnerShip(int y , int x, char sens){
        System.out.println(sens +" "+ y + " " + x + " " );
        if(sens == 'V'){
            if (x>10 || y+taille>10 || 0>y || 0>x){
                System.out.println("imposible de placer le bateau");
                return false;
            }else{
                for(int i = 0 ; i < taille ; i++){
                    shipParts[i].setCordoner(x, y+i);
                }
                return true;
            }
        } else if (sens=='H') {
            if (x+taille>10 || y>10 || 0>y || 0>x){
                System.out.println("imposible de placer le bateau");
                return false;
            }else {
                for(int i = 0 ; i < taille ; i++){
                    shipParts[i].setCordoner(x+i, y);
                }
                return true;

            }
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
            for(int i = 0 ; i < taille ; i++){
                shipParts[i].setColors(2);
            }
            couler = true;
            this.setVisible(true);
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
