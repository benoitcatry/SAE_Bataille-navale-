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

    public int getPlayer1toplay() {
        return player1toplay;
    }


    
    public int getPlayer2toplay() {
        return Player2toplay;
    }



    private void setupCallbacks(){

    }

    //regarde qui gagne
    private void computePartyResult(){

    }




}
