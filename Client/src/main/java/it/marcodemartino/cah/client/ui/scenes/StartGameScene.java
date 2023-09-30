package it.marcodemartino.cah.client.ui.scenes;

import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.actions.Action;
import it.marcodemartino.cah.client.actions.StartGameAction;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.elements.LabelWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class StartGameScene extends InitPane {

    private final Invoker invoker;
    private final GameManager gameManager;
    private final SceneController sceneController;
    private final Text playersListText;

    public StartGameScene(Invoker invoker, GameManager gameManager, SceneController sceneController) {
        this.invoker = invoker;
        this.gameManager = gameManager;
        this.sceneController = sceneController;
        this.playersListText = new Text();
        sceneController.setPlayersJoinedText(playersListText.textProperty());
    }

    @Override
    public void init() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(gameManager.getGame().getUuid().toString());
        clipboard.setContent(content);

        VBox mainContainer = new VBox();
        mainContainer.setPadding(new Insets(20));
        setId("background");

        HBox titleLabel = new LabelWrapper("Cards Against Humanity", "titleLabel");

        TextFlow textFlow = new TextFlow();

        Text gameText = new Text("Game ID: ");
        gameText.getStyleClass().add("bold");
        Text id = new Text(gameManager.getGame().getUuid().toString() + System.lineSeparator());
        id.getStyleClass().add("regular");

        Text description = new Text("Share this ID with your friends to play together!");
        description.setId("regular");
        textFlow.getChildren().addAll(gameText, id, description);

        TextFlow playerInGame = new TextFlow();

        Text playersText = new Text("Players: ");
        playersText.getStyleClass().add("bold");

        playersListText.getStyleClass().add("regular");
        playerInGame.getChildren().addAll(playersText, playersListText);
        sceneController.setPlayersJoinedText(playersListText.textProperty());

        HBox gameTextWrapper = new HBox(textFlow);
        gameTextWrapper.prefWidthProperty().bind(widthProperty());
        gameTextWrapper.setAlignment(Pos.CENTER);

        Button startButton = new Button("Start the game");
        startButton.setOnAction(e -> {
            Action startAction = new StartGameAction(gameManager.getGame().getUuid());
            invoker.execute(startAction);
        });
        startButton.prefWidthProperty().bind(widthProperty().divide(3));
        startButton.prefHeightProperty().bind(heightProperty().divide(10));

        HBox startButtonWrapper = new HBox(startButton);
        startButtonWrapper.prefWidthProperty().bind(widthProperty());
        startButtonWrapper.setAlignment(Pos.CENTER);

        mainContainer.getChildren().addAll(titleLabel, createSpacer(), gameTextWrapper, playersText, createSpacer(), startButtonWrapper, createSpacer());
        getChildren().add(mainContainer);
    }

    private Node createSpacer() {
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
}
