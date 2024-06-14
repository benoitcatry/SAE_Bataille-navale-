import boardifier.control.Logger;
import boardifier.control.StageFactory;
import boardifier.model.Model;
import control.AudioController;
import control.BattleShipControler;
import control.ButtonController;
import control.PageControl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.HomePage;
import view.SelectionPage;
import view.ShipRootPane;
import view.ShipView;

public class Ship extends Application {

    public static void main(String[] args) {
        Logger.setLevel(Logger.LOGGER_TRACE);
        Logger.setVerbosity(Logger.VERBOSE_HIGH);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Logger.setLevel(Logger.LOGGER_DEBUG);
        Model model = new Model();

        StageFactory.registerModelAndView("ship", "model.BattleShipStageModel", "view.ShipStageView");

        ShipRootPane root = new ShipRootPane();
        ShipView battleShipView = new ShipView(model, stage, root);
        BattleShipControler control = new BattleShipControler(model,battleShipView);

        PageControl pageControl = new PageControl(root,control);
        HomePage homePage = new HomePage();
        SelectionPage selectionPage = new SelectionPage();

        // Placer les widgets dans les pages
        homePage.placeWidgets(root);
        selectionPage.placeWidgets(root);
        AudioController audio = new AudioController();
        audio.listAudioFiles();
        audio.playMiss();

        // Créer et attacher le contrôleur de boutons
        new ButtonController(selectionPage, homePage, model, pageControl);

        // Afficher la page d'accueil
        pageControl.hp(homePage);
        stage.setTitle("BattleShip");
        stage.show();
    }
}

