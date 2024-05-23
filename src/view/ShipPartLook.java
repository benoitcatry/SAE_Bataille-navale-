package view;

import boardifier.model.GameElement;
import boardifier.view.ConsoleColor;
import boardifier.view.ElementLook;

import model.shipPart;


public class ShipPartLook extends ElementLook {

    public ShipPartLook(GameElement element) {
        super(element, 1, 1);
    }

    public void render(){
        shipPart shipPart = (shipPart)element;
        if (shipPart.getColor() == 1 && shipPart.getIdplayer() ==0){ // neutre
            shape[0][0] = ConsoleColor.BLACK + ConsoleColor.GREEN_BACKGROUND+ "S" +ConsoleColor.RESET;
        } else if (shipPart.getColor() == 1 && shipPart.getIdplayer() ==1) {
            shape[0][0] = ConsoleColor.BLACK + ConsoleColor.BLUE_BACKGROUND+ "S"+ConsoleColor.RESET;
        } else if (shipPart.getColor() == 2){ // COULER
            shape[0][0] = ConsoleColor.BLACK + ConsoleColor.RED_BACKGROUND+ "S" +ConsoleColor.RESET;

        } else {
            shape[0][0] = ConsoleColor.BLACK + ConsoleColor.BLUE_BACKGROUND+"S" +ConsoleColor.RESET;

        }

    }
}
