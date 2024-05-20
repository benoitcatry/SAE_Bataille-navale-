package control;

import boardifier.control.ActionFactory;
import boardifier.control.Controller;
import boardifier.control.Decider;
import boardifier.model.Model;
import boardifier.model.action.ActionList;
import model.BattleBoard;
import model.BattleShipStageModel;
import model.Missille;

import java.awt.*;
import java.util.List;
import java.util.Random;

public  class BattleShipDecider extends Decider {
    int n; //avancement dans le tableau target
    private int prevRow;
    private int prevCol;
    private List<Point> listeMissile;
    private int[][] tabJeux;
    private int[][] tabMissTouche;
    private int[] targetRow;
    private int[] targetCol;
    private Random random;
    private boolean shootingMode; // false = mode aleatoire ; true = mode si touché
    public BattleShipDecider(Model model, Controller control) {
        super(model, control);
    }

    @Override
    public ActionList decide() {
        ActionList actions = null;
        BattleShipStageModel stage = (BattleShipStageModel) model.getGameStage();
        BattleBoard board = stage.getBoardPlayer2();
        Missille missile = null;
        int rowDest = 0;
        int colDest = 0;
        tabJeux = convertPointsToArray(board.computeValidCells(1));
        if ( n<3 ){
            n = 0;
        }

        tabMissTouche = convertPointsToArray(board.computeValidCells(2));//convertir pour avoir un tab des missile ayant touchés
        if (tabMissTouche[prevCol][prevRow] == 1) { // a modif pour le nombre représentant les missiles
            IfPreviousShipShot(prevCol,prevRow);


        }

        if (!isTargetEmpty(targetCol) && !isTargetEmpty(targetRow)) {
            // Si l'IA n'a pas encore touché de partie de bateau, tirer aléatoirement
            do {
                rowDest = random.nextInt(11);
                colDest = random.nextInt(11);
            } while (tabJeux[rowDest][colDest]!=0); // Tant que la case a déjà été tirée
        } else {
            rowDest = targetRow[n];
            colDest = targetCol[n];
            n += 1;

        }


        prevRow = rowDest;
        prevCol = colDest;
        actions = ActionFactory.generatePutInContainer(model, missile, "Battleboard",rowDest,colDest);
        actions.setDoEndOfTurn(true);
        return actions;
    }
    public void IfPreviousShipShot(int col, int row){
        shootingMode=true;
        if (col<10 && tabJeux[col+1][row]==0){
            targetCol[0]=col+1;
            targetRow[0]=row;
        } else if (row < 10 && tabJeux[col][row+1]==0) {
            targetCol[1]=col;
            targetRow[1]=row+1;
        } else if (row > 1 && tabJeux[col][row-1]==0) {
            targetCol[2]=col;
            targetRow[2]=row -1;
        } else if (col > 1 && tabJeux[col-1][row]==0) {
            targetCol[3]=col-1;
            targetRow[3]=row;
        }
        n = 0;
    }
    public static int[][] convertPointsToArray(List<Point> points) {
        int[][] array = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                array[i][j] = 0;
            }
        }

        for (Point point : points) {
            int x = point.x;
            int y = point.y;
            array[x][y] = 1; // Marquer la case comme tirée
        }

        return array;
    }
    public boolean isTargetEmpty(int[] target){
        for (int i=0; i < target.length;i++){
            if (target[i] != 0)
                return false;
        }
        return true;
    }

}
