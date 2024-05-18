package model;

import boardifier.control.Logger;
import boardifier.model.GameStageModel;
import boardifier.model.ContainerElement;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BattleBoard extends ContainerElement{

    public BattleBoard(int x, int y, GameStageModel gameStageModel) {
            // call the super-constructor to create a 10x10 grid, named "holeboard", and in x,y in space
            super("battelBoard", x, y, 10 , 10, gameStageModel);
        }


    public void setValidCells(int number) {
        Logger.debug("called",this);
        resetReachableCells(false);
        List<Point> valid = computeValidCells(number);
        if (valid != null) {
            for(Point p : valid) {
                reachableCells[p.y][p.x] = true;
            }
        }
    }

    /*public List<Point> computeValidCells(int number) {
        List<Point> lst = new ArrayList<>();
        Misille m = null;
        // if the grid is empty, is it the first turn and thus, all cells are valid
        if (isEmpty()) {
            // i are rows
            for(int i=0;i<10;i++) {
                // j are cols
                for (int j = 0; j < 10; j++) {
                    // cols is in x direction and rows are in y direction, so create a point in (j,i)
                    lst.add(new Point(j,i));
                }
            }
            return lst;
        }
        // else, take each empty cell and check if it is valid
        for(int i=0;i<10;i++) {
            for(int j=0;j<10;j++) {
                if (isEmptyAt(i,j)) {
                    // check adjacence in row-1
                    if (i-1 >= 0) {
                        if (j-1>=0) {
                            m = (Misille) getElement(i-1,j-1);

                            // check if same parity
                            if ((m != null) && ( m.getNumber()%2 == number%2)) {
                                lst.add(new Point(j,i));
                                continue; // go to the next point
                            }
                        }
                        m = (Misille) getElement(i-1,j);
                        // check if different parity
                        if ((m != null) && ( m.getNumber()%2 != number%2)) {
                            lst.add(new Point(j,i));
                            continue; // go to the next point
                        }
                        if (j+1<=2) {
                            m = (Misille) getElement(i-1,j+1);
                            // check if same parity
                            if ((m != null) && ( m.getNumber()%2 == number%2)) {
                                lst.add(new Point(j,i));
                                continue; // go to the next point
                            }
                        }
                    }
                    // check adjacence in row+1
                    if (i+1 <= 2) {
                        if (j-1>=0) {
                            m = (Misille) getElement(i+1,j-1);
                            // check if same parity
                            if ((m != null) && ( m.getNumber()%2 == number%2)) {
                                lst.add(new Point(j,i));
                                continue; // go to the next point
                            }
                        }
                        m = (Misille) getElement(i+1,j);
                        // check if different parity
                        if ((m != null) && ( m.getNumber()%2 != number%2)) {
                            lst.add(new Point(j,i));
                            continue; // go to the next point
                        }
                        if (j+1<=2) {
                            m = (Misille) getElement(i+1,j+1);
                            // check if same parity
                            if ((m != null) && ( m.getNumber()%2 == number%2)) {
                                lst.add(new Point(j,i));
                                continue; // go to the next point
                            }
                        }
                    }
                    // check adjacence in row
                    if (j-1>=0) {
                        m = (Misille) getElement(i,j-1);
                        // check if different parity
                        if ((m != null) && ( m.getNumber()%2 != number%2)) {
                            lst.add(new Point(j,i));
                            continue; // go to the next point
                        }
                    }
                    if (j+1<=2) {
                        m = (Misille) getElement(i,j+1);
                        // check if different parity
                        if ((m != null) && ( m.getNumber()%2 != number%2)) {
                            lst.add(new Point(j,i));
                            continue; // go to the next point
                        }

                    }
                }
            }
        }
        return lst;
    } */
    public List<Point> computeValidCells(int n) {
        List<Point> lst = new ArrayList<>();
        Missille m = null;
        if (isEmpty()) {
            for(int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    lst.add(new Point(j, i));
                }
            }
            return lst;
        }

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if (isEmptyAt(i, j)) {
                    if (i - 1 >= 0) {
                        if (j - 1 >= 0) {
                            m = (Missille) getElement(i - 1, j - 1);
                            if (m != null && m.getColor() == n) {
                                lst.add(new Point(j, i));
                                continue;
                            }
                        }
                        m = (Missille) getElement(i - 1, j);
                        if (m != null && m.getColor() == n) {
                            lst.add(new Point(j, i));
                            continue;
                        }
                        if (j + 1 <= 2) {
                            m = (Missille) getElement(i - 1, j + 1);
                            if (m != null && m.getColor() == n) {
                                lst.add(new Point(j, i));
                                continue;
                            }
                        }
                    }
                    if (i + 1 <= 2) {
                        if (j - 1 >= 0) {
                            m = (Missille) getElement(i + 1, j - 1);
                            if (m != null && m.getColor() == n) {
                                lst.add(new Point(j, i));
                                continue;
                            }
                        }
                        m = (Missille) getElement(i + 1, j);
                        if (m != null && m.getColor() == n) {
                            lst.add(new Point(j, i));
                            continue;
                        }
                        if (j + 1 <= 2) {
                            m = (Missille) getElement(i + 1, j + 1);
                            if (m != null && m.getColor() == n) {
                                lst.add(new Point(j, i));
                                continue;
                            }
                        }
                    }
                    if (j - 1 >= 0) {
                        m = (Missille) getElement(i, j - 1);
                        if (m != null && m.getColor() == n) {
                            lst.add(new Point(j, i));
                            continue;
                        }
                    }
                    if (j + 1 <= 2) {
                        m = (Missille) getElement(i, j + 1);
                        if (m != null && m.getColor() == n) {
                            lst.add(new Point(j, i));
                            continue;
                        }
                    }
                }
            }
        }
        return lst;
    }






    public BattleBoard getGet() {
        return this;
    }














}
