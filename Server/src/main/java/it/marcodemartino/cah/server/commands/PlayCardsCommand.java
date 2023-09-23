package it.marcodemartino.cah.server.commands;

import it.marcodemartino.cah.game.Game;
import it.marcodemartino.cah.game.cards.WhiteCard;
import it.marcodemartino.cah.server.GameManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayCardsCommand extends Command {

    private final GameManager gameManager;

    public PlayCardsCommand(BufferedReader in, PrintWriter out, GameManager gameManager) {
        super(in, out);
        this.gameManager = gameManager;
    }

    @Override
    public void execute(String input) {
        JSONObject object = new JSONObject(input);
        Game game = gameManager.getGame(UUID.fromString(object.getString("game_uuid")));
        List<WhiteCard> whiteCards = new ArrayList<>();
        JSONArray cardsArray = object.getJSONArray("cards_played");
        for (Object obj : cardsArray) {
            whiteCards.add(new WhiteCard((String) obj));
        }

        game.playCards(UUID.fromString(object.getString("player_uuid")), whiteCards);

        if (!game.haveAllPlayersPlayed()) return;
        game.sendPlayedCardsToAllPlayers();
    }
}
