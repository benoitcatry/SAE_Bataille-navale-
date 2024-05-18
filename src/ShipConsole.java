import boardifier.control.Logger;
import boardifier.model.GameException;
import boardifier.view.View;
import boardifier.control.StageFactory;
import boardifier.model.Model;
import java.lang.*;
import java.util.Scanner;

public class ShipConsole {

    static boolean  f;

    public static int selectionModeDeJeux(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("selectionner le mode de jeux : Mode 1 : Joueur contre joueur \n" +
                "                               Mode 2 : joueur contre ordinateur\n" +
                "                               Mode 3 : ordinateur contre ordinateur"  );
        int mode;
        while (true){
            mode = scanner.nextInt();
            if(mode == 1 || mode == 2 || mode ==3){
                return mode;
            }else {
                System.out.println("Erreur: Uniquement 1 2 et 3 sont accepter");
            }
        }


    }

    public static void main(String[] args) {

        Logger.setLevel(Logger.LOGGER_TRACE);
        Logger.setVerbosity(Logger.VERBOSE_HIGH);
        int mode = selectionModeDeJeux();
        if (args.length == 1) {
            try {
                mode = Integer.parseInt(args[0]);
                if ((mode <0) || (mode>2)) mode = 0;
            }
            catch(NumberFormatException e) {
                mode = 0;
            }
        }
        Model model = new Model();
        if (mode == 0) {
            model.addHumanPlayer("player1");
            model.addHumanPlayer("player2");
            f = true;
        }
        else if (mode == 1) {
            model.addHumanPlayer("player");
            model.addComputerPlayer("computer");
            f = true;
        }
        else if (mode == 2) {
            model.addComputerPlayer("computer1");
            model.addComputerPlayer("computer2");
            f = false;
        }

        StageFactory.registerModelAndView("ship", "model.BattleShipStageModel", "view.ShipStageView");
        View battleShipView = new View(model);
        control.BattleShipControler control = new control.BattleShipControler(model,battleShipView, f);
        control.setFirstStageName("ship");
        try {
            control.startGame();
            control.stageLoop();
        }
        catch(GameException e) {
            System.out.println(e);
            System.out.println("Cannot start the game. Abort");
        }
    }

}
