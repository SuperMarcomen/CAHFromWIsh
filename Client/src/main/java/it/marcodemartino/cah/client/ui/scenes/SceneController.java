package it.marcodemartino.cah.client.ui.scenes;

import javafx.beans.property.StringProperty;
import javafx.scene.Scene;

import java.util.HashMap;

public class SceneController {

    private final HashMap<String, InitPane> screenMap;
    private StringProperty playersJoinedText;
    private InitPane currentlyActive;
    private Scene main;

    public SceneController() {
        screenMap = new HashMap<>();
    }

    public void addScreen(String name, InitPane pane) {
        pane.prefWidthProperty().bind(main.widthProperty());
        pane.prefHeightProperty().bind(main.heightProperty());
        screenMap.put(name, pane);
    }

    public void removeScreen(String name){
        screenMap.remove(name);
    }

    public void activate(String name){
        currentlyActive = screenMap.get(name);
        main.setRoot(currentlyActive);
        currentlyActive.init();
    }

    public void setMain(Scene main) {
        this.main = main;
    }

    public StringProperty playersJoinedTextProperty() {
        return playersJoinedText;
    }

    public void setPlayersJoinedText(StringProperty playersJoinedText) {
        this.playersJoinedText = playersJoinedText;
    }

    public InitPane getCurrentlyActive() {
        return currentlyActive;
    }
}
