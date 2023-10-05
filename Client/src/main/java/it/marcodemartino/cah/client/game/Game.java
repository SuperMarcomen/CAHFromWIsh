package it.marcodemartino.cah.client.game;

import it.marcodemartino.cah.client.collections.HashSetChangeListener;
import it.marcodemartino.cah.client.collections.ObservableHashSet;
import it.marcodemartino.cah.game.Player;
import it.marcodemartino.cah.game.cards.BlackCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Game {

    private final UUID uuid;
    private BlackCard newBlackCard;
    private BlackCard oldBlackCard;
    private Player player;
    private final List<Player> allPlayers;
    private final ObservableHashSet<Player> playersWhoPlayed;
    private final List<String> whiteCards;
    private final List<String> selectedCards;
    private final List<String> playedCards;
    private final Map<String, List<String>> allPlayedCards;

    public Game(UUID uuid) {
        this.uuid = uuid;
        allPlayers = new ArrayList<>();
        playersWhoPlayed = new ObservableHashSet<>();
        whiteCards = new ArrayList<>();
        playedCards = new ArrayList<>();
        selectedCards = new ArrayList<>();
        allPlayedCards = new HashMap<>();
    }

    public boolean areEnoughCardsSelected() {
        return selectedCards.size() == newBlackCard.getNumberOfParameters();
    }

    public boolean isCardSelected(String whiteCard) {
        return selectedCards.contains(whiteCard);
    }

    public void selectCard(String whiteCard) {
        selectedCards.add(whiteCard);
    }

    public void unselectCard(String whiteCard) {
        selectedCards.remove(whiteCard);
    }

    public void setAllPlayedCards(Map<String, List<String>> allPlayedCards) {
        this.allPlayedCards.clear();
        this.allPlayedCards.putAll(allPlayedCards);
    }

    public void clearPlayedCards() {
        playedCards.clear();
        playersWhoPlayed.clear();
    }

    public void playCards(List<String> cardsCurrentlyPlayed) {
        playedCards.addAll(cardsCurrentlyPlayed);
        whiteCards.removeAll(cardsCurrentlyPlayed);
        selectedCards.removeAll(cardsCurrentlyPlayed);
    }

    public boolean hasPlayedCards() {
        return !playedCards.isEmpty();
    }

    public void setHashSetListener(HashSetChangeListener<Player> listener) {
        playersWhoPlayed.setListener(listener);
    }

    public boolean hasPlayerPlayed(Player player) {
        return playersWhoPlayed.contains(player);
    }

    public BlackCard getNewBlackCard() {
        return newBlackCard;
    }

    public List<String> getWhiteCards() {
        return whiteCards;
    }

    public void addWhiteCards(List<String> whiteCards) {
        this.whiteCards.addAll(whiteCards);
    }

    public void setNewBlackCard(BlackCard newBlackCard) {
        this.oldBlackCard = this.newBlackCard;
        if (this.newBlackCard == null) {
            this.oldBlackCard = newBlackCard;
        }
        this.newBlackCard = newBlackCard;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return player;
    }

    public Map<String, List<String>> getAllPlayedCards() {
        return allPlayedCards;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<String> getSelectedCards() {
        return selectedCards;
    }

    public BlackCard getOldBlackCard() {
        return oldBlackCard;
    }

    public List<Player> getAllPlayers() {
        return allPlayers;
    }

    public void addPlayer(Player player) {
        allPlayers.add(player);
    }

    public void addPlayersWhoPlayed(UUID playerUUID) {
        playersWhoPlayed.add(getFromUUID(playerUUID));
    }

    private Player getFromUUID(UUID playerUUID) {
        for (Player player : allPlayers) {
            if (player.getUuid().equals(playerUUID)) return player;
        }
        return null;
    }
}
