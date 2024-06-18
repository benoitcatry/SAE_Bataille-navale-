package view;

import boardifier.control.Logger;
import boardifier.model.ContainerElement;
import boardifier.view.GridLook;
import javafx.scene.paint.Color;

public class StockMissileLook extends GridLook {

    public StockMissileLook(int rowHeight, int colWidth, ContainerElement containerElement) {
        super(rowHeight, colWidth, containerElement, 1, 1, Color.BLACK);
        setVerticalAlignment(ALIGN_MIDDLE);
        setHorizontalAlignment(ALIGN_CENTER);
    }


}
