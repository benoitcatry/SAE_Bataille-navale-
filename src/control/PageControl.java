package control;

import boardifier.control.StageFactory;
import boardifier.model.GameException;
import boardifier.model.Model;
import model.BattleShipStageFactory;
import model.BattleShipStageModel;
import view.HomePage;
import view.OptionPage;
import view.SelectionPage;
import view.ShipRootPane;

import java.util.Random;

public class PageControl {
    ShipRootPane root;
    SelectionPage sp;

    BattleShipControler control;
    AudioController audio;

    OptionPage op;

    Model model;
    public PageControl(ShipRootPane root,BattleShipControler control, Model model,AudioController audio,OptionPage op){
        this.control=control;

        this.root=root;
        this.model=model;
        this.op=op;

    }
    public void hp(HomePage homePage){
        root.getChildren().clear();
        homePage.placeWidgets(root);
    }

    public void op(OptionPage op){
        root.getChildren().clear();
        op.placeWidgets(root);
    }
    public void sp(SelectionPage selectionPage){
        root.getChildren().clear();
        selectionPage.placeWidgets(root);
    }
    public void quit(){
        root.getChildren().clear();
        System.exit(0);
    }

    public void setMusic(){
        audio.toggleMusic();
    }

    public void play() throws GameException {
        int[] buttons = ButtonController.returnValues();
        //System.out.println(TextController.getMissiles());
        //TextController.getMissiles();
        if (buttons[0]==0){
            model.addHumanPlayer("Humain");
        } else if (buttons[0]==1) {
            model.addComputerPlayer("IA facile");
        } else if (buttons[0]==2) {
            model.addComputerPlayer("IA difficile");
        }

        if (buttons[1]==0){
            model.addHumanPlayer("Humain");
        } else if (buttons[1]==1) {
            model.addComputerPlayer("IA facile");
        } else if (buttons[1]==2) {
            model.addComputerPlayer("IA difficile");
        }
        /*
        BattleShipStageModel stageModel = (BattleShipStageModel) model.getGameStage();
        if (buttons[3]==0){
            control.player1playmode0(stageModel);
        }
        else if(buttons[3]==1){
            model.setNextPlayer();
            control.player2playmode0(stageModel);
        } else if (buttons[3]==2) {
            Random random = new Random();
            double randomValue = random.nextDouble(); // Génère un nombre aléatoire entre 0.0 et 1.0
            if (randomValue < 0.5) {
                control.player2playmode0(stageModel);
                model.setNextPlayer();
            }else {
                control.player1playmode0(stageModel);
            }
        }*/

        control.startGame();



    }
}
