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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class BattleShipControler extends Controller {

    int levelbot;



    public BattleShipControler(Model model, View view) {
        super(model, view);
        setControlKey(new ControllerBattleShipkey(model, view, this));
        setControlMouse(new ControllerBatleShipMouse(model, view, this));
        //setControlAction (new ControllerBattleShipAction(model, view, this));
        
    }

    public void endOfTurn() {
        // use the default method to compute next player
        model.setNextPlayer();
        // get the new player
        Player p = model.getCurrentPlayer();
        // change the text of the TextElement
        BattleShipStageModel stageModel = (BattleShipStageModel) model.getGameStage();

        if (p.getType() == Player.COMPUTER) {
            if(levelbot ==1){
                Logger.debug("COMPUTER PLAYS");
                BattleShipDecider decider = new BattleShipDecider(model,this,1,1);
                ActionPlayer play = new ActionPlayer(model, this, decider, null);
                play.start();
            } else {
                Logger.debug("COMPUTER PLAYS");
                BattleShipDecider decider = new BattleShipDecider(model,this,1,2);
                ActionPlayer play = new ActionPlayer(model, this, decider, null);
                play.start();
            }

        }
        else {
            Logger.debug("PLAYER PLAYS");
        }
    }


    public void setlevelbot(int level){
        if(level > 2|| level <0){
            return;
        }
        else {
            levelbot = level;
        }
    }

    public void setfirstplayer(int numerojoueur){
        if (numerojoueur == 1){
            return;
        } else if (numerojoueur==2) {
            model.setNextPlayer();
        }
    }

  }