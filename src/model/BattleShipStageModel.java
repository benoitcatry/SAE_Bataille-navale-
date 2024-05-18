package model;

import boardifier.model.*;

public class BattleShipStageModel extends GameStageModel {

    private int player1toplay;
    private int Player2toplay;

    private BattleBoard Boardplayer1;
    private BattleBoard Boardplayer2;
    public Ship[] ShipPlayer1;
    public Ship[] ShipPlayer2;
    private TextElement player1Name;
    private TextElement player2Name;
    private Missille[] MissileJoueur1;
    private Missille[] MissileJoueur2;
    private MissileConteneur conteneurjoueur1;
    private MissileConteneur conteneurjoueur2;
    private TextElement InfoPartie;

    public BattleShipStageModel(String name, Model model) {
        super(name, model);
        player1toplay = 50;
        Player2toplay = 50;
        setupCallbacks();

    }

    public void setInfoPartie(TextElement infopartie){this.InfoPartie = infopartie;}
    public TextElement getInfoPartie(){return this.InfoPartie;}


    //conteneur de misile
    public void setConteneurjoueur1(MissileConteneur conteneurjoueur1) {this.conteneurjoueur1 = conteneurjoueur1; addContainer(this.conteneurjoueur1);}
    public MissileConteneur getConteneurjoueur1 () {return conteneurjoueur1;}
    public void setConteneurjoueur2(MissileConteneur conteneurjoueur2) {this.conteneurjoueur2 = conteneurjoueur1; addContainer(this.conteneurjoueur1);}
    public MissileConteneur getConteneurjoueur2(){return conteneurjoueur2;}

    // set et get pour playerXtoPlay utilisé pour nb de coup réstant
    public void setPlayer1ToPlay(int player1ToPlay) {this.player1toplay = player1ToPlay;}
    public int getPlayer1ToPlay() {return player1toplay;}
    public void setPlayer2ToPlay(int player2ToPlay) {this.Player2toplay = player2ToPlay;}
    public int getPlayer2ToPlay() {return Player2toplay;}

    //set et get de board pour chaque joueur
    public void setBoardPlayer1(BattleBoard boardPlayer1) {this.Boardplayer1 = boardPlayer1; addContainer(boardPlayer1);}
    public BattleBoard getBoardPlayer1() {return Boardplayer1;}
    public void setBoardPlayer2(BattleBoard boardPlayer2) {this.Boardplayer2 = boardPlayer2; addContainer(boardPlayer2);}
    public BattleBoard getBoardPlayer2() {return Boardplayer2;}


    //set et get du tableau des bateau de chaque joueur
    public void setShipsPlayer1(Ship[] shipsPlayer1) {this.ShipPlayer1 = shipsPlayer1;
        for(int i = 0; i < shipsPlayer1.length ; i++){addContainer(shipsPlayer1[i]);}}
    public Ship[] getShipsPlayer1() {return ShipPlayer1;}
    public void setShipsPlayer2(Ship[] shipsPlayer2) {this.ShipPlayer2 = shipsPlayer2;
        for(int i = 0; i < shipsPlayer2.length ; i++){addContainer(shipsPlayer2[i]);}}
    public Ship[] getShipsPlayer2() {return ShipPlayer2;}


    //set et get du nom de chaque joueur
    public void setPlayer1Name(TextElement player1Name) {this.player1Name = player1Name; addElement(player1Name);}
    public TextElement getPlayer1Name() {return player1Name;}
    public void setPlayer2Name(TextElement player1Name) {this.player2Name = player1Name; addElement(player2Name);}
    public TextElement getPlayer2Name() {return player2Name;}

    //Set et get des cellule pour les board
    public Missille[] getMissileJoueur1() {return MissileJoueur1;}
    public void setMissileJoueur1(Missille[] m){
        MissileJoueur1 = m;
        for(int i = 0; i < MissileJoueur1.length ; i++){addElement(MissileJoueur1[i]);}}
    public Missille[] getMissileJoueur2() {return MissileJoueur2;}
    public void setMissileJoueur2(Missille[] m){
        MissileJoueur2 = m;
        for(int i = 0; i < MissileJoueur2.length ; i++){addElement(MissileJoueur2[i]);}}

