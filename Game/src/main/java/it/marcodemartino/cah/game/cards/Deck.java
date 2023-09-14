package it.marcodemartino.cah.game.cards;

import java.util.List;

public class Deck {

    private final List<WhiteCard> whiteCards;
    private final List<BlackCard> blackCards;

    public Deck(List<WhiteCard> whiteCards, List<BlackCard> blackCards) {
        this.whiteCards = whiteCards;
        this.blackCards = blackCards;
    }

    public List<WhiteCard> getWhiteCards() {
        return whiteCards;
    }

    public List<BlackCard> getBlackCards() {
        return blackCards;
    }
}
