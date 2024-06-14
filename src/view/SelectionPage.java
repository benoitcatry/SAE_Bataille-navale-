package view;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class SelectionPage {
    private TextField missiles;
    private Button valid;
    private ComboBox<String> mode, j1, j2;
    private CheckBox modifier;
    private RadioButton sj1, sj2, srd;
    private ToggleGroup fp;

    public SelectionPage() {
        initWidget();
    }

    private void initWidget() {
        fp = new ToggleGroup();
        missiles = new TextField();
        modifier = new CheckBox("oui");
        valid = new Button("valider les parametres et lancer la partie");
        sj1 = new RadioButton("joueur 1 commence        ");
        sj2 = new RadioButton("joueur 2 commence");
        srd = new RadioButton("joueur aleatoire commence");
        mode = new ComboBox<>(FXCollections.observableArrayList("mode 1", "mode 2"));
        j1 = new ComboBox<>(FXCollections.observableArrayList("humain", "IA facile", "IA difficile"));
        j2 = new ComboBox<>(FXCollections.observableArrayList("humain", "IA facile", "IA difficile"));
        sj1.setToggleGroup(fp);
        sj2.setToggleGroup(fp);
        srd.setToggleGroup(fp);
        sj1.setSelected(true);
    }

    public void placeWidgets(ShipRootPane root) {
        root.setStyle("-fx-alignment: center");
        GridPane grid = new GridPane();
        grid.setStyle("-fx-padding: 200px");
        grid.add(new Label("Selectionnez les parametres de la partie"), 1, 0);
        grid.add(new Label("sélectionner le mode des joueurs et \n le mode global de la partie"), 1, 1);
        grid.add(j1, 0, 3);
        grid.add(mode, 1, 3);
        grid.add(j2, 2, 3);
        grid.add(new Label("choisissez qui commence"), 1, 4);
        grid.add(sj1, 0, 5);
        grid.add(srd, 1, 5);
        grid.add(sj2, 2, 5);
        grid.add(new Label("voullez vous modifier le nombre de missiles ?"), 1, 6);
        grid.add(modifier, 1, 7);
        grid.add(missiles, 1, 8);
        grid.setAlignment(Pos.CENTER);
        grid.add(valid, 1, 9);
        root.getChildren().add(grid);
    }

    public void setButtonListener(EventHandler<ActionEvent> handler) {
        valid.setOnAction(handler);
        sj1.setOnAction(handler);
        srd.setOnAction(handler);
        sj2.setOnAction(handler);
        j1.setOnAction(handler);
        j2.setOnAction(handler);
        mode.setOnAction(handler);
        modifier.setOnAction(handler);
    }

    public void setTextListener(ChangeListener<String> listener) {
        missiles.textProperty().addListener(listener);
    }

    public ComboBox<String> getJ1() {
        return j1;
    }

    public ComboBox<String> getJ2() {
        return j2;
    }

    public CheckBox getModifier() {
        return modifier;
    }

    public ComboBox<String> getMode() {
        return mode;
    }

    public RadioButton getSj1() {
        return sj1;
    }

    public RadioButton getSj2() {
        return sj2;
    }

    public RadioButton getSrd() {
        return srd;
    }

    public TextField getMissiles() {
        return missiles;
    }

    public Button getValid() {
        return valid;
    }
}
