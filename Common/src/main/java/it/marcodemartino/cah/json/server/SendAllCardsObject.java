package it.marcodemartino.cah.json.server;

import com.google.gson.annotations.SerializedName;
import it.marcodemartino.cah.game.Player;
import it.marcodemartino.cah.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SendAllCardsObject implements JSONObject {

    @SerializedName("method")
    private static final String METHOD = "send_all_cards";
    @SerializedName("played_cards")
    private final Map<String, List<String>> playedCards;

    public SendAllCardsObject(Map<Player, List<String>> playedCards) {
        this.playedCards = new HashMap<>();
        for (Entry<Player, List<String>> playerEntry : playedCards.entrySet()) {
            Player player = playerEntry.getKey();
            this.playedCards.put(player.getName(), playerEntry.getValue());
        }
    }

    @Override
    public String getMethod() {
        return METHOD;
    }

    public Map<String, List<String>> getPlayedCards() {
        return playedCards;
    }
}
