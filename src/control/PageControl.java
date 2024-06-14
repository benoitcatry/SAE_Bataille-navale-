package control;

import boardifier.control.StageFactory;
import view.HomePage;
import view.SelectionPage;
import view.ShipRootPane;

public class PageControl {
    ShipRootPane root;
    SelectionPage sp;
    public PageControl(ShipRootPane root){

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

    public void play(){
        ButtonController.returnValues();
        TextController.getMissiles();
        System.out.println("play");
        StageFactory.registerModelAndView("ship", "model.BattleShipStageModel", "view.ShipStageView");


        root.getChildren().clear();


    }

    public void option(){
        root.getChildren().clear();
    }
}
