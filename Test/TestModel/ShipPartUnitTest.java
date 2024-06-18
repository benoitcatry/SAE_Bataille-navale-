package model;

import boardifier.model.GameStageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShipPartUnitTest {
    private shipPart shipPart;
    private GameStageModel gameStageModel;

    @BeforeEach
    void setUp() {
        gameStageModel = mock(GameStageModel.class);
        shipPart = new shipPart(5, 2, 1, gameStageModel);
    }

    @Test
    void testGetNumber() {
        assertEquals(5, shipPart.getNumber());
    }

    @Test
    void testSetNumber() {
        shipPart.setNumber(10);
        assertEquals(10, shipPart.getNumber());
    }

    @Test
    void testGetColor() {
        assertEquals(2, shipPart.getColor());
    }

    @Test
    void testSetColor() {
        shipPart.setColors(3);
        assertEquals(3, shipPart.getColor());
    }

    @Test
    void testSetCordoner() {
        shipPart.setCordoner(7, 8);
        assertEquals(7, shipPart.getcordonneX());
        assertEquals(8, shipPart.getcordonneY());
    }

    @Test
    void testSetcordonneX() {
        shipPart.setcordonneX(9);
        assertEquals(9, shipPart.getcordonneX());
    }

    @Test
    void testSetcordonneY() {
        shipPart.setcordonneY(10);
        assertEquals(10, shipPart.getcordonneY());
    }

    @Test
    void testSetToucher() {
        shipPart.setToucher(true);
        assertTrue(shipPart.esttoucher());
    }

    @Test
    void testEstToucher() {
        shipPart.setToucher(false);
        assertFalse(shipPart.esttoucher());
    }

    @Test
    void testGetIdplayer() {
        assertEquals(1, shipPart.getIdplayer());
    }


}
