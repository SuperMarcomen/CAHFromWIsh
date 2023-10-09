package it.marcodemartino.cah.game;

import it.marcodemartino.cah.game.cards.BlackCard;
import it.marcodemartino.cah.game.cards.Deck;
import it.marcodemartino.cah.game.collections.RandomArrayList;
import it.marcodemartino.cah.json.server.DeckInfoObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Game {

    private final UUID uuid;
    private final Map<UUID, Player> players;
    private final Set<UUID> hasPlayed;
    private static final int START_CARDS = 10;
    private final Deck deck;
    private int cardsRequired;
    private boolean started;

    public Game(UUID uuid) {
        this.uuid = uuid;
        this.players = new HashMap<>();
        this.hasPlayed = new HashSet<>();
        this.deck = new Deck();
    }

    public void sendDeckInfos(List<DeckInfoObject> deckInfos) {
        for (Player player : players.values()) {
            player.sendDecksInfos(deckInfos);
        }
    }

    public void sendNewRoundCardsToAllPlayers() {
        hasPlayed.clear();
        BlackCard blackCard = deck.getRandomBlackCard();
        RandomArrayList<String> whiteCards = pickXCards(cardsRequired);

        cardsRequired = blackCard.getNumberOfParameters();
        for (Player player : players.values()) {
            player.sendCards(whiteCards, blackCard);
        }
    }

    public void sendPlayedCardsToAllPlayers() {
        Map<Player, List<String>> cardsMap = generateCardsMap();
        for (Player player : players.values()) {
            player.sendCardsToAllPlayers(cardsMap);
        }
    }

    private Map<Player, List<String>> generateCardsMap() {
        Map<Player, List<String>> cardsMap = new HashMap<>();
        for (Player player : players.values()) {
            cardsMap.put(player, player.getPlayedCards());
        }
        return cardsMap;
    }

    public boolean haveAllPlayersPlayed() {
        return hasPlayed.size() == players.size();
    }

    public void playCards(UUID uuid, List<String> whiteCards) {
        Player player = players.get(uuid);
        player.setPlayedCards(whiteCards);
        hasPlayed.add(uuid);
    }

    public void broadcastCardPlay(UUID uuid) {
        Player player = players.get(uuid);
        for (Player toNotify : players.values()) {
            toNotify.notifyPlayerPlayed(player);
        }
    }

    public void broadcastDeckSelection(UUID senderUUID, String deckName, boolean selected) {
        for (Player player : players.values()) {
            if (player.getUuid().equals(senderUUID)) continue;
            player.notifyDeckSelected(deckName, uuid, selected);
        }
    }

    public void sendStartCardsToAllPlayer() {
        BlackCard randomBlackCard = deck.getRandomBlackCard();
        cardsRequired = randomBlackCard.getNumberOfParameters();
        for (Player player : players.values()) {
            player.sendCards(pickXCards(START_CARDS), randomBlackCard);
        }
    }

    private RandomArrayList<String> pickXCards(int x) {
        RandomArrayList<String> randomElements = new RandomArrayList<>();

        for (int i = 0; i < x; i++) {
            randomElements.add(deck.getRandomWhiteCard());
        }
        return randomElements;
    }

    public void notifyPlayerAboutEveryone(Player playerWhoJoined) {
        for (Player playerInGame : players.values()) {
            playerWhoJoined.notifyPlayerJoin(playerInGame);
        }
    }

    public void notifyPlayerJoin(Player playerWhoJoined) {
        for (Player playerToNotify : players.values()) {
            if (playerToNotify.getUuid().equals(playerWhoJoined.getUuid())) continue;
            playerToNotify.notifyPlayerJoin(playerWhoJoined);
        }
    }

    public void addPlayer(Player player) {
        players.put(player.getUuid(), player);
    }

    public void removePlayer(UUID playerUUID) {
        players.remove(playerUUID);
        hasPlayed.remove(playerUUID);
    }

    public void setDeck(Deck deck) {
        this.deck.addAll(deck);
    }

    public boolean isEmpty() {
        return players.isEmpty();
    }

    public boolean isStarted() {
        return started;
    }

    public void start() {
        started = true;
    }
}
