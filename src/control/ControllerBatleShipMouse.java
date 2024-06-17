package control;

import boardifier.control.*;
import boardifier.model.*;
import boardifier.model.action.ActionList;
import boardifier.model.animation.AnimationTypes;
import boardifier.view.GridLook;
import boardifier.view.View;
import javafx.event.*;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import model.BattleBoard;
import model.BattleShipStageModel;
import model.shipPart;
import model.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ControllerBatleShipMouse extends ControllerMouse implements EventHandler<MouseEvent> {

    int numJ1 = 0; // joueur bateau 1
    int numJ2 = 0;// bateau du joueur 2
    int tabCordMissileJ1[][];
    int tabCordMissileJ2[][];
    public int mode; // 0 si on posse les bateau, si 1 tire de bombe
    int clic1=0;
    int[] anciantclic;
    boolean inisialise =false;
    AudioController audio;

    public ControllerBatleShipMouse(Model model, View view, Controller control, AudioController audio) {
        super(model, view, control);
        this.audio = audio;
    }

    public void handle(MouseEvent event) {


        if (!model.isCaptureMouseEvent()) return;
        BattleShipStageModel stageModel = (BattleShipStageModel) model.getGameStage();
        Coord2D clic = new Coord2D(event.getSceneX(), event.getSceneY());
        List<GameElement> list = control.elementsAt(clic);
        if(!inisialise){
            inisialise();
            inisialise=true;
            ActionList actions = new ActionList();
            actions.setDoEndOfTurn(true);
            ActionPlayer play = new ActionPlayer(model, control, actions);
            model.setNextPlayer();
            stageModel.startinvisible();
            play.start();

        }

        
        //si on posse la bateau
        if(numJ1==stageModel.getShipsPlayer1().length && numJ2==stageModel.getShipsPlayer2().length) {
            mode=1;
            numJ2=0;
            numJ1=0;
        }

        System.out.println(model.getIdPlayer());
        if(mode==0) {
            //quelle joueur
            if (model.getIdPlayer() == 0) {

                boolean boardClicked = false;
                for (GameElement element : list) {
                    if (element == stageModel.getBoardPlayer1()) {
                        boardClicked = true;
                        break;
                    }
                }
                if (!boardClicked) return;
                if (clic1 == 0) {
                    BattleBoard board = stageModel.getBoardPlayer1();
                    GridLook lookBoard = (GridLook) control.getElementLook(board);
                    anciantclic = lookBoard.getCellFromSceneLocation(clic);
                    clic1 = 1;
                    return;
                } else if (clic1 == 1) {
                    BattleBoard board = stageModel.getBoardPlayer1();
                    GridLook lookBoard = (GridLook) control.getElementLook(board);
                    int[] dest = lookBoard.getCellFromSceneLocation(clic);
                    char sens = sens(anciantclic[0], anciantclic[1], dest[0], dest[1]);
                    if (!(stageModel.Verifpeutetreposer(stageModel.ShipPlayer1, anciantclic[0], anciantclic[1], stageModel.ShipPlayer1[numJ1].getTaille(), sens))) {
                        clic1 = 0;
                        return;
                    } else {
                        stageModel.ShipPlayer1[numJ1].setCordonnerShip(anciantclic[0], anciantclic[1], sens);
                        placeToutPartShip(0, stageModel, anciantclic[0], anciantclic[1], sens);
                        numJ1++;
                        clic1 = 0;

                    }

                }
                if (numJ1 == stageModel.getShipsPlayer1().length) {
                    shipoutdesk(stageModel.ShipPlayer1);
                    ActionList actions = new ActionList();
                    actions.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actions);
                    play.start();

                }

            }


            //player 2
            else {

                boolean boardClicked = false;
                for (GameElement element : list) {
                    if (element == stageModel.getBoardPlayer2()) {
                        boardClicked = true;
                        break;
                    }
                }
                if (!boardClicked) return;
                if (clic1 == 0) {
                    BattleBoard board = stageModel.getBoardPlayer2();
                    GridLook lookBoard = (GridLook) control.getElementLook(board);
                    anciantclic = lookBoard.getCellFromSceneLocation(clic);
                    clic1 = 1;
                    return;
                } else if (clic1 == 1) {
                    BattleBoard board = stageModel.getBoardPlayer2();
                    GridLook lookBoard = (GridLook) control.getElementLook(board);
                    int[] dest = lookBoard.getCellFromSceneLocation(clic);
                    char sens = sens(anciantclic[0], anciantclic[1], dest[0], dest[1]);
                    if (!(stageModel.Verifpeutetreposer(stageModel.ShipPlayer2, anciantclic[0], anciantclic[1], stageModel.ShipPlayer2[numJ2].getTaille(), sens))) {
                        clic1 = 0;
                        return;
                    } else {
                        stageModel.ShipPlayer2[numJ2].setCordonnerShip(anciantclic[0], anciantclic[1], sens);
                        placeToutPartShip(1, stageModel, anciantclic[0], anciantclic[1], sens);
                        numJ2++;
                        clic1 = 0;

                    }

                }
                if (numJ2 == stageModel.getShipsPlayer2().length) {
                    shipoutdesk(stageModel.ShipPlayer2);
                    ActionList actions = new ActionList();
                    actions.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actions);
                    play.start();

                }

            }


        }
        //fin pose bateau




        else if (mode==1) {
            ContainerElement tire = null;
            if(model.getIdPlayer()==0){

                boolean boardClicked = false;
                for (GameElement element : list) {
                    if (element == stageModel.getBoardPlayer1()) {
                        boardClicked = true; break;
                    }
                }
                if (!boardClicked) return;
                tire = stageModel.getStockMissileJ1();
                GameElement misile = tire.getElement(0,0);
                BattleBoard board = stageModel.getBoardPlayer1();
                GridLook lookBoard = (GridLook) control.getElementLook(board);
                int[] dest = lookBoard.getCellFromSceneLocation(clic);
                if (board.canReachCell(dest[0], dest[1])) {
                    audio.playTir();
                    if(stageModel.toucheroupas(stageModel.getShipsPlayer2(),dest[0], dest[1])){
                        audio.playToucher();
                        for(int i = 0; i <stageModel.getMissileJoueur1().length; i++){
                            if (stageModel.getMissileJoueur1()[i] == misile){
                                stageModel.getMissileJoueur1()[i].setColor(2);

                            }
                        }
                        tabCordMissileJ1[numJ1][0]= dest[0];
                        tabCordMissileJ1[numJ1][1]= dest[1];
                        numJ1++;
                    }else {audio.playRater();}
                    ActionList actions = ActionFactory.generatePutInContainer(control,model, misile, "boardplayer1", dest[0], dest[1], AnimationTypes.MOVE_TELEPORT, 10);
                    actions.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actions);
                    play.start();

                }
                
            }
            else if (model.getIdPlayer()==1) {

                boolean boardClicked = false;
                for (GameElement element : list) {
                    if (element == stageModel.getBoardPlayer2()) {
                        boardClicked = true; break;
                    }
                }
                if (!boardClicked) return;
                tire = stageModel.getStockMissileJ2();
                GameElement misile = tire.getElement(0,0);
                BattleBoard board = stageModel.getBoardPlayer2();
                GridLook lookBoard = (GridLook) control.getElementLook(board);
                int[] dest = lookBoard.getCellFromSceneLocation(clic);
                if (board.canReachCell(dest[0], dest[1])) {
                    if(stageModel.toucheroupas(stageModel.getShipsPlayer1(),dest[0],dest[1])){
                        for(int i = 0; i <stageModel.getMissileJoueur2().length; i++){
                            if (stageModel.getMissileJoueur1()[i] == misile){
                                stageModel.getMissileJoueur1()[i].setColor(2);
                            }
                        }
                        tabCordMissileJ2[numJ2][0]= dest[0];
                        tabCordMissileJ2[numJ2][1]= dest[1];
                            numJ2++;
                    }
                    ActionList actions = ActionFactory.generatePutInContainer(control,model, misile, "boardplayer2", dest[0], dest[1], AnimationTypes.MOVE_TELEPORT, 10);
                    actions.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actions);
                    play.start();


                }
            }
        }
    }


    /*
     * permet de placer (graphiquement) les bateau sur la grille
     */
    public void placeToutPartShip(int idplayer, BattleShipStageModel stageModel, int X, int Y, char sens) {
        ContainerElement bateau = null;
        if (idplayer == 0) {
            bateau = stageModel.getship(numJ1, stageModel.ShipPlayer1);
            for (int i = 0; i < bateau.getNbRows(); i++) {
                GameElement shippart = bateau.getElement(i, 0);
                if (sens == 'H') {
                    ActionList actions = ActionFactory.generatePutInContainer(control, model, shippart, "boardplayer1", X+i, Y );
                    ActionPlayer play = new ActionPlayer(model, control, actions);
                    play.start();
                } else if (sens == 'V') {
                    ActionList actions = ActionFactory.generatePutInContainer(control, model, shippart, "boardplayer1", X , Y+i);
                    ActionPlayer play = new ActionPlayer(model, control, actions);
                    play.start();

                } else {
                    return;
                }

            }

        } else if (idplayer == 1) {
            bateau = stageModel.getship(numJ2, stageModel.ShipPlayer2);
            for (int i = 0; i < bateau.getNbRows(); i++) {
                GameElement shippart = bateau.getElement(i, 0);
                if (sens == 'H') {
                    ActionList actions = ActionFactory.generatePutInContainer(control, model, shippart, "boardplayer2",  X+i, Y );
                    ActionPlayer play = new ActionPlayer(model, control, actions);
                    play.start();
                } else if (sens == 'V') {
                    ActionList actions = ActionFactory.generatePutInContainer(control, model, shippart, "boardplayer2",  X , Y+i);
                    ActionPlayer play = new ActionPlayer(model, control, actions);
                    play.start();
                } else {
                    return;
                }
            }
        }

    }



    /*
    * méthode permetant de conaitre le sens du bateau
    */
    public char sens(int Xclic1, int Yclic1, int Xclic2, int Yclic2) {
        int deltaX = Math.abs(Xclic1 -Xclic2 );
        int deltaY = Math.abs(Yclic1 - Yclic2);

        if (deltaX >= deltaY) {
            return 'H'; // Considérer horizontal si déplacement en X est > ou égal à Y
        } else {
            return 'V'; // Considérer vertical si déplacement en Y est >
        }

    }

    public void inisialise(){
        BattleShipStageModel stageModel = (BattleShipStageModel) model.getGameStage();
        int tab[] =ButtonController.returnValues();
        //firstplayer
        //first is 2
        if (tab[3]==0){

        }
        else if(tab[3]==1){
            model.setNextPlayer();
        } else if (tab[3]==2) {
            Random random = new Random();
            double randomValue = random.nextDouble(); // Génère un nombre aléatoire entre 0.0 et 1.0
            if (randomValue < 0.5) {
                model.setNextPlayer();
            }
        }
        tabCordMissileJ1= new int[stageModel.getPlayer1ToPlay()][2];
        tabCordMissileJ2= new int[stageModel.getPlayer2ToPlay()][2];

    }

    public int mode(){
        return mode;
    }

    public void shipoutdesk(Ship[] ship){
        ContainerElement bateau = null;
        Random random = new Random();
        for (int i = 0; i < ship.length; i++) {
            bateau = ship[i];
            List<Integer> availablePositions = new ArrayList<>();
            for (int x = 0; x < bateau.getNbRows(); x++) {
                availablePositions.add(x);
            }

            for(int j=0;j<ship[i].getTaille(); j++){
                GameElement shippart =ship[i].shipParts[j];
                int x = availablePositions.remove(0);
                ActionList actions = ActionFactory.generatePutInContainer(control, model, shippart, bateau.getName(),  x, 0 );
                ActionPlayer play = new ActionPlayer(model, control, actions);
                play.start();

            }
        }
    }





}
