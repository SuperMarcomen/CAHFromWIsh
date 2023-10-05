package it.marcodemartino.cah.game.cards;

import it.marcodemartino.cah.game.collections.RandomArrayList;

import java.util.List;

public class Deck {

    private final RandomArrayList<String> whiteCards;
    private final RandomArrayList<BlackCard> blackCards;

    public Deck(RandomArrayList<String> whiteCards, RandomArrayList<BlackCard> blackCards) {
        this.whiteCards = whiteCards;
        this.blackCards = blackCards;
    }

    public String getRandomWhiteCard() {
        return whiteCards.removeRandom();
    }

    public BlackCard getRandomBlackCard() {
        return blackCards.removeRandom();
    }

    public List<String> getWhiteCards() {
        return whiteCards;
    }

    public List<BlackCard> getBlackCards() {
        return blackCards;
    }
}
