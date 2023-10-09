package it.marcodemartino.cah.client.actions;

import com.google.gson.Gson;
import it.marcodemartino.cah.client.game.Game;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.json.GsonInstance;
import it.marcodemartino.cah.json.JSONObject;
import it.marcodemartino.cah.json.client.PlayCardsObject;

import java.util.List;
import java.util.UUID;

public class PlayCardsAction implements Action {

    private final List<String> whiteCards;
    private final GameManager gameManager;
    private final Gson gson;

    public PlayCardsAction(List<String> whiteCards, GameManager gameManager) {
        this.whiteCards = List.copyOf(whiteCards);
        this.gameManager = gameManager;
        this.gson = GsonInstance.get();
    }

    @Override
    public String execute() {
        Game game = gameManager.getGame();
        UUID playerUUID = game.getPlayer().getUuid();
        JSONObject jsonObject = new PlayCardsObject(whiteCards, playerUUID, game.getUuid());
        game.playCards(whiteCards);
        return gson.toJson(jsonObject);
    }
}
