package control;

import boardifier.control.ActionFactory;
import boardifier.control.Controller;
import boardifier.control.Decider;
import boardifier.model.GameElement;
import boardifier.model.Model;
import boardifier.model.action.ActionList;


import java.awt.*;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class BattleShipDecider extends Decider {

    private static final Random loto = new Random(Calendar.getInstance().getTimeInMillis());

    public BattleShipDecider(Model model, Controller control) {
        super(model, control);
    }

    @Override
    public ActionList decide() {
        return null;
    }
}
