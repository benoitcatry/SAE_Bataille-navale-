package TestModel;

import boardifier.model.action.ActionList;
import control.BattleShipControler;
import control.BattleShipDecider;
import model.BattleShipStageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class BattleShipDeciderTest {

    private BattleShipDecider decider;
    private BattleShipControler controler;
    private BattleShipStageModel model;
    private int[][] gridP1;
    private int[][] gridP2;

    @BeforeEach
    public void setUp() {
        // Configuration de la simulation de l'environnement
        model = ;
        controler = new BattleShipStageModel("TestStage", model);
        decider = new BattleShipDecider(model.getModel(), controler, 0, 1);
        gridP1 = new int[10][10];
        gridP2 = new int[10][10];
        decider.gridP1 = gridP1;
        decider.gridP2 = gridP2;
    }

    @Test
    public void testMarkMiss() {
        Point p = new Point(3, 3);
        decider.markMiss(p);
        assertEquals(2, gridP1[3][3]);
    }

    @Test
    public void testMarkHit() {
        Point p = new Point(4, 4);
        decider.markHit(p);
        assertEquals(3, gridP1[4][4]);
        assertTrue(controler.hitJ1.contains(p));
    }

    @Test
    public void testMarkSunk() {
        Point p = new Point(5, 5);
        decider.markHit(p);
        decider.markSunk(p);
        assertEquals(4, gridP1[5][5]);
        assertFalse(controler.hitJ1.contains(p));
    }

    @Test
    public void testUpdatePotentialTargets() {
        Point p = new Point(5, 5);
        decider.markHit(p);
        decider.updatePotentialTargets(p);
        assertTrue(controler.potentialTargetsJ1.contains(new Point(4, 5)));
        assertTrue(controler.potentialTargetsJ1.contains(new Point(6, 5)));
        assertTrue(controler.potentialTargetsJ1.contains(new Point(5, 4)));
        assertTrue(controler.potentialTargetsJ1.contains(new Point(5, 6)));
    }

    @Test
    public void testGetRandomTarget() {
        Point target = decider.getRandomTarget();
        assertNotNull(target);
        assertTrue(target.x >= 0 && target.x < 10);
        assertTrue(target.y >= 0 && target.y < 10);
    }

    @Test
    public void testGetNextTarget() {
        Point target = decider.getNextTarget();
        assertNotNull(target);
    }
}
