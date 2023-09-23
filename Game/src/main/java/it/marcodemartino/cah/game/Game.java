package it.marcodemartino.cah.game;

import it.marcodemartino.cah.game.cards.BlackCard;
import it.marcodemartino.cah.game.cards.Deck;
import it.marcodemartino.cah.game.cards.WhiteCard;
import it.marcodemartino.cah.game.collections.RandomArrayList;

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

    public void sendPlayedCardsToAllPlayers() {
        Map<Player, List<WhiteCard>> cardsMap = generateCardsMap();
        for (Player player : players.values()) {
            player.sendCardsToAllPlayers(cardsMap);
        }
    }

    private Map<Player, List<WhiteCard>> generateCardsMap() {
        Map<Player, List<WhiteCard>> cardsMap = new HashMap<>();
        for (Player player : players.values()) {
            cardsMap.put(player, player.getPlayedCards());
        }
        return cardsMap;
    }

    public boolean haveAllPlayersPlayed() {
        return hasPlayed.size() == players.size();
    }

    public void playCards(UUID uuid, List<WhiteCard> whiteCards) {
        Player player = players.get(uuid);
        player.setPlayedCards(whiteCards);
        hasPlayed.add(uuid);
    }

    public void sendStartCardsToAllPlayer() {
        RandomArrayList<BlackCard> blackCard = new RandomArrayList<>();
        blackCard.add(deck.getRandomBlackCard());
        for (Player player : players.values()) {
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
        players.put(player.getUuid(), player);
    }
}
