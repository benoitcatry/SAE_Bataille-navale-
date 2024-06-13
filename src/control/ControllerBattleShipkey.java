package control;

import boardifier.control.Controller;
import boardifier.control.ControllerKey;
import boardifier.control.Logger;
import boardifier.model.Model;
import boardifier.view.View;
import javafx.event.*;
import javafx.scene.input.*;


public class ControllerBattleShipkey extends ControllerKey implements EventHandler<KeyEvent> {

    public ControllerBattleShipkey(Model model, View view, Controller control) {
        super(model, view, control);
    }

    public void handle(KeyEvent event) {
        if (!model.isCaptureKeyEvent()) return;

        // if a key is pressed, just prints its code
        Logger.debug(event.getCode().toString());
    }
}
