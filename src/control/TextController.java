package control;

import model.Ship;
import view.SelectionPage;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TextController implements ChangeListener {


    private int missiles;
    private Ship model;
    private SelectionPage sp;

    public TextController(Ship model, SelectionPage sp){
        this.sp=sp;
        this.model=model;
        sp.setTextListener((javafx.beans.value.ChangeListener) this);

    }


    @Override
    public void stateChanged(ChangeEvent e) {
        missiles=Integer.parseInt(sp.getMissiles().getText());
    }
}
