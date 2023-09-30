package it.marcodemartino.cah.client.ui;

import it.marcodemartino.cah.client.Client;
import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.actions.Action;
import it.marcodemartino.cah.client.actions.QuitAction;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.scenes.JoinGameScene;
import it.marcodemartino.cah.client.ui.scenes.MainScene;
import it.marcodemartino.cah.client.ui.scenes.PlayCardsScene;
import it.marcodemartino.cah.client.ui.scenes.SceneController;
import it.marcodemartino.cah.client.ui.scenes.ShowCardsScene;
import it.marcodemartino.cah.client.ui.scenes.StartGameScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CardsAgainstHumanity extends Application {

    private Client client;
    private Invoker invoker;
    private GameManager gameManager;

    @Override
    public void start(Stage primaryStage) {
        Font.loadFont(getClass().getResourceAsStream("/fonts/Lato/Lato-Bold.ttf"), 16);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Lato/Lato-Regular.ttf"), 16);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Lato/Lato-Light.ttf"), 16);

        gameManager = new GameManager();
        SceneController sceneController = new SceneController();
        client = new Client(gameManager, sceneController);
        client.start();
        invoker = new Invoker(client);

        MainScene mainScene = new MainScene(invoker, gameManager, sceneController);
        Scene scene = new Scene(mainScene, 1500, 800);
        sceneController.setMain(scene);

        sceneController.addScreen("start_game", new StartGameScene(invoker, gameManager, sceneController));
        sceneController.addScreen("join_game", new JoinGameScene(invoker, gameManager));
        sceneController.addScreen("play_cards", new PlayCardsScene(gameManager, invoker));
        sceneController.addScreen("show_all_cards", new ShowCardsScene(gameManager, sceneController));

        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        primaryStage.setTitle("Cards against humanity");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        Action quitAction = new QuitAction(gameManager);
        invoker.execute(quitAction);
        client.stopConnection();
        super.stop();
    }
}
