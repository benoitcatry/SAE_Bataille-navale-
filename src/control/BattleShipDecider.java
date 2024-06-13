package control;

import boardifier.control.ActionFactory;
import boardifier.control.Controller;
import boardifier.control.Decider;
import boardifier.model.ContainerElement;
import boardifier.model.GameElement;
import boardifier.model.Model;
import boardifier.model.action.ActionList;
import model.BattleShipStageModel;
import model.Ship;
import model.BattleBoard;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public  class BattleShipDecider extends Decider {
    public int n; //avancement dans la liste target
    public int count;
    private Point pointCentrale;
    private int prevY;
    private boolean modeCaseAdja = true; //false tant que les cases adjacentes n'ont pas trouvé un autre hit
    private int prevX;
    private List<Point> listeMissile;
    private int[][] tabJeux;
    private int[][] tabMissileTouche;
    private List<Point> listeTarget = new ArrayList<>();
    private List<Point> listePointLigne;
    private int[] targetRow;
    private int[] targetCol;
    private Random random;
    private int id_Bot;
    private BattleShipStageModel battleShipStageModel;
    private int levelBot;
    public int[][] posssitionvisité = new int[10][10];
    public int stade = 0;
    public int[][] futurcible = new int[1][2];
    public int[][] futurcibleavance = new int[4][2];
    public int tirederecherche = 0;
    public boolean stade3toucher = false;
    public int tirerat = 0;
    public int touche = 0;
    public boolean toucher = false;
    boolean set = false;

    public BattleShipDecider(Model model, Controller control, int id, int level) {
        super(model, control);
        this.count = count;
        id_Bot = id;
        this.battleShipStageModel = (BattleShipStageModel) model.getGameStage();
        this.levelBot = level;

    }


    @Override
    public ActionList decide() {return null;}
        /*int X = 0;
        int Y = 0;
        BattleBoard board = null;
        if (id_Bot==0){
            board = battleShipStageModel.getBoardPlayer1();
            System.out.println("boardP1");
        }
        else{
            board = battleShipStageModel.getBoardPlayer2();
            System.out.println("boardP2");
        }
        tabMissileTouche = convertPointsToArray(board.computeValidCells(2));
        tabJeux = convertPointsToArray(board.computeValidCells(1));
        if (set = false) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    posssitionvisité[i][j] = 0;
                }
            }
            set = true;
        }
        BattleShipStageModel gameStage = (BattleShipStageModel) model.getGameStage();
        random = new Random();
        ContainerElement tire = null;
        do {

            if (levelBot == 1) {
                if (stade == 0) {

                    X = random.nextInt(10);
                    Y = random.nextInt(10);

                } else if (stade == 1) {
                    X = futurcibleavance[tirederecherche][0];
                    Y = futurcibleavance[tirederecherche][1];
                } else if (stade == 2) {
                    X = futurcible[0][0];
                    Y = futurcible[0][1];
                }


            } else {
                if (tabMissileTouche[prevX][prevY] == 1) {
                    if (!listeTarget.isEmpty()) {
                        listeTarget.clear();
                        modeCaseAdja = true;
                        n = 0;
                        listePointLigne = getLineFromAdjacentPoints(pointCentrale, new Point(prevY, prevX));
                        pointCentrale = null;
                    } else {
                        listeTarget =  calculerCaseAdjacente2(prevY, prevX);
                        modeCaseAdja = false;
                        n = 0;
                    }
                }
                if (listeTarget.isEmpty()) {
                    calculerGrilleProba(tabJeux);
                    Point meilleurCase = trouverLaMeilleurProba();
                    X = meilleurCase.x;
                    Y = meilleurCase.y;
                    prevY = Y;
                    prevX = X;
                } else if (!modeCaseAdja) {
                    Point target = listeTarget.get(n);
                    X = target.x;
                    Y = target.y;
                    n += 1;
                } else {
                    Point target = listePointLigne.get(n);
                    X = target.x;
                    Y = target.y;
                    n += 1;
                }


            }

        } while ((posssitionvisité[X][Y] != 0));
        posssitionvisité[X][Y] = 1;

        if (id_Bot == 0) {
            tire = gameStage.getStockMissileJ1();
            GameElement missile = tire.getElement(0, 0);
            if (gameStage.toucheroupas(gameStage.getShipsPlayer2(), X, Y) == true) {
                toucher = true;
                for (int i = 0; i < gameStage.getMissileJoueur1().length; i++) {
                    if (gameStage.getMissileJoueur1()[i] == missile) {
                        gameStage.getMissileJoueur1()[i].setColor(2);

                    }
                }
            } else {
                toucher = false;
            }
            if (levelBot == 1) {
                if (toucher == true) {
                    if (stade == 0) {
                        stade++;
                        calculerCaseAdjacente(Y, X);
                    } else if (stade == 1) {
                        stade++;
                        stade3toucher = true;
                        stade3(prevX, prevY, X, Y, stade3toucher);
                        tirederecherche = 0;
                    } else if (stade == 2) {
                        stade3toucher = true;
                        stade3(prevX, prevY, X, Y, stade3toucher);
                    }
                }

                if (stade == 1) {
                    tirederecherche++;
                }
                if (stade == 2) {
                    stade3toucher = false;
                    stade3(prevX, prevY, X, Y, stade3toucher);
                }
            }

            prevY = Y;
            prevX = X;
            ActionList actions = ActionFactory.generatePutInContainer(model, missile, "boardplayer1", Y, X);
            actions.setDoEndOfTurn(true);
            return actions;
        } else {
            tire = gameStage.getStockMissileJ2();
            GameElement missile = tire.getElement(0, 0);
            if (gameStage.toucheroupas(gameStage.getShipsPlayer1(), X, Y) == true) {
                for (int i = 0; i < gameStage.getMissileJoueur1().length; i++) {
                    if (gameStage.getMissileJoueur2()[i] == missile) {
                        gameStage.getMissileJoueur2()[i].setColor(2);

                    }
                }
            } else {
                toucher = false;
            }
            if (levelBot == 1) {
                if (toucher == true) {
                    if (stade == 0) {
                        stade++;
                        calculerCaseAdjacente(Y, X);
                    } else if (stade == 1) {
                        stade++;
                        stade3toucher = true;
                        stade3(prevX, prevY, X, Y, stade3toucher);
                        tirederecherche = 0;
                    } else if (stade == 2) {
                        stade3toucher = true;
                        stade3(prevX, prevY, X, Y, stade3toucher);
                    }
                }

                if (stade == 1) {
                    tirederecherche++;
                }
                if (stade == 2) {
                    stade3toucher = false;
                    stade3(prevX, prevY, X, Y, stade3toucher);
                }
            }

            prevY = Y;
            prevX = X;
            ActionList actions = ActionFactory.generatePutInContainer(model, missile, "boardplayer2", Y, X);
            actions.setDoEndOfTurn(true);
            return actions;
        }
    }


    public void stade3(int oldX, int oldY, int newX, int newY, boolean stade3toucher) {
        if (stade3toucher) {
            if (oldX == newX) {
                if (oldY < newY) {
                    futurcible[0][0] = newX;
                    futurcible[1][0] = newY + 1;
                    touche++;
                } else if (oldY > newY) {
                    futurcible[0][0] = newX;
                    futurcible[1][0] = newY - 1;
                    touche++;
                }
            } else {
                if (oldX < newX) {
                    futurcible[0][0] = newX + 1;
                    futurcible[0][1] = newY;
                    touche++;
                } else if (oldX > newX) {
                    futurcible[0][0] = newX - 1;
                    futurcible[0][1] = newY;
                    touche++;
                }
            }
        } else {
            if (oldX == newX) {
                if (oldY < newY) {
                    futurcible[0][0] = newX;
                    futurcible[0][1] = newY - touche;
                    touche = 0;
                } else if (oldY > newY) {
                    futurcible[0][0] = newX;
                    futurcible[0][1] = newY + touche;
                    touche = 0;
                }
            } else {
                if (oldX < newX) {
                    futurcible[0][0] = newX - touche;
                    futurcible[0][1] = newY;
                    touche = 0;
                } else if (oldX > newX) {
                    futurcible[0][0] = newX + touche;
                    futurcible[0][1] = newY;
                    touche = 0;
                }
            }

        }
        if (tirerat == 2) {
            stade = 0;
        }


    }


    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int[][] calculerCaseAdjacente(int row, int col) {
        modeCaseAdja = false;
        int newRow = 0;
        int newCol = 0;
        int a = 0;
        int n = 0;
        for (int i = 0; i < 4; i++) {

            do {
                newRow = row + (DIRECTIONS[i][0]);
                newCol = col + (DIRECTIONS[i][1]);
                if (newCol >= 10) {
                    newCol = 9;
                    while (!(posssitionvisité[newRow][newCol] == 0)) {
                        newCol--;
                    }
                }
                if (newRow >= 10) {
                    newRow = 9;
                    while (!(posssitionvisité[newRow][newCol] == 0)) {
                        newRow--;
                    }
                }
                if (newCol < 0) {
                    newCol = 0;
                    while (!(posssitionvisité[newRow][newCol] == 0)) {
                        newCol++;
                    }
                }
                if (newRow < 0) {
                    newRow = 0;
                    while (!(posssitionvisité[newRow][newCol] == 0)) {
                        newRow++;
                    }
                }

                System.out.println(newRow + " " + newCol + " " + n);
                if (isValidPosition(newRow, newCol)) {
                    futurcibleavance[n][0] = newCol;
                    futurcibleavance[n][1] = newRow;

                }
            } while (!(posssitionvisité[newRow][newCol] == 0));
        }
        return futurcible;
    }

    private static boolean isValidPosition(int row, int col) {
        return row >= 0 && row <= 9 && col >= 0 && col <= 9;
    }


    public int placeAllShips(int m) {
        if (id_Bot == 0) {
            int taille = battleShipStageModel.ShipPlayer1[m].getTaille();
            placeShip(battleShipStageModel.ShipPlayer1[m], taille, battleShipStageModel.ShipPlayer1);
        } else if (id_Bot == 1) {
            int taille = battleShipStageModel.ShipPlayer2[m].getTaille();
            placeShip(battleShipStageModel.ShipPlayer2[m], taille, battleShipStageModel.ShipPlayer2);
        }
        if (battleShipStageModel.ShipPlayer2.length - 1 == m) {
            return 1;
        } else {
            return 0;
        }

    }

    private void placeShip(Ship bateau, int taille, Ship[] ship) {
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
        } while (!battleShipStageModel.Verifpeutetreposer(ship, x, y, taille, sens) || !((x > 9 || y + taille - 1 > 9 || 0 > y || 0 > x) || (x + taille - 1 > 9 || y > 9 || 0 > y || 0 > x)));
        bateau.setCordonnerShip(y, x, sens);


    }





    //niveau 2 Grille proba
    private static final int GRID_SIZE = 9;
    private static final int[] SHIP_SIZES = {5, 4, 3, 3, 2}; // Porte-avion, Croiseur, Contre-torpilleur x2, Torpilleur
    private int[][] grilleProba;

    public void calculerGrilleProba(int[][] previousHitsGrid) {
        // Reset the probability grid
        if (grilleProba == null) {
            grilleProba = new int[GRID_SIZE][GRID_SIZE];
        }
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

    public boolean isTargetEmpty(int[] target) {
        for (int i = 0; i < target.length; i++) {
            if (target[i] != 0)
                return false;
        }
        return true;
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



    public List<Point> calculerCaseAdjacente2(int row, int col) {
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
    }*/
}
