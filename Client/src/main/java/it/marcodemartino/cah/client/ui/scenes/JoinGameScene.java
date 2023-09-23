package it.marcodemartino.cah.client.ui.scenes;

import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.actions.Action;
import it.marcodemartino.cah.client.actions.JoinGameAction;
import it.marcodemartino.cah.client.game.GameManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.UUID;

public class JoinGameScene extends InitPane {

    private final Invoker invoker;
    private final GameManager gameManager;

    public JoinGameScene(Invoker invoker, GameManager gameManager) {
        this.invoker = invoker;
        this.gameManager = gameManager;
    }

    @Override
    public void init() {
        VBox mainContainer = new VBox();

        Label titleLabel = new Label("Cards Against Humanity");
        titleLabel.setId("titleLabel");

        Label enterIdLabel = new Label("Enter the id of the game");
        enterIdLabel.setId("enterIdLabel");

        TextField idInputField = new TextField("DH76-PO86-DJE4-48DA");
        idInputField.setId("idInputField");

        Button joinButton = new Button("Join the game");
        joinButton.setOnAction(e -> {
            if (idInputField.getText().isEmpty()) {
                showAlert();
                return;
            }
            Action joinGame = new JoinGameAction(gameManager, gameManager.getPlayer().getUuid(), gameManager.getPlayer().getName(), UUID.fromString(idInputField.getText()));
            invoker.execute(joinGame);
        });

        mainContainer.getChildren().addAll(titleLabel, enterIdLabel, idInputField, joinButton);
        getChildren().add(mainContainer);
    }

    private void showAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText("Insert an id");
        alert.show();
    }
}
