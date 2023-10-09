package it.marcodemartino.cah.client.commands;

import it.marcodemartino.cah.client.ui.scenes.SceneController;
import it.marcodemartino.cah.json.server.AllDecksInfoObject;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReceiveDecksCommand extends Command<AllDecksInfoObject> {

    private final SceneController sceneController;
    private final Logger logger = LogManager.getLogger(ReceiveDecksCommand.class);

    public ReceiveDecksCommand(SceneController sceneController) {
        super(AllDecksInfoObject.class);
        this.sceneController = sceneController;
    }

    @Override
    protected void execute(AllDecksInfoObject allDecksInfoObject) {
        logger.info("Received {} decks", allDecksInfoObject.getDeckInfos().size());
        sceneController.addDeckInfoObjectList(allDecksInfoObject.getDeckInfos());
        Platform.runLater(() -> sceneController.activate("choose_decks"));
    }
}
