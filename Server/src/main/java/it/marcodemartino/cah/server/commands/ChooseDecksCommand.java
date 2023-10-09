package it.marcodemartino.cah.server.commands;

import it.marcodemartino.cah.json.client.ChooseDecksObject;
import it.marcodemartino.cah.server.DeckManager;
import it.marcodemartino.cah.server.GameManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ChooseDecksCommand extends Command<ChooseDecksObject> {

    private final GameManager gameManager;
    private final DeckManager deckManager;
    private final Logger logger = LogManager.getLogger(ChooseDecksCommand.class);

    public ChooseDecksCommand(BufferedReader in, PrintWriter out, GameManager gameManager, DeckManager deckManager) {
        super(in, out, ChooseDecksObject.class);
        this.gameManager = gameManager;
        this.deckManager = deckManager;
    }

    @Override
    protected void execute(ChooseDecksObject chooseDecksObject) {
        gameManager.getGame(chooseDecksObject.getGameUUID()).sendDeckInfos(deckManager.createDeckInfos());
        logger.info("Sent all decks informations");
    }
}
