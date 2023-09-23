package it.marcodemartino.cah.client.game;

import it.marcodemartino.cah.game.Player;
import it.marcodemartino.cah.game.cards.BlackCard;
import it.marcodemartino.cah.game.cards.WhiteCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Game {

    private final UUID uuid;
    private BlackCard blackCard;
    private Player player;
    private final List<WhiteCard> whiteCards;
    private final List<WhiteCard> selectedCards;
    private final List<WhiteCard> playedCards;
    private final Map<String, List<WhiteCard>> allPlayedCards;

    public Game(UUID uuid) {
        this.uuid = uuid;
        whiteCards = new ArrayList<>();
        playedCards = new ArrayList<>();
        selectedCards = new ArrayList<>();
        allPlayedCards = new HashMap<>();
    }

    public boolean areEnoughCardsSelected() {
        return selectedCards.size() == blackCard.getNumberOfParameters();
    }

    public boolean isCardSelected(WhiteCard whiteCard) {
        return selectedCards.contains(whiteCard);
    }

    public void selectCard(WhiteCard whiteCard) {
        selectedCards.add(whiteCard);
    }

    public void unselectCard(WhiteCard whiteCard) {
        selectedCards.remove(whiteCard);
    }

    public void setAllPlayedCards(Map<String, List<WhiteCard>> allPlayedCards) {
        this.allPlayedCards.clear();
        this.allPlayedCards.putAll(allPlayedCards);
    }

    public void playCards(List<WhiteCard> cardsCurrentlyPlayed) {
        playedCards.addAll(cardsCurrentlyPlayed);
        whiteCards.removeAll(cardsCurrentlyPlayed);
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

    public Player getPlayer() {
        return player;
    }

    public Map<String, List<WhiteCard>> getAllPlayedCards() {
        return allPlayedCards;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<WhiteCard> getSelectedCards() {
        return selectedCards;
    }
}
