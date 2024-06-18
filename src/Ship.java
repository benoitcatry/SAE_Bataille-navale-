import boardifier.control.Logger;
import boardifier.control.StageFactory;
import boardifier.model.Model;
import control.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.*;

public class Ship extends Application {
    private static int mode;
    public static void main(String[] args) {

        Logger.setLevel(Logger.LOGGER_TRACE);
        Logger.setVerbosity(Logger.VERBOSE_HIGH);
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        Logger.setLevel(Logger.LOGGER_DEBUG);
        Model model = new Model();
        HomePage homePage = new HomePage();
        StageFactory.registerModelAndView("ship", "model.BattleShipStageModel", "view.ShipStageView");
        ShipRootPane root = new ShipRootPane();
        AudioController audio = new AudioController();
        ShipView battleShipView = new ShipView(model, stage, root);
        BattleShipControler control = new BattleShipControler(model,battleShipView,root,homePage, audio);
        control.setFirstStageName("ship");

        OptionPage op = new OptionPage();

        PageControl pageControl = new PageControl(root,control,model,audio,op);
        SelectionPage selectionPage = new SelectionPage();

        // Placer les widgets dans les pages
        homePage.placeWidgets(root);
        selectionPage.placeWidgets(root);

        // Créer et attacher le contrôleur de boutons
        new ButtonController(selectionPage, homePage, model, pageControl,op);
        new TextController(model,selectionPage);

        // Afficher la page d'accueil
        pageControl.hp(homePage);
        stage.setTitle("BattleShip");
        stage.show();
        //audio.toggleMusic();
    }
}

