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

import java.util.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class BattleShipControler extends Controller {
    int numJ1 =0 ; // joueur bateau 1
    int numJ2 =0 ;// bateau du joueur 2
    int count =0;
    public boolean quiJouePremier = false;
    int tabCordMissileJ1[][];
    int tabCordMissileJ2[][];
    int levelbot1;
    int levelbot2;
    boolean difficulset1 = false ;
    boolean difficulset2 = false ;
    public Set<Point> potentialTargetsJ1;
    Set<Point> potentialTargetsJ2;
    boolean targetModeJ1;
    boolean targetModeJ2;
    boolean lineModeJ1;
    boolean lineModeJ2;
    int[][] gridJ1 = new int[10][10];
    int[][] gridJ2 = new int[10][10];
    List<Point> lineTargetsJ1 = new ArrayList<>();
    List<Point> lineTargetsJ2 = new ArrayList<>();
    public List<Point> hitJ1;
    List<Point> hitJ2;
    List<Point> shipPartJ1;
    List<Point> shipPartJ2;
    boolean inisialise =false;

    int levelbot;
    public ControllerBatleShipMouse controllerBatleShipMouse;


    public BattleShipControler(Model model, View view, ShipRootPane root, HomePage homePage) {
        super(model, view);
        setControlKey(new ControllerBattleShipkey(model, view, this));
        setControlAction(new ControllerBattleShipAction(model, view, this,root, homePage ));
        AudioController audio = new AudioController();
        controllerBatleShipMouse = new ControllerBatleShipMouse(model, view, this, audio);
        setControlMouse(controllerBatleShipMouse);
        potentialTargetsJ1 = new HashSet<>();
        potentialTargetsJ2 = new HashSet<>();
        targetModeJ1 = false;
        targetModeJ2 = false;
        hitJ1 = new ArrayList<>();
        hitJ2 = new ArrayList<>();
        shipPartJ1 = new ArrayList<>();
        shipPartJ2 = new ArrayList<>();
    }


    public void endOfTurn () {
            //set scene
            update();
            // use the default method to compute next player
            model.setNextPlayer();
            // get the new player
            Player p = model.getCurrentPlayer();
            // change the text of the TextElement
            BattleShipStageModel stageModel = (BattleShipStageModel) model.getGameStage();
            if (p.getType() == Player.HUMAN){
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
            }
            if (p.getType() == Player.COMPUTER) {
                System.out.println("count "+count+" "+stageModel.ShipPlayer2.length+" numJ1 "+numJ1);
                if (numJ1==stageModel.ShipPlayer1.length) {
                    ActionList actions = new ActionList();
                    actions.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, this, actions);
                    play.start();
                }
                if (count<stageModel.ShipPlayer2.length) {
                    if (model.getIdPlayer() == 0) {
                        player1playmode0(stageModel);
                        System.out.println("test");
                        BattleShipDecider decider = new BattleShipDecider(model, this, 0, levelbot1);
                        decider.placeAllShips(numJ1);
                        System.out.println("test2");
                        numJ1++;
                    } else {
                        player2playmode0(stageModel);
                        BattleShipDecider decider = new BattleShipDecider(model, this, 1, levelbot1);
                        decider.placeAllShips(numJ2);
                        numJ2++;

                    }
                }else {
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
    }