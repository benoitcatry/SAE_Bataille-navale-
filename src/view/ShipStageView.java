package view;

import boardifier.control.Logger;
import boardifier.model.GameElement;
import boardifier.model.GameException;
import boardifier.model.GameStageModel;
import boardifier.model.TextElement;
import boardifier.view.*;

import model.BattleShipStageModel;
import model.Ship;


public class ShipStageView extends GameStageView {



    public ShipStageView(String name, GameStageModel gameStageModel){
        super(name,gameStageModel);
    }
    @Override
    public void createLooks(){
        BattleShipStageModel model = (BattleShipStageModel) gameStageModel;

        addLook(new TextLook(model.getPlayer1Name()));

        addLook(new ClassicBoardLook(2,4,model.getBoardPlayer1(),1,1,true));

        addLook(new TextLook(model.getPlayer2Name()));

        addLook(new ClassicBoardLook(2,4,model.getBoardPlayer2(),1,1,true));

        Ship[] shipsj1 = model.getShipsPlayer1();
        Ship[] shipsj2 = model.getShipsPlayer2();
        for (int i=0; i<shipsj1.length; i++){

            System.out.println(i + "bateau");
            addLook(new ContainerLook(shipsj1[i], 0));
        }


        addLook(new TextLook(model.getInfoPartie()));

        int[] stats;
        TextElement j1 = model.getPlayer1Name();
        TextElement j2 = model.getPlayer2Name();
        stats = StatsJoueurs.findStats(model);
        StatsJoueurs.showStats(stats,j1,j2);


    }
}
