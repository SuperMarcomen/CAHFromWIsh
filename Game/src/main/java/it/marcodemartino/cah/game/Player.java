package it.marcodemartino.cah.game;

import it.marcodemartino.cah.game.cards.WhiteCard;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Player {

    private final String name;
    private final UUID uuid;
    private final List<WhiteCard> cards;

    public Player(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
        this.cards = new ArrayList<>();
    }

    public void addCard(WhiteCard whiteCard) {
        cards.add(whiteCard);
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<WhiteCard> getCards() {
        return cards;
    }
}
