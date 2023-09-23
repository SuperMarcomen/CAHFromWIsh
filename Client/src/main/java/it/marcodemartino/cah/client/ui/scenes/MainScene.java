package it.marcodemartino.cah.client.ui.scenes;

import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.actions.Action;
import it.marcodemartino.cah.client.actions.CreateGameAction;
import it.marcodemartino.cah.client.actions.JoinGameAction;
import it.marcodemartino.cah.client.game.GameManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.UUID;

public class MainScene extends StackPane {


    public MainScene(Invoker invoker, GameManager gameManager, SceneController sceneController) {
        VBox mainContainer = new VBox();
        Label titleLabel = new Label("Cards Against Humanity");
        titleLabel.setId("titleLabel");

        Label nameLabel = new Label("Enter your name");
        nameLabel.setId("nameLabel");

        TextField nameInput = new TextField("dd");
        nameInput.setId("nameInput");

        HBox buttonsContainer = new HBox();
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

        Button joinButton = new Button("Join a game");
        joinButton.setOnAction(e -> {
            if (nameInput.getText().isEmpty()) {
                showAlert();
                return;
            }
            gameManager.createDummyPlayer(nameInput.getText());
            sceneController.activate("join_game");
        });
        buttonsContainer.getChildren().addAll(startButton, joinButton);

        mainContainer.getChildren().addAll(titleLabel, nameLabel, nameInput, buttonsContainer);
        getChildren().add(mainContainer);
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
