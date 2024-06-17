package control;

import boardifier.control.Controller;
import boardifier.control.ControllerAction;
import boardifier.model.GameException;
import boardifier.model.Model;
import boardifier.view.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.*;
import view.SelectionPage;

public class ControllerBattleShipAction extends ControllerAction implements EventHandler<ActionEvent> {

    private ShipView shipView;
    ShipRootPane root;
    SelectionPage sp;

    Model model;
    HomePage homePage;

    public ControllerBattleShipAction(Model model, View view, Controller control, ShipRootPane root, HomePage homePage) {
        super(model, view, control);
        shipView = (ShipView) view;
        setMenuHandlers();
        this.control=control;
        this.homePage=homePage;

        this.root=root;
        this.model=model;

    }


    private void setMenuHandlers() {

        // set event handler on the MenuIntro item
        shipView.getMenuIntro().setOnAction(e -> {
            control.stopGame();
            homePage.placeWidgets(root);
        });
        // set event handler on the MenuQuit item
        shipView.getMenuQuit().setOnAction(e -> {
            System.exit(0);
        });
    }
}
