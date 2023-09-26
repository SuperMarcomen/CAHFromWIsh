package it.marcodemartino.cah.client.ui;

import it.marcodemartino.cah.client.Client;
import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.scenes.JoinGameScene;
import it.marcodemartino.cah.client.ui.scenes.MainScene;
import it.marcodemartino.cah.client.ui.scenes.PlayCardsScene;
import it.marcodemartino.cah.client.ui.scenes.SceneController;
import it.marcodemartino.cah.client.ui.scenes.ShowCardsScene;
import it.marcodemartino.cah.client.ui.scenes.StartGameScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CardsAgainstHumanity extends Application {

    @Override
    public void start(Stage primaryStage) {
        GameManager gameManager = new GameManager();
        SceneController sceneController = new SceneController();
        Client client = new Client(gameManager, sceneController);
        client.start();
        Invoker invoker = new Invoker(client);


        MainScene mainScene = new MainScene(invoker, gameManager, sceneController);
        Scene scene = new Scene(mainScene, 1500, 800);
        sceneController.setMain(scene);

        sceneController.addScreen("start_game", new StartGameScene(invoker, gameManager, sceneController));
        sceneController.addScreen("join_game", new JoinGameScene(invoker, gameManager));
        sceneController.addScreen("play_cards", new PlayCardsScene(gameManager, invoker));
        sceneController.addScreen("show_all_cards", new ShowCardsScene(gameManager, sceneController));

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
