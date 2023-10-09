package it.marcodemartino.cah.client.ui;

import it.marcodemartino.cah.client.Client;
import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.actions.Action;
import it.marcodemartino.cah.client.actions.QuitAction;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.scenes.ChooseDecksScene;
import it.marcodemartino.cah.client.ui.scenes.InitPane;
import it.marcodemartino.cah.client.ui.scenes.JoinGameScene;
import it.marcodemartino.cah.client.ui.scenes.MainScene;
import it.marcodemartino.cah.client.ui.scenes.PlayCardsScene;
import it.marcodemartino.cah.client.ui.scenes.SceneController;
import it.marcodemartino.cah.client.ui.scenes.ShowCardsScene;
import it.marcodemartino.cah.client.ui.scenes.WaitJoinGameScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CardsAgainstHumanity extends Application {

    private Client client;
    private Invoker invoker;
    private GameManager gameManager;

    public static void main(String[] args) {
        launch(args);
    }

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

        InitPane mainScene = new MainScene(invoker, gameManager, sceneController, primaryStage);
        mainScene.init();
        Scene scene = new Scene(mainScene, 1500, 800);
        sceneController.setMain(scene);

        sceneController.addScreen("start_game", new WaitJoinGameScene(invoker, gameManager, sceneController, primaryStage));
        sceneController.addScreen("join_game", new JoinGameScene(invoker, gameManager, primaryStage));
        sceneController.addScreen("play_cards", new PlayCardsScene(gameManager, invoker, primaryStage));
        sceneController.addScreen("show_all_cards", new ShowCardsScene(gameManager, sceneController, primaryStage));
        sceneController.addScreen("choose_decks", new ChooseDecksScene(sceneController, primaryStage, invoker, gameManager));

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
