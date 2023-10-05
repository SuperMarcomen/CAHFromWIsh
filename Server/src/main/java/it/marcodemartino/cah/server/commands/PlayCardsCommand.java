package it.marcodemartino.cah.server.commands;

import it.marcodemartino.cah.game.Game;
import it.marcodemartino.cah.json.client.PlayCardsObject;
import it.marcodemartino.cah.server.GameManager;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.UUID;

public class PlayCardsCommand extends Command<PlayCardsObject> {

    private final GameManager gameManager;

    public PlayCardsCommand(BufferedReader in, PrintWriter out, GameManager gameManager) {
        super(in, out, PlayCardsObject.class);
        this.gameManager = gameManager;
    }

    @Override
    public void execute(PlayCardsObject object) {
        Game game = gameManager.getGame(object.getGameUUID());

        UUID playerUUID = object.getPlayerUUID();
        game.playCards(object.getPlayerUUID(), object.getPlayedCards());
        game.broadcastCardPlay(playerUUID);

        if (!game.haveAllPlayersPlayed()) return;
        game.sendNewRoundCardsToAllPlayers();
        game.sendPlayedCardsToAllPlayers();
    }
}
