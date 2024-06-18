package TestModel;

import model.shipPart;
import boardifier.model.GameStageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import model.Ship;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ShipUnitTest {

    private Ship ship;
    private GameStageModel gameStageModel;
    private shipPart[] mockShipParts;

    @BeforeEach
    void setUp() {
        gameStageModel = mock(GameStageModel.class);
        ship = new Ship(0, 0, 3, gameStageModel);
        mockShipParts = new shipPart[3];
        for (int i = 0; i < 3; i++) {
            mockShipParts[i] = mock(shipPart.class);
        }
        ship.shipParts = mockShipParts;
    }

    @Test
    void testGetTaille() {
        assertEquals(3, ship.getTaille());
    }

    @Test
    void testSetSens() {
        ship.setSens(true);
        assertTrue(ship.getSens());

        ship.setSens(false);
        assertFalse(ship.getSens());
    }

    @Test
    void testSetShipParts() {
        ship.setShipParts(gameStageModel);
        assertNotNull(ship.getshippart());
        assertEquals(3, ship.getshippart().length);
    }

    @Test
    void testSetCordonnerShipVertical() {
        when(mockShipParts[0].getcordonneX()).thenReturn(0);
        when(mockShipParts[0].getcordonneY()).thenReturn(0);
        when(mockShipParts[1].getcordonneX()).thenReturn(0);
        when(mockShipParts[1].getcordonneY()).thenReturn(1);
        when(mockShipParts[2].getcordonneX()).thenReturn(0);
        when(mockShipParts[2].getcordonneY()).thenReturn(2);

        assertTrue(ship.setCordonnerShip(0, 0, 'V'));
        verify(mockShipParts[0]).setCordoner(0, 0);
        verify(mockShipParts[1]).setCordoner(0, 1);
        verify(mockShipParts[2]).setCordoner(0, 2);
    }

    @Test
    void testSetCordonnerShipHorizontal() {
        when(mockShipParts[0].getcordonneX()).thenReturn(0);
        when(mockShipParts[0].getcordonneY()).thenReturn(0);
        when(mockShipParts[1].getcordonneX()).thenReturn(1);
        when(mockShipParts[1].getcordonneY()).thenReturn(0);
        when(mockShipParts[2].getcordonneX()).thenReturn(2);
        when(mockShipParts[2].getcordonneY()).thenReturn(0);

        assertTrue(ship.setCordonnerShip(0, 0, 'H'));
        verify(mockShipParts[0]).setCordoner(0, 0);
        verify(mockShipParts[1]).setCordoner(1, 0);
        verify(mockShipParts[2]).setCordoner(2, 0);
    }

    @Test
    void testSetCordonnerShipInvalid() {
        assertFalse(ship.setCordonnerShip(0, 8, 'V'));
        assertFalse(ship.setCordonnerShip(8, 0, 'H'));
    }

    @Test
    void testVerifCouler() {
        when(mockShipParts[0].esttoucher()).thenReturn(true);
        when(mockShipParts[1].esttoucher()).thenReturn(true);
        when(mockShipParts[2].esttoucher()).thenReturn(true);

        ship.verifcouler();
        assertTrue(ship.getcouler());
        for (shipPart part : mockShipParts) {
            verify(part).setColors(2);
        }
    }

    @Test
    void testNbDepartCouler() {
        when(mockShipParts[0].esttoucher()).thenReturn(true);
        when(mockShipParts[1].esttoucher()).thenReturn(false);
        when(mockShipParts[2].esttoucher()).thenReturn(true);

        assertEquals(2, ship.nbdepartcouler());
    }

    @Test
    void testGetPartCordonneX() {
        when(mockShipParts[1].getcordonneX()).thenReturn(5);
        assertEquals(5, ship.getPartCordonneX(1));
    }

    @Test
    void testGetPartCordonneY() {
        when(mockShipParts[1].getcordonneY()).thenReturn(5);
        assertEquals(5, ship.getPartCordonneY(1));
    }

    @Test
    void testSetIdPlayer() {
        ship.setidplayer(2);
        assertEquals(2, ship.getPlayerID());
    }

    @Test
    void testGetShipID() {
        assertEquals(0, ship.getShipID());
    }
}
