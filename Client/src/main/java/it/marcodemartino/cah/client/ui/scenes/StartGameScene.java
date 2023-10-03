package it.marcodemartino.cah.client.ui.scenes;

import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.actions.Action;
import it.marcodemartino.cah.client.actions.StartGameAction;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.elements.BigWhiteButton;
import it.marcodemartino.cah.client.ui.elements.LabelWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class StartGameScene extends InitPane {

    private final Invoker invoker;
    private final GameManager gameManager;
    private final Text playersListText;

    public StartGameScene(Invoker invoker, GameManager gameManager, SceneController sceneController, Stage primaryStage) {
        super(primaryStage);
        this.invoker = invoker;
        this.gameManager = gameManager;
        this.playersListText = new Text();
        sceneController.setPlayersJoinedText(playersListText.textProperty());
    }

    @Override
    public void init() {
        setGameIdInClipboard();

        VBox mainContainer = new VBox();
        mainContainer.setPadding(new Insets(20));
        setId("background");

        HBox titleLabel = new LabelWrapper("Cards Against Humanity", "titleLabel");

        TextFlow textFlow = createDescriptiveText();
        TextFlow playerInGame = createPlayersInGameText();

        HBox textsWrapper = wrapTexts(textFlow, playerInGame);

        Button startButton = new BigWhiteButton("Start the game", widthProperty(), heightProperty());
        startButton.setOnAction(e -> handleStartButtonClick());

        HBox startButtonWrapper = new HBox(startButton);
        startButtonWrapper.setAlignment(Pos.CENTER);

        mainContainer.getChildren().addAll(titleLabel, createSpacer(), textsWrapper, createSpacer(), startButtonWrapper, createSpacer());
        getChildren().add(mainContainer);
    }

    private void handleStartButtonClick() {
        Action startAction = new StartGameAction(gameManager.getGame().getUuid());
        invoker.execute(startAction);
    }

    private HBox wrapTexts(TextFlow textFlow, TextFlow playerInGame) {
        VBox textContainer = new VBox(textFlow, playerInGame);
        textContainer.setSpacing(50);
        HBox gameTextWrapper = new HBox(textContainer);
        gameTextWrapper.prefWidthProperty().bind(widthProperty());
        gameTextWrapper.setAlignment(Pos.CENTER);
        return gameTextWrapper;
    }

    private TextFlow createPlayersInGameText() {
        TextFlow playerInGame = new TextFlow();

        Text playersText = new Text("Players: ");
        playersText.getStyleClass().add("bold");

        playersListText.getStyleClass().add("regular");
        playerInGame.getChildren().addAll(playersText, playersListText);
        return playerInGame;
    }

    private TextFlow createDescriptiveText() {
        TextFlow textFlow = new TextFlow();

        Text gameText = new Text("Game ID: ");
        gameText.getStyleClass().add("bold");
        Text id = new Text(gameManager.getGame().getUuid().toString() + System.lineSeparator() + System.lineSeparator());
        id.getStyleClass().add("regular");

        Text description = new Text("Share this ID with your friends to play together!" + System.lineSeparator());
        description.getStyleClass().add("regular");
        Text clipboardText = new Text("(The ID is already copied in your clipboard)");
        clipboardText.getStyleClass().add("light");
        textFlow.getChildren().addAll(gameText, id, description, clipboardText);
        return textFlow;
    }

    private void setGameIdInClipboard() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(gameManager.getGame().getUuid().toString());
        clipboard.setContent(content);
    }
}
