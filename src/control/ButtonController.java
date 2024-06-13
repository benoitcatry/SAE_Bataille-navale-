package control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Ship;
import view.HomePage;
import view.SelectionPage;

public class ButtonController implements EventHandler<ActionEvent> {
    private SelectionPage sp;
    private HomePage hp;
    private Ship model;

    public ButtonController(SelectionPage sp, HomePage hp , Ship model){
        this.sp=sp;
        this.model=model;
        this.hp=hp;
        sp.setButtonListener(this);
        hp.setButtonListener(this);

    }


    @Override
    public void handle(ActionEvent actionEvent) {
        int startPlayer;   //0 = j1, 1=j2, 2=aleatoire
        if (sp.getSj1().isSelected()){
            startPlayer=0;
        } else if (sp.getSj2().isSelected()) {
            startPlayer=1;
        }
        else {
            startPlayer=2;
        }

        int playerj1;
        int playerj2;
        //0 = humain, 1 = ia facile, 2 = ia difficile

        playerj1=sp.getJ1().getSelectionModel().getSelectedIndex();
        playerj2=sp.getJ2().getSelectionModel().getSelectedIndex();


        int mode;
        //0 = mode1, 1 = mode2

        mode=SelectionPage.getMode().getSelectionModel().getSelectedIndex();

    }
}
