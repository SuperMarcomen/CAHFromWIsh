package it.marcodemartino.cah.json.server;

import com.google.gson.annotations.SerializedName;
import it.marcodemartino.cah.game.cards.BlackCard;
import it.marcodemartino.cah.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SendCardsObject implements JSONObject {

    @SerializedName("method")
    private final String METHOD = "send_cards";
    @SerializedName("white_cards")
    private final List<String> whiteCards;
    private final BlackCard blackcard;

    public SendCardsObject(List<String> whiteCards, BlackCard blackcard) {
        this.whiteCards = whiteCards;
        this.blackcard = blackcard;
    }

    @Override
    public String getMethod() {
        return METHOD;
    }

    public List<String> getWhiteCards() {
        List<String> whiteCards = new ArrayList<>();
        this.whiteCards.forEach(text -> whiteCards.add(text));
        return whiteCards;
    }

    public BlackCard getBlackcard() {
        return blackcard;
    }
}
