package view;

import boardifier.model.GameElement;
import boardifier.view.ConsoleColor;
import boardifier.view.ElementLook;
import model.Missille;

public class MissileLook extends ElementLook {

    public MissileLook(GameElement element) { super(element,1,1);}

    protected void render() {

        Missille missille = (Missille) element;
        if (missille.getColor() == 1) {
            shape[0][0] = ConsoleColor.BLACK + ConsoleColor.WHITE_BACKGROUND + "M" + ConsoleColor.RESET;
        }
        if (missille.getColor() == 2) {
            shape[0][0] = ConsoleColor.BLACK + ConsoleColor.RED_BACKGROUND + "T" + ConsoleColor.RESET;
        }

    }
}
