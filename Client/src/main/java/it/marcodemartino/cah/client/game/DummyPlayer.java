package it.marcodemartino.cah.client.game;

import it.marcodemartino.cah.game.Player;
import it.marcodemartino.cah.game.cards.BlackCard;
import it.marcodemartino.cah.json.server.DeckInfoObject;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DummyPlayer implements Player {

    private final UUID uuid;

    private final String name;

    public DummyPlayer(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    @Override
    public void setPlayedCards(List<String> playedCards) {

    }

    @Override
    public void sendCardsToAllPlayers(Map<Player, List<String>> cardsMap) {

    }

    @Override
    public void sendCards(List<String> whiteCards, BlackCard blackCard) {

    }

    @Override
    public void notifyPlayerJoin(Player player) {

    }

    @Override
    public void notifyPlayerPlayed(Player player) {

    }

    @Override
    public void sendDecksInfos(List<DeckInfoObject> deckInfos) {

    }

    @Override
    public void notifyDeckSelected(String deckName, UUID gameUUID, boolean selected) {

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
        return null;
    }

    @Override
    public List<String> getPlayedCards() {
        return null;
    }
}
