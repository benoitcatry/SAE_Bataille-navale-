package model;

import boardifier.model.*;

public class BattleShipStageModel extends GameStageModel {

    private int player1toplay;
    private int Player2toplay;

    private BattleBoard Boardplayer1;
    private BattleBoard Boardplayer2;
    private Ship[] ShipPlayer1;
    private Ship[] ShipPlayer2;
    private TextElement player1Name;
    private TextElement player2Name;

    public BattleShipStageModel(String name, Model model) {
        super(name, model);
        player1toplay = 50;
        Player2toplay = 50;

    }

    // set et get pour playerXtoPlay utilisé pour nb de coup réstant
    public void setPlayer1ToPlay(int player1ToPlay) {this.player1toplay = player1ToPlay;}
    public int getPlayer1ToPlay() {return player1toplay;}
    public void setPlayer2ToPlay(int player2ToPlay) {this.Player2toplay = player2ToPlay;}
    public int getPlayer2ToPlay() {return Player2toplay;}

    //set et get de board pour chaque joueur
    public void setBoardPlayer1(BattleBoard boardPlayer1) {this.Boardplayer1 = boardPlayer1;}
    public BattleBoard getBoardPlayer1() {return Boardplayer1;}
    public void setBoardPlayer2(BattleBoard boardPlayer1) {this.Boardplayer2 = boardPlayer1;}
    public BattleBoard getBoardPlayer2() {return Boardplayer2;}


    //set et get du tableau des bateau de chaque joueur
    public void setShipsPlayer1(Ship[] shipsPlayer1) {this.ShipPlayer1 = shipsPlayer1;}
    public Ship[] getShipsPlayer1() {return ShipPlayer1;}
    public void setShipsPlayer2(Ship[] shipsPlayer1) {this.ShipPlayer2 = shipsPlayer1;}
    public Ship[] getShipsPlayer2() {return ShipPlayer2;}


    //set et get du nom de chaque joueur
    public void setPlayer1Name(TextElement player1Name) {this.player1Name = player1Name;}
    public TextElement getPlayer1Name() {return player1Name;}
    public void setPlayer2Name(TextElement player1Name) {this.player2Name = player1Name;}
    public TextElement getPlayer2Name() {return player2Name;}






    //doit gerer la partie pas fini dsl
    private void setupCallbacks(){

        onPutInContainer( (element, gridDest, rowDest, colDest) -> {
            // just check when pawns are put in 3x3 board
                /* if (gridDest != board) return;
                Pawn p = (Pawn) element;
                if (p.getColor() == 0) {
                    player1toplay--;
                }
                else {
                    Player2toplay--;
                }
                if ((player1toplay == 0) && (Player2toplay == 0)) {
                    computePartyResult();
                }*/
            });


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
        return null;
    }


}
