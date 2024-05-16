package model;

import boardifier.model.ContainerElement;
import boardifier.model.GameStageModel;

public class MisileConteneur extends ContainerElement {

    public MisileConteneur(int x, int y,  GameStageModel gameStageModel){
        super("ship", x, y, 1, 50 , gameStageModel);
    }
}
