package view;

import boardifier.control.Logger;
import boardifier.model.ContainerElement;
import boardifier.view.GridLook;
import boardifier.view.TableLook;
import javafx.scene.paint.Color;

public class Shiplook extends GridLook {

    public Shiplook(int rowHeight, int colWidth, ContainerElement containerElement) {
        super(rowHeight, colWidth, containerElement, 1, 1, Color.BLACK);
        setVerticalAlignment(ALIGN_MIDDLE);
        setHorizontalAlignment(ALIGN_CENTER);
    }

}

