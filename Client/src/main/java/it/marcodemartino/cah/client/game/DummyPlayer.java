package it.marcodemartino.cah.client.game;

import it.marcodemartino.cah.game.Player;
import it.marcodemartino.cah.game.cards.Deck;
import it.marcodemartino.cah.game.cards.WhiteCard;

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
    public String getName() {
        return name;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public void setPlayedCards(List<WhiteCard> playedCards) {

    }

    @Override
    public void sendCardsToAllPlayers(Map<Player, List<WhiteCard>> cardsMap) {

    }

    @Override
    public void sendCards(Deck deck) {

    }

    @Override
    public void addCard(WhiteCard whiteCard) {

    }

    @Override
    public void notifyPlayerJoin(Player player) {

    }

    @Override
    public List<WhiteCard> getCards() {
        return null;
    }

    @Override
    public List<WhiteCard> getPlayedCards() {
        return null;
    }
}
