package it.marcodemartino.cah.client.ui.scenes;

import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.actions.Action;
import it.marcodemartino.cah.client.actions.CreateGameAction;
import it.marcodemartino.cah.client.actions.JoinGameAction;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.elements.LabelWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.UUID;

public class MainScene extends StackPane {

    public MainScene(Invoker invoker, GameManager gameManager, SceneController sceneController) {
        VBox mainContainer = new VBox();
        mainContainer.prefWidthProperty().bind(widthProperty());
        mainContainer.prefHeightProperty().bind(heightProperty());
        setId("background");

        HBox labelWrapper = new LabelWrapper("Cards Against Humanity", "titleLabel");

        HBox nameLabelWrapper = new LabelWrapper("Enter your name", "nameLabel");

        TextField nameInput = new TextField();
        nameInput.setMaxWidth(Control.USE_PREF_SIZE);
        nameInput.prefWidthProperty().bind(widthProperty().divide(5));
        nameInput.setId("nameInput");
        HBox nameInputWrapper = new HBox(nameInput);
        nameInputWrapper.prefWidthProperty().bind(widthProperty());
        nameInputWrapper.setAlignment(Pos.CENTER);

        HBox buttonsContainer = new HBox();
        buttonsContainer.setAlignment(Pos.CENTER);
        buttonsContainer.setSpacing(50);
        createStartButton(invoker, gameManager, sceneController, nameInput, buttonsContainer);
        createJoinButton(gameManager, sceneController, nameInput, buttonsContainer);

        mainContainer.getChildren().addAll(labelWrapper, createSpacer(), nameLabelWrapper, nameInputWrapper, createSpacer(), buttonsContainer, createSpacer());
        mainContainer.setPadding(new Insets(20));
        mainContainer.setSpacing(20);

        HBox horizontalCenter = new HBox();
        horizontalCenter.setAlignment(Pos.CENTER);
        horizontalCenter.getChildren().add(mainContainer);
        getChildren().add(horizontalCenter);
    }

    private void createStartButton(Invoker invoker, GameManager gameManager, SceneController sceneController, TextField nameInput, HBox buttonsContainer) {
        Button startButton = new Button("Start a new game");
        startButton.setOnAction(e -> {
            if (nameInput.getText().isEmpty()) {
                showAlert();
                return;
            }
            Action createAction = new CreateGameAction();
            invoker.execute(createAction);
            waitOneSecond();
            gameManager.createDummyPlayer(nameInput.getText());

            Action joinAction = new JoinGameAction(gameManager, UUID.randomUUID(), nameInput.getText(), gameManager.getGame().getUuid());
            invoker.execute(joinAction);
            sceneController.activate("start_game");
        });
        buttonsContainer.getChildren().add(startButton);
        startButton.prefWidthProperty().bind(widthProperty().divide(3));
        startButton.prefHeightProperty().bind(heightProperty().divide(10));
    }

    private void createJoinButton(GameManager gameManager, SceneController sceneController, TextField nameInput, HBox buttonsContainer) {
        Button joinButton = new Button("Join a game");
        joinButton.setOnAction(e -> {
            if (nameInput.getText().isEmpty()) {
                showAlert();
                return;
            }
            gameManager.createDummyPlayer(nameInput.getText());
            sceneController.activate("join_game");
        });

        joinButton.prefWidthProperty().bind(widthProperty().divide(3));
        joinButton.prefHeightProperty().bind(heightProperty().divide(10));
        buttonsContainer.getChildren().add(joinButton);
    }

    private Node createSpacer() {
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    private void waitOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void showAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText("Insert a name");
        alert.show();
    }
}
