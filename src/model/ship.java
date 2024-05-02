package model;

import boardifier.model.ElementTypes;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;
import org.w3c.dom.Element;

import java.lang.annotation.ElementType;

public class ship extends GameElement {

    private int number;
    private int colors

    public ship(int number, int color, GameStageModel gameStageModel ) {
        super(gameStageModel);
        ElementType.register("ship")
    }
}