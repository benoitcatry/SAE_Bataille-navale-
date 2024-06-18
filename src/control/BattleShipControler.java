package control;

import boardifier.control.ActionFactory;
import boardifier.control.ActionPlayer;
import boardifier.control.Controller;
import boardifier.control.Logger;
import boardifier.model.*;
import boardifier.model.action.ActionList;
import boardifier.view.View;
import model.BattleShipStageModel;
import model.Ship;
import view.HomePage;
import view.ShipRootPane;
import model.shipPart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;


public class BattleShipControler extends Controller {

    int levelbot;
    private final ControllerBatleShipMouse controllerBatleShipMouse;


    public BattleShipControler(Model model, View view, ShipRootPane root, HomePage homePage, AudioController audio) {
        super(model, view);
        setControlKey(new ControllerBattleShipkey(model, view, this));
        setControlAction(new ControllerBattleShipAction(model, view, this,root, homePage ));
        controllerBatleShipMouse = new ControllerBatleShipMouse(model, view, this, audio);
        setControlMouse(controllerBatleShipMouse);


    }

        public void endOfTurn () {
            //set scene
            update();
            udateellement();
            // use the default method to compute next player
            model.setNextPlayer();
            // get the new player
            Player p = model.getCurrentPlayer();
            // change the text of the TextElement
            BattleShipStageModel stageModel = (BattleShipStageModel) model.getGameStage();
            if (model.getIdPlayer() == 0) {
                if (controllerBatleShipMouse.mode() == 0) {
                    player1playmode0(stageModel);
                } else {
                    player1playmode1(stageModel);
                }
            } else {
                if (controllerBatleShipMouse.mode() == 0) {
                    player2playmode0(stageModel);
                } else {
                    player2playmode1(stageModel);
                }
            }


            if (p.getType() == Player.COMPUTER) {
                if (levelbot == 1) {
                    Logger.debug("COMPUTER PLAYS");
                    BattleShipDecider decider = new BattleShipDecider(model, this, 1, 1);
                    ActionPlayer play = new ActionPlayer(model, this, decider, null);
                    play.start();
                } else {
                    Logger.debug("COMPUTER PLAYS");
                    BattleShipDecider decider = new BattleShipDecider(model, this, 1, 2);
                    ActionPlayer play = new ActionPlayer(model, this, decider, null);
                    play.start();
                }

            } else {

                Logger.debug("PLAYER PLAYS");

            }
        }



        public void setlevelbot ( int level){
            if (level > 2 || level < 0) {
                return;
            } else {
                levelbot = level;
            }
        }

        public void player1playmode0 (BattleShipStageModel stageModel){

            stageModel.setinvisiblebateau(stageModel.getShipsPlayer2());
            stageModel.setjoueur2invisible();
            stageModel.setvisiblej1();
            stageModel.setvisiblebateau(stageModel.getShipsPlayer1());
        }

        public void player2playmode0 (BattleShipStageModel stageModel){

            stageModel.setinvisiblebateau(stageModel.getShipsPlayer1());
            stageModel.setjoueur1invisible();
            stageModel.setvisiblej2();
            stageModel.setvisiblebateau(stageModel.getShipsPlayer2());
        }

        public void initmode1 (BattleShipStageModel stageModel){

            stageModel.setinvisiblebateau(stageModel.getShipsPlayer1());
            stageModel.setjoueur1invisible();
            stageModel.setinvisiblebateau(stageModel.getShipsPlayer2());
            stageModel.setjoueur2invisible();
        }

        public void player1playmode1 (BattleShipStageModel stageModel){
            stageModel.setinvisiblebateau(stageModel.getShipsPlayer1());
            stageModel.setjoueur2invisible();
            stageModel.setvisiblebateau(stageModel.getShipsPlayer2());
            stageModel.setvisiblej1();
        }

        public void player2playmode1 (BattleShipStageModel stageModel){
            stageModel.setinvisiblebateau(stageModel.getShipsPlayer2());
            stageModel.setjoueur1invisible();
            stageModel.setvisiblebateau(stageModel.getShipsPlayer1());
            stageModel.setvisiblej2();
        }

        public void udateellement(){
            BattleShipStageModel stageModel = (BattleShipStageModel) model.getGameStage();

            for(int i =0 ; i < stageModel.ShipPlayer1.length ; i++){
                for(int j =0 ; j < stageModel.ShipPlayer1[i].shipParts.length ; j++){
                    stageModel.ShipPlayer1[i].shipParts[j].addChangeFaceEvent();
                    stageModel.ShipPlayer2[i].shipParts[j].addChangeFaceEvent();
                }
            }
            for(int i =0 ; i < stageModel.getMissileJoueur1().length ; i++){
                stageModel.getMissileJoueur1()[i].addChangeFaceEvent();
                stageModel.getMissileJoueur2()[i].addChangeFaceEvent();
            }

        }

        public void start(BattleShipStageModel stageModel){
            stageModel.setinvisiblebateau(stageModel.getShipsPlayer2());
            stageModel.setjoueur1invisible();
            stageModel.setjoueur2invisible();
            stageModel.setinvisiblebateau(stageModel.getShipsPlayer1());
        }



    }