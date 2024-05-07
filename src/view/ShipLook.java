package view;

import boardifier.model.GameElement;
import boardifier.view.ConsoleColor;
import boardifier.view.ElementLook;
import model.Ship;


public class ShipLook extends ElementLook {

    public ShipLook(GameElement element) {
        super(element, 1, 1);
    }

    public protected render(){
        Ship ship = (Ship)element;
        if (ship.getOwner() == "Player1"){
            shape[0][0] = ConsoleColor.WHITE + ConsoleColor.GREEN_BACKGROUND+ship.getSize()+ConsoleColor.RESET,
        }

        else {
            shape[0][0] = ConsoleColor.BLACK + ConsoleColor.RED_BACKGROUND+ship.getSize()+ConsoleColor.RESET,
        }
    }
}
