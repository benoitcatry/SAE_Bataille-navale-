import boardifier.control.Logger;
import boardifier.control.StageFactory;
import boardifier.model.Model;
import control.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.HomePage;
import view.SelectionPage;
import view.ShipRootPane;
import view.ShipView;

public class Ship extends Application {
    private static int mode;
    public static void main(String[] args) {
        /*
        Logger.setLevel(Logger.LOGGER_TRACE);
        Logger.setVerbosity(Logger.VERBOSE_HIGH);
        launch(args);*/
        if (args.length == 1) {
            try {
                mode = Integer.parseInt(args[0]);
                if ((mode <0) || (mode>2)) mode = 0;
            }
            catch(NumberFormatException e) {
                mode = 0;
            }
        }
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        Logger.setLevel(Logger.LOGGER_DEBUG);
        Model model = new Model();

        if (mode == 0) {
            model.addHumanPlayer("player1");
            model.addHumanPlayer("player2");
        }
        else if (mode == 1) {
            model.addHumanPlayer("player");
            model.addComputerPlayer("computer");
        }
        else if (mode == 2) {
            model.addComputerPlayer("computer1");
            model.addComputerPlayer("computer2");
        }

        StageFactory.registerModelAndView("ship", "model.BattleShipStageModel", "view.ShipStageView");

        ShipRootPane root = new ShipRootPane();
        ShipView battleShipView = new ShipView(model, stage, root);
        BattleShipControler control = new BattleShipControler(model,battleShipView);
        control.setFirstStageName("ship");

        //PageControl pageControl = new PageControl(root,control,model);
        //HomePage homePage = new HomePage();
        //SelectionPage selectionPage = new SelectionPage();

        // Placer les widgets dans les pages
        //homePage.placeWidgets(root);
        //selectionPage.placeWidgets(root);
        //AudioController audio = new AudioController();
        //audio.listAudioFiles();
        //audio.playMiss();

        // Créer et attacher le contrôleur de boutons
        //new ButtonController(selectionPage, homePage, model, pageControl);
        //new TextController(model,selectionPage);

        // Afficher la page d'accueil
        //pageControl.hp(homePage);
        stage.setTitle("BattleShip");
        stage.show();
    }
}

