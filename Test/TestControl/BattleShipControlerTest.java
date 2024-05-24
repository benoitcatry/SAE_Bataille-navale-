package TestControl;

import boardifier.model.GameStageModel;
import boardifier.model.Model;
import boardifier.model.Player;
import boardifier.view.View;
import control.BattleShipControler;
import model.BattleShipStageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class BattleShipControlerTest {

    private BattleShipControler controler;
    private GameStageModel gameStageModel;
    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        gameStageModel = new BattleShipStageModel("Model",new Model());
        player1 = new Player(Player.HUMAN,"Player 1");
        player2 = new Player(Player.HUMAN,"Player 2");
        controler = new BattleShipControler(Mockito.mock(Model.class), new View(new Model()), true);
    }

    @Test
    void testAnalyseAndPlayPose() {
        boolean result = controler.analyseAndPlayPose("H3", 0);
        assertFalse(result); // Should return false because it's an invalid instruction
    }

    @Test
    void testAnalyseAndPlay() {
        boolean result = controler.analyseAndPlay("A1");
        assertFalse(result); // Should return false because it's an invalid instruction
    }

    @Test
    void testQuiJoueEnPremier() {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        controler.quiJoueEnPremier();
        assertTrue(controler.quiJouePremier);
    }
}
