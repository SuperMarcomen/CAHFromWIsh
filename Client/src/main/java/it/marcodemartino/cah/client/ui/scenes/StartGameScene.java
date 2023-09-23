package it.marcodemartino.cah.client.ui.scenes;

import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.actions.Action;
import it.marcodemartino.cah.client.actions.StartGameAction;
import it.marcodemartino.cah.client.game.GameManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class StartGameScene extends InitPane {

    private final Invoker invoker;
    private final GameManager gameManager;
    private final SceneController sceneController;

    public StartGameScene(Invoker invoker, GameManager gameManager, SceneController sceneController) {
        this.invoker = invoker;
        this.gameManager = gameManager;
        this.sceneController = sceneController;
    }


    @Override
    public void init() {
        VBox mainContainer = new VBox();

        Label titleLabel = new Label("Cards Against Humanity");
        titleLabel.setId("titleLabel");

        TextFlow textFlow = new TextFlow();

        Text gameText = new Text("Game ID: ");
        gameText.getStyleClass().add("bold-text");
        Text id = new Text(gameManager.getGame().getUuid().toString() + System.lineSeparator());
        Text description = new Text("Share this ID with your friends to play together!");
        textFlow.getChildren().addAll(gameText, id, description);

        Button startButton = new Button("Start the game");
        startButton.setOnAction(e -> {
            Action startAction = new StartGameAction(gameManager.getGame().getUuid());
            invoker.execute(startAction);
        });

        mainContainer.getChildren().addAll(titleLabel, textFlow, startButton);
        getChildren().add(mainContainer);
    }

    private void waitOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
