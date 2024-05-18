package control;

import boardifier.control.ActionFactory;
import boardifier.control.ActionPlayer;
import boardifier.control.Controller;
import boardifier.model.*;
import boardifier.model.action.ActionList;
import boardifier.view.View;
import model.BattleShipStageModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class BattleShipControler extends Controller {

    BufferedReader consoleIn;
    boolean firstPlayer;
    int bateaunum =0 ; // joueur bateau 1
    int bateaunum2 =0 ;// bateau du joueur 2
    int count =0;
    boolean quiJouePremier = false;


    public BattleShipControler(Model model, View view, boolean f) {
        super(model, view);
        firstPlayer = f;
    }






    @Override
    public void stageLoop() {
        consoleIn = new BufferedReader(new InputStreamReader(System.in));
        update();
        while(! model.isEndStage()) {
            playTurn();
            endOfTurn();
            update();

        }
        endGame();
    }


    private void playTurn() {
        Player p = model.getCurrentPlayer();
        if(quiJouePremier == false && firstPlayer == true){
            quiJoueEnPremier();
        }

        //il y a du stade a la partie ; la partie 1 ou on pose les bateau | la partie 2 ou on tire
        if(count == 0 || count == 1) { //on pose les bateau
            //verif si bot
            if (p.getType() == Player.COMPUTER) {
                //a modif pour le bot
                System.out.println("COMPUTER PLAYS");
                BattleShipDecider decider = new BattleShipDecider(model,this);
                ActionPlayer play = new ActionPlayer(model, this, decider, null);
                play.start();
            }
            else { // si pas bot alors joueur
                boolean ok = false;

                    while (!ok) {
                    System.out.print(p.getName()+ " > ");
                    try {
                        String line = consoleIn.readLine();
                        if (line.length() == 3) {
                            if (model.getIdPlayer() == 0){ok = analyseAndPlayPose(line, bateaunum);}
                            else {ok = analyseAndPlayPose(line, bateaunum2);}

                        }
                        if (!ok) {
                            System.out.println("incorrect instruction. retry !");
                        }
                    }
                    catch(IOException e) {}
                }

            }   
        } else if (count == 2) {// partie de la partie 2 donc on tire
            if (p.getType() == Player.COMPUTER) {
                //a modif avec le methode des bot
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
                        if (line.length() == 2) {
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
        if ((row<0)||(row>10)) return false;
        if ((col<0)||(col>10)) return false;
        if (model.getIdPlayer() == 0) {
            if (!(gameStage.VerifPasColer(gameStage.getShipsPlayer1(),row,col,gameStage.ShipPlayer1[m].getTaille(), sens))){return false;}

            else {
                gameStage.ShipPlayer1[m].setCordonnerShip(col, row, sens);
                bateaunum++;
            }
        }
        if (model.getIdPlayer() == 1) {
            if (!(gameStage.VerifPasColer(gameStage.getShipsPlayer2(),row,col,gameStage.ShipPlayer2[m].getTaille(), sens))){return false;}
            else {
                gameStage.ShipPlayer2[m].setCordonnerShip(col, row, sens);
                bateaunum2++;
            }
        }

        if(gameStage.ShipPlayer1.length-1 == m){
            count ++;
            System.out.println(count);
        }
        return true;




        }

        //tire le bouler
        private boolean analyseAndPlay (String line){
            BattleShipStageModel gameStage = (BattleShipStageModel) model.getGameStage();            // get the pawn value from the first char

            // get the cords in the board
            int col = (int) (line.charAt(0) - 'A');
            int row = (int) ((line.charAt(1)) - '1');
            // check coords validity
            if ((row<0)||(row>10)) return false;
            if ((col<0)||(col>10)) return false;
            // check if the pawn is still in its pot
            ContainerElement tire = null;
            if (model.getIdPlayer() == 0) {
                tire = gameStage.getBoardPlayer1();
            }
            else {
                tire = gameStage.getBoardPlayer2();
            }
            if (tire.isEmptyAt(0,0)) return false;
            if (model.getIdPlayer() == 0) {
                GameElement misile = tire.getElement(gameStage.getPlayer1ToPlay()-1,0);
                gameStage.getBoardPlayer1().computeValidCells(5);
                if (!gameStage.getBoardPlayer1().canReachCell(row,col)) {return false;}
                gameStage.toucheroupas(gameStage.getShipsPlayer2(), col,row);
                ActionList actions = ActionFactory.generatePutInContainer(model, misile, "BattleBoardPlayer1", row, col);
                actions.setDoEndOfTurn(true);
                ActionPlayer play = new ActionPlayer(model, this, actions);
                play.start();
                return true;
            }else {
                GameElement misile = tire.getElement(gameStage.getPlayer1ToPlay()-1,0);
                gameStage.getBoardPlayer1().computeValidCells(5);
                if (!gameStage.getBoardPlayer1().canReachCell(row,col)) {return false;}
                gameStage.toucheroupas(gameStage.getShipsPlayer2(), col,row);
                ActionList actions = ActionFactory.generatePutInContainer(model, misile, "BattleBoardPlayer1", row, col);
                actions.setDoEndOfTurn(true);
                ActionPlayer play = new ActionPlayer(model, this, actions);
                play.start();
                return true;
            }






        }


        //aide pour les méthode anlyseandplay


    public void quiJoueEnPremier(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qui joue en premier : 1 Joueur 1 \n  " + " 2 Joueur 2\n" + "3 Aleatoire");
        String rep;
        while (true){
            rep = scanner.next();
            if(rep == "1"){
                return;
            }
            if(rep == "2"){
                model.setNextPlayer();
                return;
            }
            if(rep == "3"){
                if(Math.random() > 0.5){ // si > 0.5 joueur 2 si <0.5 joueur 1
                    model.setNextPlayer();
                    return;
                }else {
                    return; // car le joueur de basse est le 1
                }
            }
            else {
                System.out.println("Erreur uniquement  1 2 ou 3 sont accepter ");
            }

        }
    }



    public void endOfTurn() {

        model.setNextPlayer();
        // get the new player to display its name
        Player p = model.getCurrentPlayer();
        BattleShipStageModel stageModel = (BattleShipStageModel) model.getGameStage();
        stageModel.getPlayer2Name().setText(p.getName());
    }
    }

