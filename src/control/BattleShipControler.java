package control;

import boardifier.control.ActionFactory;
import boardifier.control.ActionPlayer;
import boardifier.control.Controller;
import boardifier.model.GameElement;
import boardifier.model.ContainerElement;
import boardifier.model.Model;
import boardifier.model.Player;
import boardifier.model.action.ActionList;
import boardifier.view.View;
import model.BattleShipStageModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BattleShipControler extends Controller {

    BufferedReader consoleIn;
    boolean firstPlayer;
    int stadeDeLaPartie; // si 1 on pose le bateau Si 2 feu a vaulonté
    int modedejeux ; // pour savoir qui joue le bot toujour quand 2
    int count =0;

    public BattleShipControler(Model model, View view) {
        super(model, view);
        firstPlayer = true;
    }






    @Override
    public void stageLoop() {
        consoleIn = new BufferedReader(new InputStreamReader(System.in));
        update();
        while(! model.isEndStage()) {
            playTurn();
            endOfTurn();
            //update();
        }
        endGame();
    }


    private void playTurn() {
        Player p = model.getCurrentPlayer();
        //verifie si c'est un bot qui joue
        if(stadeDeLaPartie == 1) { //on pose les bateau
            if (p.getType() == Player.COMPUTER) {
                System.out.println("COMPUTER PLAYS");
                BattleShipDecider decider = new BattleShipDecider(model,this);
                ActionPlayer play = new ActionPlayer(model, this, decider, null);
                play.start();
            }
            else {
                boolean ok = false;
                for(int i = 0; i < modedejeux; i++) {
                    while (!ok) {
                    System.out.print(p.getName()+ " > ");
                    try {
                        String line = consoleIn.readLine();
                        if (line.length() == 3) {
                            ok = analyseAndPlayPose(line, i);
                        }
                        if (!ok) {
                            System.out.println("incorrect instruction. retry !");
                        }
                    }
                    catch(IOException e) {}
                }}

            }   
        } else if (count == 2) {
            if (p.getType() == Player.COMPUTER) {
                System.out.println("COMPUTER PLAYS");
                BattleShipDecider decider = new BattleShipDecider(model,this);
                ActionPlayer play = new ActionPlayer(model, this, decider, null);
                play.start();
            }
            else {
                boolean ok = false;
                while (!ok) {
                    System.out.print(p.getName()+ " > ");
                    try {
                        String line = consoleIn.readLine();
                        if (line.length() == 3) {
                            ok = analyseAndPlay(line);
                        }
                        if (!ok) {
                            System.out.println("incorrect instruction. retry !");
                        }
                    }
                    catch(IOException e) {}
                }
            }
        }

    }

    //posse les bateau
    private boolean analyseAndPlayPose(String line, int m) {
        BattleShipStageModel gameStage = (BattleShipStageModel) model.getGameStage();
        char sens = Character.toUpperCase(line.charAt(0));
        if (sens != 'H' && sens != 'V') {return false;}
        int col = (int) Character.toUpperCase((line.charAt(1) - 'A'));
        int row = (int) (line.charAt(2) - '1');
        if ((row<0)||(row>9)) return false;
        if ((col<0)||(col>9)) return false;
        if (model.getIdPlayer() == 0) {
            if (!(gameStage.VerifPasColer(gameStage.getShipsPlayer1(),row,col,gameStage.ShipPlayer1[m].getTaille(), sens))){return false;}
            else {
                gameStage.ShipPlayer1[m].setCordonnerShip(col, row, sens);
            }
        }
        if (model.getIdPlayer() == 1) {
            if (!(gameStage.VerifPasColer(gameStage.getShipsPlayer2(),row,col,gameStage.ShipPlayer2[m].getTaille(), sens))){return false;}
            else {
                gameStage.ShipPlayer2[m].setCordonnerShip(col, row, sens);
            }
        }
        if(gameStage.ShipPlayer1.length == m){
            model.setNextPlayer();
            count ++;
            return true;
        }
        return true;




        }

        //tire le bouler
        private boolean analyseAndPlay (String line){
            BattleShipStageModel gameStage = (BattleShipStageModel) model.getGameStage();            // get the pawn value from the first char

            // get the cords in the board
            int col = (int) (line.charAt(1) - 'A');
            int row = (int) (line.charAt(2) - '1');
            // check coords validity
            if ((row<0)||(row>9)) return false;
            if ((col<0)||(col>9)) return false;
            // check if the pawn is still in its pot
            GameElement tire = null;
            if (model.getIdPlayer() == 0) {
                //tire = gameStage.getMissilejoueur1();
            }
            else {
                //tire = gameStage.getMissilejoueur2();
            }
            /*if (pot.isEmptyAt(pawnIndex,0)) return false;
            GameElement pawn = pot.getElement(pawnIndex,0);
            // compute valid cells for the chosen pawn
            gameStage.getBoard().setValidCells(pawnIndex+1);
            if (!gameStage.getBoard().canReachCell(row,col)) return false;

            ActionList actions = ActionFactory.generatePutInContainer(model, pawn, "holeboard", row, col);
            actions.setDoEndOfTurn(true); // after playing this action list, it will be the end of turn for current player.
            ActionPlayer play = new ActionPlayer(model, this, actions);
            play.start();

             */
            return true;


        }


        //aide pour les méthode anlyseandplay

    }

