package control;

import boardifier.control.ActionFactory;
import boardifier.control.Controller;
import boardifier.control.Decider;
import boardifier.model.ContainerElement;
import boardifier.model.GameElement;
import boardifier.model.Model;
import boardifier.model.action.ActionList;
import model.BattleBoard;
import model.BattleShipStageModel;
import model.Missille;
import model.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public  class BattleShipDecider extends Decider {
    int n; //avancement dans la liste target
    int count;
    private Point pointCentrale;
    private int prevRow;
    private boolean modeCaseAdja = true; //false tant que les cases adjacentes n'ont pas trouvé un autre hit
    private int prevCol;
    private List<Point> listeMissile;
    private int[][] tabJeux;
    private int[][] tabMissileTouche;
    private List<Point> listeTarget;
    private List<Point> listePointLigne;
    private int[] targetRow;
    private int[] targetCol;
    private Random random;
    private BattleShipStageModel battleShipStageModel;

    public BattleShipDecider(Model model, Controller control) {
        super(model, control);
        this.count=count;
        this.battleShipStageModel = battleShipStageModel;
        num = battleShipStageModel.ShipPlayer2.length-1;
    }

    @Override
    public ActionList decide() {
        ActionList actions = null;
        ContainerElement tire = null;
        tire = battleShipStageModel.getBoardPlayer2();
        int rowDest = 0;
        int colDest = 0;

        BattleShipStageModel stage = (BattleShipStageModel) model.getGameStage();
        BattleBoard board = stage.getBoardPlayer2();
        tabJeux = convertPointsToArray(board.computeValidCells(1));
        //Missille missile = null;

        tabMissileTouche = convertPointsToArray(board.computeValidCells(2));//convertir pour avoir un tab des missile ayant touchés
        if (tabMissileTouche[prevCol][prevRow] == 1) { // a modif pour le nombre représentant les missiles
            calculerCaseAdjacente(prevRow, prevCol);
            if (!listeTarget.isEmpty()){
                listeTarget.clear();
                modeCaseAdja = false;
                n=0;
                listePointLigne = getLineFromAdjacentPoints(pointCentrale,new Point(prevRow,prevCol));
            }
        }

        if (1==1/*difficulter 2*/) {
            if (listeTarget.isEmpty()) {
                calculerGrilleProba(tabJeux);
                Point meilleurCase = trouverLaMeilleurProba();
                rowDest = meilleurCase.x;
                colDest = meilleurCase.y;
                prevRow = rowDest;
                prevCol = colDest;
            } else if (!modeCaseAdja) {
                Point target = listeTarget.get(n);
                target.x = rowDest;
                target.y = colDest;
                n+=1;
            } else {
                Point target = listePointLigne.get(n);
                target.x = rowDest;
                target.y = colDest;
                n+=1;
            }
        }else {
            if (listeTarget.isEmpty()) {
                do {
                    rowDest = random.nextInt(11);
                    colDest = random.nextInt(11);
                } while (tabJeux[rowDest][colDest] != 0); // Tant que la case a déjà été tirée

            } else if (!modeCaseAdja) {
                Point target = listeTarget.get(n);
                target.x = rowDest;
                target.y = colDest;
                n+=1;
            } else {
                Point target = listePointLigne.get(n);
                target.x = rowDest;
                target.y = colDest;
                n+=1;
            }

        }
        GameElement missile = tire.getElement(battleShipStageModel.getPlayer2ToPlay()-1,0);
        prevRow = rowDest;
        prevCol = colDest;
        actions = ActionFactory.generatePutInContainer(model, missile, "Battleboard", rowDest, colDest);
        actions.setDoEndOfTurn(true);
        return actions;
    }
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<Point> calculerCaseAdjacente(int row, int col) {
        List<Point> caseAdjacente = new ArrayList<>();
        pointCentrale = new Point(row,col);
        modeCaseAdja = false;


        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (isValidPosition(newRow, newCol)) {
                caseAdjacente.add(new Point(newCol, newRow));
            }
        }

        return caseAdjacente;
    }
    private static boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 10 && col >= 0 && col < 10;
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






    //placement bateaux
    int num;


    public void placeAllShips() {
        Ship[] ship = battleShipStageModel.ShipPlayer2;
        Ship bateau = ship[num];
        int taille = bateau.getTaille();
        placeShip(bateau, taille, ship);

        num-=1;

    }

    private void placeShip(Ship bateau, int taille, Ship[] ship) {
        boolean placed = false;
        random = new Random();
        int x;
        int y;
        char sens;
        do {
            x = random.nextInt(10);
            y = random.nextInt(10);
            boolean n = random.nextBoolean();
            if (n)
                sens = 'V';
            else
                sens = 'H';
        }while (battleShipStageModel.Verifpeutetreposer(ship,x,y,taille,sens));

        bateau.setCordonnerShip(y,x,sens);

    }
    //niveau 2 tir
    /*
    public int[][] grilleProb() {
        double probGrid[][];
        Ship[] tab = battleShipStageModel.ShipPlayer1;
        int nbLigne = probGrid.length;
        int nbCol = probGrid[0].length;
        for (int m = 0; m < tab.length; m++)//ajouter un for pour chaque bateaux non couler
            for (int ligne = 0; ligne < nbLigne; ligne++) {
                for (int colonne = 0; colonne < nbCol; colonne++) {
                    Ship test = tab[m];
                    if (placement possible && pas de partie sur une zone de tir){
                        for (int i = 0; i < Nombre de partie du bateau;i++)
                        probGrid[ligne][colonne] += 1;


                    }
                }
            }
    }
    public boolean bateauSurCaseToucher(){

    }
    */
    //niveau 2 Grille proba
    private static final int GRID_SIZE = 10;
    private static final int[] SHIP_SIZES = {5, 4, 3, 3, 2}; // Porte-avion, Croiseur, Contre-torpilleur x2, Torpilleur
    private int[][] grilleProba;

    public void calculerGrilleProba(int[][] previousHitsGrid) {
        // Reset the probability grid
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (previousHitsGrid[i][j] == 1) {
                    grilleProba[i][j] = 0;
                } else {
                    grilleProba[i][j] = 1;
                } // Reset to 0 if a missile was already launched, otherwise set to 1
            }
        }

        // Calculate probabilities for each ship size
        for (int size : SHIP_SIZES) {
            calculerProbaParBateau(size, previousHitsGrid);
        }
    }

    private void calculerProbaParBateau(int size, int[][] previousHitsGrid) {
        // horizontal
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col <= GRID_SIZE - size; col++) {
                boolean canPlace = true;
                for (int k = 0; k < size; k++) {
                    if (previousHitsGrid[row][col + k] == 1) {
                        canPlace = false;
                        break;
                    }
                }
                if (canPlace) {
                    for (int k = 0; k < size; k++) {
                        grilleProba[row][col + k]++;
                    }
                }
            }
        }

        // vertical
        for (int col = 0; col < GRID_SIZE; col++) {
            for (int row = 0; row <= GRID_SIZE - size; row++) {
                boolean canPlace = true;
                for (int k = 0; k < size; k++) {
                    if (previousHitsGrid[row + k][col] == 1) {
                        canPlace = false;
                        break;
                    }
                }
                if (canPlace) {
                    for (int k = 0; k < size; k++) {
                        grilleProba[row + k][col]++;
                    }
                }
            }
        }
    }

    public Point trouverLaMeilleurProba() {
        int maxProbability = -1;
        Point bestCell = null;

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (grilleProba[row][col] > maxProbability) {
                    maxProbability = grilleProba[row][col];
                    bestCell = new Point(col, row);
                }
            }
        }

        return bestCell;
    }
    public List<Point> getLineFromAdjacentPoints(Point p1, Point p2) {
        List<Point> linePoints = new ArrayList<>();

        if (p1.y == p2.y) { // Horizontal line
            int row = p1.y;
            int startCol = Math.min(p1.x, p2.x);
            int endCol = Math.max(p1.x, p2.x);

            // Add points in the line including and between p1 and p2
            for (int col = startCol; col <= endCol; col++) {
                linePoints.add(new Point(col, row));
            }

            // Extend line to the left of startCol
            for (int col = startCol - 1; col >= 0; col--) {
                linePoints.add(new Point(col, row));
            }

            // Extend line to the right of endCol
            for (int col = endCol + 1; col < 10; col++) {
                linePoints.add(new Point(col, row));
            }

        } else if (p1.x == p2.x) { // Vertical line
            int col = p1.x;
            int startRow = Math.min(p1.y, p2.y);
            int endRow = Math.max(p1.y, p2.y);

            // Add points in the line including and between p1 and p2
            for (int row = startRow; row <= endRow; row++) {
                linePoints.add(new Point(col, row));
            }

            // Extend line above startRow
            for (int row = startRow - 1; row >= 0; row--) {
                linePoints.add(new Point(col, row));
            }

            // Extend line below endRow
            for (int row = endRow + 1; row < 10; row++) {
                linePoints.add(new Point(col, row));
            }
        }

        return linePoints;
    }

}
