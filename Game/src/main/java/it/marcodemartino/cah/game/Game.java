package it.marcodemartino.cah.game;

import it.marcodemartino.cah.game.cards.Deck;
import it.marcodemartino.cah.game.cards.WhiteCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private final List<Player> players;
    private final List<Boolean> hasPlayed;
    private final Deck deck;

    public Game(Deck deck) {
        this.players = new ArrayList<>();
        this.hasPlayed = new ArrayList<>();
        this.deck = deck;
    }

    public List<WhiteCard> pickXCards(int x) {
        Random rand = new Random();
        List<WhiteCard> randomElements = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            int randomIndex = rand.nextInt(deck.getWhiteCards().size());
            randomElements.add(deck.getWhiteCards().remove(randomIndex));
        }
        return randomElements;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}
