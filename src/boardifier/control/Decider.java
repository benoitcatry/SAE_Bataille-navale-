package boardifier.control;

import boardifier.model.action.ActionList;
import boardifier.model.Model;
import model.BattleBoard;
import model.BattleShipStageFactory;
import model.BattleShipStageModel;

public abstract class Decider {
    protected Model model;
    protected Controller control;

    public Decider(Model model, Controller control) {
        this.model = model;
        this.control = control;
    }

    public abstract ActionList decide();
}

