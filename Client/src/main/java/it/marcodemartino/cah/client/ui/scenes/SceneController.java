package it.marcodemartino.cah.client.ui.scenes;

import javafx.scene.Scene;

import java.util.HashMap;

public class SceneController {

    private final HashMap<String, InitPane> screenMap;
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
        main.setRoot( screenMap.get(name) );
        screenMap.get(name).init();
    }

    public void setMain(Scene main) {
        this.main = main;
    }
}
