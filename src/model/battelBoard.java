package model;

import boardifier.control.Logger;
import boardifier.model.GameStageModel;
import boardifier.model.ContainerElement;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class battleBoard extends ContainerElement{

    public battleBoard(int x, int y, GameStageModel gameStageModel) {
            // call the super-constructor to create a 10x10 grid, named "holeboard", and in x,y in space
            super("battelBoard", x, y, 10 , 10, gameStageModel);
        }





    }
