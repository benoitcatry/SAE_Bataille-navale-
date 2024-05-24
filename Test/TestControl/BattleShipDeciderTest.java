package TestControl;
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
import boardifier.control.Controller;
import control.BattleShipDecider;
import model.BattleShipStageModel;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class BattleShipDeciderTest {
    private BattleShipDecider decider;
    private BattleShipStageModel model;
    private Controller controller;

    @BeforeEach
    void setUp() {
        model = Mockito.mock(BattleShipStageModel.class);
        controller = Mockito.mock(Controller.class);
        decider = new BattleShipDecider(model.getModel(), controller);
    }

    @Test
    void testConstructorInitialization() {
        assertNotNull(decider.battleShipStageModel);
        assertEquals(model, decider.battleShipStageModel);
    }

    @Test
    void testCalculerCaseAdjacente() {
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(3, 2));
        expected.add(new Point(5, 2));
        expected.add(new Point(4, 1));
        expected.add(new Point(4, 3));

        List<Point> result = decider.calculerCaseAdjacente(4, 2);
        assertTrue(result.containsAll(expected) && expected.containsAll(result));
    }

    @Test
    void testConvertPointsToArray() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(9, 9));
        points.add(new Point(5, 5));

        int[][] array = BattleShipDecider.convertPointsToArray(points);

        assertEquals(1, array[0][0]);
        assertEquals(1, array[9][9]);
        assertEquals(1, array[5][5]);
        assertEquals(0, array[1][1]); // A point not in the list should be 0
    }

    @Test
    void testCalculerGrilleProba() {
        int[][] previousHitsGrid = new int[10][10];
        previousHitsGrid[0][0] = 1; // simulate a hit

        decider.calculerGrilleProba(previousHitsGrid);

        assertEquals(0, decider.grilleProba[0][0]); // a hit cell should have 0 probability
        assertTrue(decider.grilleProba[1][0] > 0); // adjacent cells should have incremented probabilities
    }

    @Test
    void testTrouverLaMeilleurProba() {
        int[][] probGrid = {
                {0, 0, 1},
                {0, 2, 1},
                {0, 0, 3},
        };

        decider.grilleProba = probGrid;
        Point bestCell = decider.trouverLaMeilleurProba();

        assertEquals(2, bestCell.x);
        assertEquals(2, bestCell.y);
    }

    @Test
    void testGetLineFromAdjacentPoints() {
        Point p1 = new Point(2, 2);
        Point p2 = new Point(2, 4);
        List<Point> linePoints = decider.getLineFromAdjacentPoints(p1, p2);

        // Doit inclure des points (2,2), (2,3), (2,4)
        assertTrue(linePoints.contains(new Point(2, 2)));
        assertTrue(linePoints.contains(new Point(2, 3)));
        assertTrue(linePoints.contains(new Point(2, 4)));
        assertTrue(linePoints.contains(new Point(2, 1)));
        assertTrue(linePoints.contains(new Point(2, 5)));
    }
}
