package TestModel;


import boardifier.model.Model;
import boardifier.model.TextElement;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class BattleShipStageModelUnitTest {

    private BattleShipStageModel battleShipStageModel;
    private Model model;
    private BattleBoard boardPlayer1;
    private BattleBoard boardPlayer2;
    private Ship[] shipsPlayer1;
    private Ship[] shipsPlayer2;
    private TextElement player1Name;
    private TextElement player2Name;
    private StockMissile stockMissileJ1;
    private StockMissile stockMissileJ2;
    private Missille missille;

    @BeforeEach
    public void setUp() {
        model = mock(Model.class);
        battleShipStageModel = new BattleShipStageModel("TestStage", model);
        boardPlayer1 = mock(BattleBoard.class);
        boardPlayer2 = mock(BattleBoard.class);
        missille = mock(Missille.class);
        shipsPlayer1 = new Ship[5];
        shipsPlayer2 = new Ship[5];
        for (int i = 0; i < 5; i++) {
            shipsPlayer1[i] = mock(Ship.class);
            shipsPlayer2[i] = mock(Ship.class);
        }
        battleShipStageModel.setBoardPlayer1(boardPlayer1);
        battleShipStageModel.setBoardPlayer2(boardPlayer2);
        battleShipStageModel.setShipsPlayer1(shipsPlayer1);
        battleShipStageModel.setShipsPlayer2(shipsPlayer2);
    }



    @Test
    public void testSetStockMissileJ1() {
        battleShipStageModel.setStockMissileJ1(stockMissileJ1);
        assertEquals(stockMissileJ1, battleShipStageModel.getStockMissileJ1());
    }

    @Test
    public void testSetStockMissileJ2() {
        battleShipStageModel.setStockMissileJ2(stockMissileJ2);
        assertEquals(stockMissileJ2, battleShipStageModel.getStockMissileJ2());
    }

    @Test
    public void testSetShipsPlayer1() {
        battleShipStageModel.setShipsPlayer1(shipsPlayer1);
        assertArrayEquals(shipsPlayer1, battleShipStageModel.getShipsPlayer1());
    }

    @Test
    public void testSetShipsPlayer2() {
        battleShipStageModel.setShipsPlayer2(shipsPlayer2);
        assertArrayEquals(shipsPlayer2, battleShipStageModel.getShipsPlayer2());
    }

    @Test
    public void testSetPlayer1Name() {
        battleShipStageModel.setPlayer1Name(player1Name);
        assertEquals(player1Name, battleShipStageModel.getPlayer1Name());
    }

    @Test
    public void testSetPlayer2Name() {
        battleShipStageModel.setPlayer2Name(player2Name);
        assertEquals(player2Name, battleShipStageModel.getPlayer2Name());
    }


    @Test
    public void testVerifpeutetreposer() {

        when(shipsPlayer1[0].getPartCordonneX(anyInt())).thenReturn(1);
        when(shipsPlayer1[0].getPartCordonneY(anyInt())).thenReturn(1);
        when(shipsPlayer1[0].getTaille()).thenReturn(3);

        boolean result = battleShipStageModel.Verifpeutetreposer(shipsPlayer1, 4, 4, 3, 'H');
        assertTrue(result);

        result = battleShipStageModel.Verifpeutetreposer(shipsPlayer1, 1, 1, 3, 'H');
        assertFalse(result);
    }

    @Test
    public void testToutShipCouler() {
        when(shipsPlayer1[0].getcouler()).thenReturn(true);
        when(shipsPlayer1[1].getcouler()).thenReturn(true);
        when(shipsPlayer1[2].getcouler()).thenReturn(true);
        when(shipsPlayer1[3].getcouler()).thenReturn(true);
        when(shipsPlayer1[4].getcouler()).thenReturn(true);

        boolean result = battleShipStageModel.toutShipCouler(shipsPlayer1);
        assertTrue(result);

        when(shipsPlayer1[4].getcouler()).thenReturn(false);
        result = battleShipStageModel.toutShipCouler(shipsPlayer1);
        assertFalse(result);
    }

    @Test
    public void testSetupCallbacks_Player1Missille() {
        when(missille.getIdjoueur()).thenReturn(1);

        // Trigger the callback
        battleShipStageModel.setupCallbacks();


        assertEquals(49, battleShipStageModel.getPlayer1ToPlay());
        verify(missille, times(1)).getIdjoueur();
    }





    @Mock
    private Model mockModel;




    @Test
    void testComputePartyResult() {
        BattleShipStageModel model = new BattleShipStageModel("TestModel", mockModel);
        TextElement mockTextElement = mock(TextElement.class);
        model.setTextWiner(mockTextElement);

        Ship mockShip1 = mock(Ship.class);
        Ship mockShip2 = mock(Ship.class);
        when(mockShip1.nbdepartcouler()).thenReturn(2);
        when(mockShip2.nbdepartcouler()).thenReturn(3);
        Ship[] ships1 = {mockShip1};
        Ship[] ships2 = {mockShip2};
        model.setShipsPlayer1(ships1);
        model.setShipsPlayer2(ships2);

        model.computePartyResult();
        verify(mockTextElement).setText("The Winner is Player 2");
        verify(mockTextElement).setVisible(true);

        when(mockShip2.nbdepartcouler()).thenReturn(2);
        model.computePartyResult();
        verify(mockTextElement).setText("Equality");
    }

    @Test
    void testToucheroupas() {
        BattleShipStageModel model = new BattleShipStageModel("TestModel", mockModel);
        Ship mockShip = mock(Ship.class);
        shipPart mockShipPart = mock(shipPart.class);
        when(mockShip.getshippart()).thenReturn(new shipPart[]{mockShipPart});
        when(mockShipPart.getcordonneX()).thenReturn(1);
        when(mockShipPart.getcordonneY()).thenReturn(2);
        when(mockShipPart.esttoucher()).thenReturn(false);

        Ship[] ships = {mockShip};

        assertFalse(model.toucheroupas(ships, 1, 2));
    }

    @Test
    void testNbdepart() {
        BattleShipStageModel model = new BattleShipStageModel("TestModel", mockModel);
        Ship mockShip1 = mock(Ship.class);
        Ship mockShip2 = mock(Ship.class);
        when(mockShip1.getTaille()).thenReturn(3);
        when(mockShip2.getTaille()).thenReturn(2);
        Ship[] ships = {mockShip1, mockShip2};

        assertEquals(5, model.nbdepart(ships));
    }



    @Test
    void testComputePartyResult_Player1Wins() {
        BattleShipStageModel model = new BattleShipStageModel("TestModel", mockModel);
        TextElement mockTextElement = mock(TextElement.class);
        model.setTextWiner(mockTextElement);
        Ship mockShip1 = mock(Ship.class);
        Ship mockShip2 = mock(Ship.class);
        when(mockShip1.nbdepartcouler()).thenReturn(3);
        when(mockShip2.nbdepartcouler()).thenReturn(0);
        model.setShipsPlayer1(new Ship[]{mockShip1});
        model.setShipsPlayer2(new Ship[]{mockShip2});

        model.computePartyResult();

        verify(mockTextElement).setText("The Winner is Player 1");
        verify(mockTextElement).setVisible(true);
    }

    @Test
    void testToucheroupas_Hit() {

        BattleShipStageModel model = new BattleShipStageModel("TestModel", mockModel);
        Ship mockShip = mock(Ship.class);
        shipPart mockShipPart = mock(shipPart.class);
        when(mockShipPart.getcordonneX()).thenReturn(1);
        when(mockShipPart.getcordonneY()).thenReturn(1);
        when(mockShip.shipParts).thenReturn(new shipPart[]{mockShipPart});


        boolean result = model.toucheroupas(model.getShipsPlayer1(), 1, 1);


        assertTrue(result);
        verify(mockShipPart).setToucher(true);
        verify(mockShip).verifcouler();
    }

    @Test
    void testToucheroupas_Miss() {
        // Setup
        BattleShipStageModel model = new BattleShipStageModel("TestModel", mockModel);
        Ship mockShip = mock(Ship.class);
        shipPart mockShipPart = mock(shipPart.class);
        when(mockShipPart.getcordonneX()).thenReturn(1);
        when(mockShipPart.getcordonneY()).thenReturn(1);

        when(mockShip.shipParts).thenReturn(new shipPart[]{mockShipPart});

        // Call method under test with different coordinates
        boolean result = model.toucheroupas(model.getShipsPlayer1(), 2, 2);

        // Verify
        assertFalse(result);
        verify(mockShipPart, never()).setToucher(true); // Ensure setToucher(true) was never called
        verify(mockShip, never()).verifcouler(); // Ensure verifcouler() was never called
    }



    @Test
    void testVerifpeutetreposer_ValidPlacement() {
        // Setup
        BattleShipStageModel model = new BattleShipStageModel("TestModel", mockModel);
        Ship mockShip = mock(Ship.class);
        shipPart mockShipPart = mock(shipPart.class);
        when(mockShip.getTaille()).thenReturn(3); // Taille arbitraire pour le navire
        when(mockShip.shipParts).thenReturn(new shipPart[]{mockShipPart, mockShipPart, mockShipPart});
        when(mockShipPart.getcordonneX()).thenReturn(1); // Coordonnées arbitraires
        when(mockShipPart.getcordonneY()).thenReturn(1);

        Ship[] ships = new Ship[]{mockShip};

        boolean result = model.Verifpeutetreposer(ships, 0, 0, 3, 'H');

        assertTrue(result);
    }

    @Test
    void testVerifpeutetreposer_InvalidPlacement() {
        // Setup
        BattleShipStageModel model = new BattleShipStageModel("TestModel", mockModel);
        Ship mockShip = mock(Ship.class);
        shipPart mockShipPart = mock(shipPart.class);
        when(mockShip.getTaille()).thenReturn(3); // Taille arbitraire pour le navire
        when(mockShip.shipParts).thenReturn(new shipPart[]{mockShipPart, mockShipPart, mockShipPart});
        when(mockShipPart.getcordonneX()).thenReturn(1); // Coordonnées arbitraires
        when(mockShipPart.getcordonneY()).thenReturn(1);

        Ship[] ships = new Ship[]{mockShip};


        boolean result = model.Verifpeutetreposer(ships, 0, 1, 3, 'H');


        assertFalse(result);
    }



    @Test
    void testComputePartyResult_Player2Wins() {
        // Setup
        BattleShipStageModel model = new BattleShipStageModel("TestModel", mockModel);
        Ship mockShip1 = mock(Ship.class);
        Ship mockShip2 = mock(Ship.class);
        when(mockShip1.nbdepartcouler()).thenReturn(2); // Nombre arbitraire de parties coulées pour mockShip1
        when(mockShip2.nbdepartcouler()).thenReturn(3); // Nombre arbitraire de parties coulées pour mockShip2
        model.setShipsPlayer1(new Ship[]{mockShip1});
        model.setShipsPlayer2(new Ship[]{mockShip2});

        // Call method under test
        model.computePartyResult();

        // Verify
        verify(mockModel).setIdWinner(2); // Ensure player 2 is set as the winner
        verify(mockModel).stopStage(); // Ensure model.stopStage() is called
    }





}
