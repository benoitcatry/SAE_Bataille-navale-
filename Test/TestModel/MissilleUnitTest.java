package TestModel;
import boardifier.model.GameStageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import model.Missille;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class MissilleUnitTest {
    private Missille missille;
    private GameStageModel gameStageModel;

    @BeforeEach
    void setUp() {
        gameStageModel = mock(GameStageModel.class);
        missille = new Missille(5, 2, 1, gameStageModel);
    }

    @Test
    void testGetNumber() {
        assertEquals(5, missille.getNumber());
    }

    @Test
    void testSetNumber() {
        missille.setNumber(10);
        assertEquals(10, missille.getNumber());
    }

    @Test
    void testGetColor() {
        assertEquals(2, missille.getColor());
    }

    @Test
    void testSetColor() {
        missille.setColor(3);
        assertEquals(3, missille.getColor());
    }

    @Test
    void testGetIdjoueur() {
        assertEquals(1, missille.getIdjoueur());
    }

    @Test
    void testSetIdjoueur() {
        missille.setIdjoueur(2);
        assertEquals(2, missille.getIdjoueur());
    }


}
