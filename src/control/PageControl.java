package control;

import boardifier.control.StageFactory;
import boardifier.model.GameException;
import model.BattleShipStageFactory;
import model.BattleShipStageModel;
import view.HomePage;
import view.SelectionPage;
import view.ShipRootPane;

public class PageControl {
    ShipRootPane root;
    SelectionPage sp;

    BattleShipControler control;

    BattleShipStageModel stageModel;
    public PageControl(ShipRootPane root,BattleShipControler control){
        this.control=control;

        this.root=root;

    }
    public void hp(HomePage homePage){
        root.getChildren().clear();
        homePage.placeWidgets(root);
    }
    public void sp(SelectionPage selectionPage){
        root.getChildren().clear();
        selectionPage.placeWidgets(root);
    }
    public void quit(){
        root.getChildren().clear();
        System.exit(0);
    }

    public void play() throws GameException {
        ButtonController.returnValues();
        TextController.getMissiles();
        System.out.println("play");
        control.startGame();
        root.getChildren().clear();


    }

    public void option(){
        root.getChildren().clear();
    }
}
