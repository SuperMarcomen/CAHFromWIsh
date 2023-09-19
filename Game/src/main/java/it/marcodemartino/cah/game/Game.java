package it.marcodemartino.cah.game;

import it.marcodemartino.cah.game.cards.BlackCard;
import it.marcodemartino.cah.game.cards.Deck;
import it.marcodemartino.cah.game.cards.WhiteCard;
import it.marcodemartino.cah.game.collections.RandomArrayList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Game {

    private final Map<UUID, Player> players;
    private final Set<UUID> hasPlayed;
    private static final int START_CARDS = 10;
    private final Deck deck;

    public Game(Deck deck) {
        this.players = new HashMap<>();
        this.hasPlayed = new HashSet<>();
        this.deck = deck;
    }

    public void playCards(UUID uuid, List<WhiteCard> whiteCards) {
        Player player = players.get(uuid);
        player.setPlayedCards(whiteCards);
        hasPlayed.add(uuid);
    }

    public void sendStartCardsToAllPlayer() {
        RandomArrayList<BlackCard> blackCard = new RandomArrayList<>();
        blackCard.add(deck.getRandomBlackCard());
        for (Player player : players) {
            player.sendCards(new Deck(pickXCards(START_CARDS), blackCard));
        }
    }

    private RandomArrayList<WhiteCard> pickXCards(int x) {
        RandomArrayList<WhiteCard> randomElements = new RandomArrayList<>();

        for (int i = 0; i < x; i++) {
            randomElements.add(deck.getRandomWhiteCard());
        }
        return randomElements;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}
