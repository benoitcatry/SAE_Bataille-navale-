package view;

import boardifier.control.Logger;
import boardifier.model.GameElement;
import boardifier.model.GameException;
import boardifier.model.GameStageModel;
import boardifier.model.TextElement;
import boardifier.view.*;

import model.BattleShipStageModel;
import model.Ship;
import model.shipPart;


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


        for (int i=0; i<model.getShipsPlayer1().length; i++){
            //System.out.println(i + "bateau");
            addLook(new Shiplook(2, 2,model.getShipsPlayer1()[i]));
            addLook(new Shiplook(2, 2,model.getShipsPlayer2()[i]));
        }

        //on ajoute les partie de bateau
        for (int i=0; i<model.getShipsPlayer1().length-1; i++){
            for (int j = 0; j < model.getShipsPlayer1()[i].shipParts.length; j++){
                addLook(new ShipPartLook(model.getShipsPlayer1()[i].getshippart()[j]));
                addLook(new ShipPartLook(model.getShipsPlayer2()[i].getshippart()[j]));
            }
        }

        /*for(int i=0; i < model.getMissileJoueur1().length; i++){
            addLook(new MissileLook(model.getMissileJoueur1()[i]));
            addLook(new MissileLook(model.getMissileJoueur2()[i]));
        }*/

        addLook(new StockMissileLook(2,2,model.getStockMissileJ1()));
        addLook(new StockMissileLook(2,2,model.getStockMissileJ2()));

        for (int i=0; i<model.getMissileJoueur2().length; i++){
            addLook(new MissileLook(model.getMissileJoueur2()[i]));
            addLook(new MissileLook(model.getMissileJoueur1()[i]));

        }




        addLook(new TextLook(model.getInfoPartie()));

        int[] stats;
        TextElement j1 = model.getPlayer1Name();
        TextElement j2 = model.getPlayer2Name();
        stats = StatsJoueurs.findStats(model);
        StatsJoueurs.showStats(stats,j1,j2);
        System.out.println(getLooks());

    }
}
