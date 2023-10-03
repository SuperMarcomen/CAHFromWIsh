package it.marcodemartino.cah.client.ui.scenes;

import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.actions.Action;
import it.marcodemartino.cah.client.actions.CreateGameAction;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.elements.BigWhiteButton;
import it.marcodemartino.cah.client.ui.elements.LabelWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScene extends InitPane {

    private final Invoker invoker;
    private final GameManager gameManager;
    private final SceneController sceneController;

    public MainScene(Invoker invoker, GameManager gameManager, SceneController sceneController, Stage primaryStage) {
        super(primaryStage);
        this.invoker = invoker;
        this.gameManager = gameManager;
        this.sceneController = sceneController;
    }

    @Override
    public void init() {
        VBox mainContainer = new VBox();
        mainContainer.prefWidthProperty().bind(widthProperty());
        mainContainer.prefHeightProperty().bind(heightProperty());
        setId("background");

        HBox labelWrapper = new LabelWrapper("Cards Against Humanity", "titleLabel");

        HBox nameLabelWrapper = new LabelWrapper("Enter your name", "nameLabel");

        TextField nameInput = createNameInput();

        HBox nameInputWrapper = new HBox(nameInput);
        nameInputWrapper.setAlignment(Pos.CENTER);

        Button startButton = createStartButton(nameInput);
        Button joinButton = createJoinButton(nameInput);
        HBox buttonsContainer = wrapButtons(startButton, joinButton);

        mainContainer.getChildren().addAll(labelWrapper, createSpacer(), nameLabelWrapper, nameInputWrapper, createSpacer(), buttonsContainer, createSpacer());
        mainContainer.setPadding(new Insets(20));
        mainContainer.setSpacing(20);

        getChildren().add(mainContainer);
    }

    private static HBox wrapButtons(Button startButton, Button joinButton) {
        HBox buttonsContainer = new HBox();
        buttonsContainer.setAlignment(Pos.CENTER);
        buttonsContainer.setSpacing(50);
        buttonsContainer.getChildren().addAll(startButton, joinButton);
        return buttonsContainer;
    }

    private TextField createNameInput() {
        TextField nameInput = new TextField();
        nameInput.setMaxWidth(Control.USE_PREF_SIZE);
        nameInput.prefWidthProperty().bind(widthProperty().divide(5));
        nameInput.setId("nameInput");
        nameInput.setPromptText("Mario");
        return nameInput;
    }

    private Button createStartButton(TextField nameInput) {
        Button startButton = new BigWhiteButton("Start a new game", widthProperty(), heightProperty());
        startButton.setOnAction(e -> handleStartButtonClick(nameInput));
        startButton.prefWidthProperty().bind(widthProperty().divide(3));
        startButton.prefHeightProperty().bind(heightProperty().divide(10));
        return startButton;
    }

    private void handleStartButtonClick(TextField nameInput) {
        if (checkNameEmpty(nameInput)) return;
        Action createAction = new CreateGameAction();
        invoker.execute(createAction);
        gameManager.createDummyPlayer(nameInput.getText());
    }

    private Button createJoinButton(TextField nameInput) {
        Button joinButton = new BigWhiteButton("Join a game", widthProperty(), heightProperty());
        joinButton.setOnAction(e -> handleJoinButtonClick(nameInput));
        return joinButton;
    }

    private void handleJoinButtonClick(TextField nameInput) {
        if (checkNameEmpty(nameInput)) return;
        gameManager.createDummyPlayer(nameInput.getText());
        sceneController.activate("join_game");
    }

    private boolean checkNameEmpty(TextField nameInput) {
        if (nameInput.getText().isEmpty()) {
            showPopup("The name can not be empty!");
            return true;
        }
        return false;
    }
}
