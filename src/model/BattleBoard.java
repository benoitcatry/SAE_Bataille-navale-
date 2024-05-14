package model;

import boardifier.model.GameStageModel;
import boardifier.model.ContainerElement;

public class BattleBoard extends ContainerElement{

    public BattleBoard(int x, int y, GameStageModel gameStageModel) {
            // call the super-constructor to create a 10x10 grid, named "holeboard", and in x,y in space
            super("battelBoard", x, y, 10 , 10, gameStageModel);
        }





    public BattleBoard getGet() {
        return this;
    }
}
