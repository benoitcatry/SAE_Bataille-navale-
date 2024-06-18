package model;

import boardifier.model.GameStageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BattleBoardTest {

    private BattleBoard battleBoard;
    private GameStageModel gameStageModel;

    @BeforeEach
    void setUp() {
        gameStageModel = mock(GameStageModel.class);
        battleBoard = new BattleBoard(0, 0, gameStageModel, "TestBoard");
    }

    @Test
    void testSetValidCells() {
        // Arrange
        BattleBoard spyBattleBoard = spy(battleBoard);
        doReturn(List.of(new Point(1, 1), new Point(2, 2))).when(spyBattleBoard).computeValidCells(anyInt());

        // Act
        spyBattleBoard.setValidCells(1);

        // Assert
        verify(spyBattleBoard).resetReachableCells(false);
        assertTrue(spyBattleBoard.getReachableCells()[1][1]);
        assertTrue(spyBattleBoard.getReachableCells()[2][2]);
    }

    @Test
    void testComputeValidCells_whenBoardIsEmpty() {
        // Arrange
        BattleBoard spyBattleBoard = spy(battleBoard);
        doReturn(true).when(spyBattleBoard).isEmpty();

        // Act
        List<Point> result = spyBattleBoard.computeValidCells(1);

        // Assert
        assertEquals(100, result.size());
        assertTrue(result.contains(new Point(0, 0)));
        assertTrue(result.contains(new Point(9, 9)));
    }

    @Test
    void testComputeValidCells_whenBoardIsNotEmpty() {
        // Arrange
        BattleBoard spyBattleBoard = spy(battleBoard);
        doReturn(false).when(spyBattleBoard).isEmpty();
        doReturn(true).when(spyBattleBoard).isEmptyAt(anyInt(), anyInt());
        Missille mockMissille = mock(Missille.class);
        doReturn(mockMissille).when(spyBattleBoard).getElement(anyInt(), anyInt());
        when(mockMissille.getColor()).thenReturn(1);

        // Act
        List<Point> result = spyBattleBoard.computeValidCells(1);

        // Assert
        assertNotNull(result);
        // Add additional assertions based on expected behavior
    }
}