package view;

import boardifier.control.Logger;
import boardifier.model.GameException;
import boardifier.model.GameStageModel;
import boardifier.view.ClassicBoardLook;
import boardifier.view.ContainerLook;
import boardifier.view.GameStageView;

import boardifier.view.TextLook;
import model.BattleShipStageModel;

public class ShipStageView extends GameStageView {



    public ShipStageView(String name, GameStageModel gameStageModel){
        super(name,gameStageModel);
    }
    @Override
    public void createLooks(){
        BattleShipStageModel model = (BattleShipStageModel) gameStageModel;

        addLook(new TextLook(model.getPlayerName()));

        addLook(new ClassicBoardLook(2,4,model.getBoard(),1,1,true));
    }
}