    //verif que les cordonnée des ship ne se colle pas : sur les coter et les coin
    //elle est a vomir

    public boolean VerifPasColer(Ship[] ships, int xNewship, int yNewShip, int tailleNewShip, char sens){
        int verif = 0;
        for(int i =0; i<tailleNewShip; i++){
            for (Ship ship : ships) {
                //true =  V et false = H
                if ( sens == 'V') {
                    for(int g = 0; g < ship.getTaille(); g++){
                        for(int j =-1; j<=ship.getTaille()+1; j++) {
                            for(int k =-1; k<1; k++) {
                                if (ship.getPartCordonneY(g)+j ==yNewShip && ship.getPartCordonneX(g)+k ==xNewship ){
                                    return false;
                                }else{verif++;}
                            }
                        }
                    }
                } else if (sens == 'H') {
                    for(int g = 0; g < ship.getTaille(); g++){
                        for(int j =-1; j<=ship.getTaille()+1; j++) {
                            for(int k =-1; k<1; k++) {
                                if (ship.getPartCordonneY(g)+k ==yNewShip && ship.getPartCordonneX(g)+j ==xNewship ){
                                    return false;
                                }else{verif++;}
                            }
                        }
                    }

                }
            }
        }
        return true;
    }

    public boolean toucheroupas(Ship[] ships, int x , int y){
        for(int i = 0; i < ships.length; i++){
            for(int j= 0; j < ships[i].shipParts.length; j++ ){
                if (ships[i].shipParts[j].esttoucher() == false){
                    if(ships[i].shipParts[j].getcordonneX() == x && ships[i].shipParts[j].getcordonneY() == y){
                        ships[i].shipParts[j].setToucher(true);
                        return true;
                    }
                }
            }
        }
        return false;
    }




    //doit gerer la partie pas fini dsl
    private void setupCallbacks(){
 
        onPutInContainer( (element, gridDest, rowDest, colDest) -> {
            // just check when pawns are put in 3x3 board
                 if (gridDest != Boardplayer1 || gridDest!= Boardplayer2) return;
                Missille m = (Missille) element;
                if (m.getIdjoueur() == 1) {
                    player1toplay--;
                }
                else {
                    Player2toplay--;
                }
                if ((player1toplay == 0) && (Player2toplay == 0 ) ||(toutShipCouler(ShipPlayer1)|| toutShipCouler(ShipPlayer2))) {
                    computePartyResult();
                }
            });


    }


    public boolean toutShipCouler(Ship[] ships){

        for (Ship ship : ships) {
            if (ship.getcouler() ==false) {
                return false;
            }
        }
        return true;
    }




    //méthode pour regarder qui gagne dans la partie
    private void computePartyResult(){
    int shippartcoulerplayer1 =0;
    int shippartcoulerplayer2 = 0;
    for(Ship ship : ShipPlayer1){
        shippartcoulerplayer1 += ship.nbdepartcouler();
    }
    for(Ship ship : ShipPlayer2){
        shippartcoulerplayer2 += ship.nbdepartcouler();
    }
    if(shippartcoulerplayer1 > shippartcoulerplayer2){
        System.out.println("le joueur :" + player1Name + " a gagner avec : "+ shippartcoulerplayer1 + "touche.");
        model.setIdWinner(1);
        model.stopStage();
    } else if (shippartcoulerplayer2 > shippartcoulerplayer1) {
        System.out.println("le joueur :" + player2Name + " a gagner avec : "+ shippartcoulerplayer2 + "touche.");
        model.setIdWinner(2);
        model.stopStage();
    }else{
        System.out.println("Match null");
    }
    }


    //truc obligatoir c'est pas a quoi il sert mais il est la
    @Override
    public StageElementsFactory getDefaultElementFactory() {
        return new BattleShipStageFactory(this);
    }


}
