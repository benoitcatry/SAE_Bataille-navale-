package TestModel;
import boardifier.model.GameStageModel;
import model.BattleShipStageFactory;
import model.BattleShipStageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import model.Missille;

import static org.junit.Assert.*;

public class BattleShipeStageFactoryUnitTest {
    private BattleShipStageModel stageModel;
    private BattleShipStageFactory factory;

    @BeforeEach
    void setUp() {
        stageModel = Mockito.mock(BattleShipStageModel.class);
        factory = new BattleShipStageFactory(stageModel);
    }

    @Test
    void testSetupMode1() {
        factory.setupMode1();

        assertNotNull(stageModel.getPlayer1Name());
        assertNotNull(stageModel.getPlayer2Name());
        assertNotNull(stageModel.getBoardPlayer1());
        assertNotNull(stageModel.getBoardPlayer2());
        assertNotNull(stageModel.getShipsPlayer1());
        assertNotNull(stageModel.getShipsPlayer2());
        assertNotNull(stageModel.getShipsPlayer1());
    }

    @Test
    void testSetupMode2() {
        factory.setupMode2();

        assertNotNull(stageModel.getPlayer1Name());
        assertNotNull(stageModel.getPlayer2Name());
        assertNotNull(stageModel.getBoardPlayer1());
        assertNotNull(stageModel.getBoardPlayer2());
        assertNotNull(stageModel.getShipsPlayer1());
        assertNotNull(stageModel.getShipsPlayer2());
    }

    @Test
    void testNbdeMissile() {
        int nb = factory.nbdeMissile();
        assertTrue(nb > 0);
    }

    @Test
    void testNbdeMissile2() {
        int nb = factory.nbdeMissile2();
        assertTrue(nb > 0);
    }

    @Test
    void testQuelleModeDeJeux() {
        int mode = factory.quelleModeDeJeux();
        assertTrue(mode == 1 || mode == 2);
    }

    @Test
    void testSetupMissile() {
        factory.setupmissile(10);

        assertNotNull(stageModel.getStockMissileJ1());
        assertNotNull(stageModel.getStockMissileJ2());
        assertEquals(10, stageModel.getMissileJoueur1().length);
        assertEquals(10, stageModel.getMissileJoueur2().length);
    }

    @Test
    void testSetup() {
        factory.setup();

        assertNotNull(stageModel.getPlayer1Name());
        assertNotNull(stageModel.getPlayer2Name());
        assertNotNull(stageModel.getBoardPlayer1());
        assertNotNull(stageModel.getBoardPlayer2());
        assertNotNull(stageModel.getShipsPlayer1());
        assertNotNull(stageModel.getShipsPlayer2());
        assertNotNull(stageModel.getStockMissileJ1());
        assertNotNull(stageModel.getStockMissileJ2());
    }
}
