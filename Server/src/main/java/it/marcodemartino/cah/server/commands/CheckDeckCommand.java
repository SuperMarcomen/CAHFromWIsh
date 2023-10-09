package it.marcodemartino.cah.server.commands;

import it.marcodemartino.cah.game.Game;
import it.marcodemartino.cah.json.client.CheckDeckObject;
import it.marcodemartino.cah.server.GameManager;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class CheckDeckCommand extends Command<CheckDeckObject> {

    private final GameManager gameManager;

    public CheckDeckCommand(BufferedReader in, PrintWriter out, GameManager gameManager) {
        super(in, out, CheckDeckObject.class);
        this.gameManager = gameManager;
    }

    @Override
    protected void execute(CheckDeckObject checkDeckObject) {
        Game game = gameManager.getGame(checkDeckObject.getGameUUID());
        game.broadcastDeckSelection(checkDeckObject.getPlayerUUID(), checkDeckObject.getDeckName(), checkDeckObject.isSelected());
    }
}
