package it.marcodemartino.cah.game.cards;

import it.marcodemartino.cah.game.collections.RandomArrayList;

import java.util.List;

public class Deck {

    private final RandomArrayList<WhiteCard> whiteCards;
    private final RandomArrayList<BlackCard> blackCards;

    public Deck(RandomArrayList<WhiteCard> whiteCards, RandomArrayList<BlackCard> blackCards) {
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