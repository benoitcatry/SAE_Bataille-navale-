package view;

import boardifier.model.GameElement;
import boardifier.view.ConsoleColor;
import boardifier.view.ElementLook;
import model.Ship;
import model.shipPart;


public class ShipPartLook extends ElementLook {

    public ShipPartLook(GameElement element) {
        super(element, 1, 1);
    }

    public void render(){
        shipPart shipPart = (shipPart)element;
        if (shipPart.getColor() == 1){ // neutre
            shape[0][0] = ConsoleColor.BLACK + ConsoleColor.GREEN_BACKGROUND+ shipPart.getNumber()+ConsoleColor.RESET;
        }
        if (shipPart.getColor() == 2){ // toucher
            shape[0][0] = ConsoleColor.RED + ConsoleColor.GREEN_BACKGROUND+shipPart.getNumber()+ConsoleColor.RESET;
        }
        if (shipPart.getColor() == 3){ // couler
            shape[0][0] = ConsoleColor.YELLOW + ConsoleColor.GREEN_BACKGROUND+shipPart.getNumber()+ConsoleColor.RESET;
        }
        else {
            shape[0][0] = ConsoleColor.BLACK + ConsoleColor.BLUE_BACKGROUND+shipPart.getNumber()+ConsoleColor.RESET;
        }
    }
}
