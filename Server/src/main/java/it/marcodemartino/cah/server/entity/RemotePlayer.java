package it.marcodemartino.cah.server.entity;

import com.google.gson.Gson;
import it.marcodemartino.cah.game.Player;
import it.marcodemartino.cah.game.cards.BlackCard;
import it.marcodemartino.cah.json.JSONObject;
import it.marcodemartino.cah.json.client.CheckDeckObject;
import it.marcodemartino.cah.json.server.AllDecksInfoObject;
import it.marcodemartino.cah.json.server.DeckInfoObject;
import it.marcodemartino.cah.json.server.NotifyPlayerJoinObject;
import it.marcodemartino.cah.json.server.NotifyPlayerPlayedObject;
import it.marcodemartino.cah.json.server.SendAllCardsObject;
import it.marcodemartino.cah.json.server.SendCardsObject;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RemotePlayer implements Player {

    private final String name;
    private final UUID uuid;
    private final PrintWriter out;
    private final List<String> cards;
    private final List<String> playedCards;
    private final Gson gson;

    public RemotePlayer(String name, UUID uuid, PrintWriter out) {
        this.name = name;
        this.uuid = uuid;
        this.out = out;
        cards = new ArrayList<>();
        playedCards = new ArrayList<>();
        gson = new Gson();
    }

    @Override
    public void setPlayedCards(List<String> playedCards) {
        this.playedCards.clear();
        this.playedCards.addAll(playedCards);
        cards.removeAll(playedCards);
    }

    @Override
    public void sendCardsToAllPlayers(Map<Player, List<String>> cardsMap) {
        JSONObject jsonObject = new SendAllCardsObject(cardsMap);
        send(jsonObject);
    }

    @Override
    public void sendCards(List<String> whiteCards, BlackCard blackCard) {
        JSONObject jsonObject = new SendCardsObject(whiteCards, blackCard);
        send(jsonObject);
    }

    @Override
    public void notifyPlayerJoin(Player player) {
        JSONObject jsonObject = new NotifyPlayerJoinObject(player.getUuid(), player.getName());
        send(jsonObject);
    }

    @Override
    public void notifyPlayerPlayed(Player player) {
        JSONObject jsonObject = new NotifyPlayerPlayedObject(player.getUuid(), player.getName());
        send(jsonObject);
    }

    @Override
    public void sendDecksInfos(List<DeckInfoObject> deckInfos) {
        JSONObject jsonObject = new AllDecksInfoObject(deckInfos);
        send(jsonObject);
    }

    @Override
    public void notifyDeckSelected(String deckName, UUID gameUUID, boolean selected) {
        JSONObject jsonObject = new CheckDeckObject(deckName, uuid, gameUUID, selected);
        send(jsonObject);
    }

    private void send(JSONObject jsonObject) {
        out.println(gson.toJson(jsonObject));
        out.flush();
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
    public List<String> getCards() {
        return cards;
    }

    @Override
    public List<String> getPlayedCards() {
        return playedCards;
    }
}
