package it.marcodemartino.cah.client.ui.scenes;

import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.actions.Action;
import it.marcodemartino.cah.client.actions.CheckDeckAction;
import it.marcodemartino.cah.client.actions.StartGameAction;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.elements.BigWhiteButton;
import it.marcodemartino.cah.json.server.DeckInfoObject;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChooseDecksScene extends InitPane {

    private final List<DeckInfoObject> deckInfos;
    private final List<String> decksNames;
    private final Map<String, CheckBox> checkBoxes;
    private final SceneController sceneController;
    private final Invoker invoker;
    private final GameManager gameManager;

    public ChooseDecksScene(SceneController sceneController, Stage primaryStage, Invoker invoker, GameManager gameManager) {
        super(primaryStage);
        this.invoker = invoker;
        this.gameManager = gameManager;
        this.deckInfos = new ArrayList<>();
        this.decksNames = new ArrayList<>();
        this.checkBoxes = new HashMap<>();
        sceneController.setDeckInfoObjectList(deckInfos);
        this.sceneController = sceneController;
    }

    @Override
    public void init() {
        VBox mainContainer = new VBox();
        mainContainer.setPadding(new Insets(20));
        mainContainer.setSpacing(20);
        mainContainer.prefWidthProperty().bind(widthProperty());
        mainContainer.prefHeightProperty().bind(heightProperty());
        setId("background");

        Label label = new Label("Select the decks you want");

        CheckBox selectAll = new CheckBox("Select all");
        selectAll.selectedProperty().addListener((observable, oldValue, newValue) -> {
            for (CheckBox checkBox : checkBoxes.values()) {
                checkBox.setSelected(newValue);
            }
        });

        TextField searchField = new TextField();
        searchField.setPromptText("Search...");
        searchField.textProperty().addListener((observable, oldValue, newValue) -> filterCheckboxes(newValue));

        VBox checkBoxContainer = new VBox();
        checkBoxContainer.setId("background");

        Map<String, BooleanProperty> decksCheckboxesProperty = new HashMap<>();
        for (DeckInfoObject deckInfo : deckInfos) {
            CheckBox checkBox = new CheckBox(deckInfo.getName());
            checkBoxes.put(deckInfo.getName(), checkBox);
            decksCheckboxesProperty.put(deckInfo.getName(), checkBox.selectedProperty());

            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                handleCheckBoxClick(deckInfo.getName(), newValue);
            });

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            int cardTotal = deckInfo.getWhiteCardsAmount() + deckInfo.getBlackCardsAmount();
            Label cardsLabel = new Label(String.valueOf(cardTotal));
            HBox container = new HBox(checkBox, spacer, cardsLabel);
            container.setPadding(new Insets(0, 20, 0, 0));


            checkBoxContainer.getChildren().add(container);
        }
        sceneController.setDecksCheckboxesProperty(decksCheckboxesProperty);

        ScrollPane scrollPane = new ScrollPane(checkBoxContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Button startButton = new BigWhiteButton("Start the game", widthProperty(), heightProperty());
        startButton.setOnAction(e -> handleStartClick());
        HBox startButtonWrapper = new HBox(startButton);
        startButtonWrapper.setAlignment(Pos.CENTER);

        mainContainer.getChildren().addAll(label, searchField, selectAll, scrollPane, startButtonWrapper);
        getChildren().add(mainContainer);
    }

    private void handleStartClick() {
        Action startGameAction = new StartGameAction(gameManager.getGame().getUuid(), decksNames);
        invoker.execute(startGameAction);
    }

    private void handleCheckBoxClick(String deckName, Boolean selected) {
        if (selected) {
            decksNames.add(deckName);
        } else {
            decksNames.remove(deckName);
        }

        Action checkDeckAction = new CheckDeckAction(deckName, gameManager.getPlayer().getUuid(), gameManager.getGame().getUuid(), selected);
        invoker.execute(checkDeckAction);
    }

    private void filterCheckboxes(String searchText) {
        for (CheckBox checkBox : checkBoxes.values()) {
            boolean isVisible = checkBox.getText().toLowerCase().contains(searchText.toLowerCase());
            checkBox.setVisible(isVisible);
            checkBox.setManaged(isVisible);
        }
    }
}
