package it.marcodemartino.cah.server.entity;

import it.marcodemartino.cah.game.Player;
import it.marcodemartino.cah.game.cards.BlackCard;
import it.marcodemartino.cah.game.cards.Deck;
import it.marcodemartino.cah.game.cards.WhiteCard;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class RemotePlayer implements Player {

    private final String name;
    private final UUID uuid;
    private final PrintWriter out;
    private final List<WhiteCard> cards;
    private final List<WhiteCard> playedCards;

    public RemotePlayer(String name, UUID uuid, PrintWriter out) {
        this.name = name;
        this.uuid = uuid;
        this.out = out;
        cards = new ArrayList<>();
        playedCards = new ArrayList<>();
    }

    @Override
    public void setPlayedCards(List<WhiteCard> playedCards) {
        this.playedCards.clear();
        this.playedCards.addAll(playedCards);
        cards.removeAll(playedCards);
    }

    @Override
    public void sendCardsToAllPlayers(Map<Player, List<WhiteCard>> cardsMap) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("method", "send_all_cards");
        JSONArray players = new JSONArray();
        for (Entry<Player, List<WhiteCard>> playerListEntry : cardsMap.entrySet()) {
            Player player = playerListEntry.getKey();
            List<WhiteCard> playedWhiteCards = playerListEntry.getValue();
            JSONObject playerBlock = new JSONObject();
            playerBlock.put("player_name", player.getName());
            JSONArray cardsObj = new JSONArray();
            for (WhiteCard playedWhiteCard : playedWhiteCards) {
                cardsObj.put(playedWhiteCard.getText());
            }

            playerBlock.put("played_cards", cardsObj);
            players.put(playerBlock);
        }
        jsonObject.put("cards", players);
        out.println(jsonObject);
        out.flush();

    }

    @Override
    public void sendCards(Deck deck) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("method", "send_cards");
        JSONArray whiteCards = new JSONArray();
        for (WhiteCard whiteCard : deck.getWhiteCards()) {
            whiteCards.put(whiteCard.getText());
        }

        JSONArray blackCards = new JSONArray();
        for (BlackCard blackCard : deck.getBlackCards()) {
            JSONObject card = new JSONObject();
            card.put("text", blackCard.getText());
            card.put("parameters", blackCard.getNumberOfParameters());
            blackCards.put(card);
        }

        if (!whiteCards.isEmpty()) {
            jsonObject.put("whitecards", whiteCards);
        }

        if (!blackCards.isEmpty()) {
            jsonObject.put("blackcards", blackCards);
        }

        out.println(jsonObject);
        out.flush();
    }

    @Override
    public void addCard(WhiteCard whiteCard) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public List<WhiteCard> getCards() {
        return cards;
    }

    @Override
    public List<WhiteCard> getPlayedCards() {
        return playedCards;
    }
}
