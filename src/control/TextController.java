package control;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.Ship;
import view.SelectionPage;

public class TextController implements ChangeListener<String> {

    private static String missiles;
    private Ship model;
    private SelectionPage sp;

    public TextController(Ship model, SelectionPage sp) {
        this.sp = sp;
        this.model = model;
        sp.setTextListener(this);  // Set the text listener in the SelectionPage
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        try {
            missiles = newValue;
            System.out.println(missiles);
            // Update the model with the new missile count if needed
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + newValue);
            // Handle the invalid number format if needed
        }
    }

    public static String getMissiles() {
        System.out.println("missiles : "+missiles);
        return missiles;
    }
}
