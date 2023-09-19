package it.marcodemartino.cah.client.game;

import it.marcodemartino.cah.game.cards.BlackCard;
import it.marcodemartino.cah.game.cards.WhiteCard;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {

    private final UUID uuid;
    private BlackCard blackCard;
    private final List<WhiteCard> whiteCards;

    public Game(UUID uuid) {
        this.uuid = uuid;
        whiteCards = new ArrayList<>();
    }

    public BlackCard getBlackCard() {
        return blackCard;
    }

    public List<WhiteCard> getWhiteCards() {
        return whiteCards;
    }

    public void addWhiteCards(List<WhiteCard> whiteCards) {
        this.whiteCards.addAll(whiteCards);
    }

    public void setBlackCard(BlackCard blackCard) {
        this.blackCard = blackCard;
    }

    public UUID getUuid() {
        return uuid;
    }
}
