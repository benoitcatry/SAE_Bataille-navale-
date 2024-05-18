package model;

import boardifier.model.ContainerElement;
import boardifier.model.GameStageModel;

public class MissileConteneur extends ContainerElement {

    public MissileConteneur(int x, int y, GameStageModel gameStageModel){
        super("ship", x, y, 1, 50 , gameStageModel);
    }
}
