package it.marcodemartino.cah.client.actions;

import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.game.cards.WhiteCard;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class PlayCardsAction implements Action {

    private final List<WhiteCard> whiteCards;
    private final GameManager gameManager;

    public PlayCardsAction(List<WhiteCard> whiteCards, GameManager gameManager) {
        this.whiteCards = whiteCards;
        this.gameManager = gameManager;
    }

    @Override
    public String execute() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("method", "play_cards");
        jsonObject.put("game_uuid", gameManager.getGame().getUuid());
        jsonObject.put("player_uuid", gameManager.getGame().getPlayer().getUuid());

        JSONArray cardsArray = new JSONArray();
        for (WhiteCard whiteCard : whiteCards) {
            cardsArray.put(whiteCard.getText());
        }

        jsonObject.put("cards_played", cardsArray);
        gameManager.getGame().playCards(List.copyOf(whiteCards));
        return jsonObject.toString();
    }
}
