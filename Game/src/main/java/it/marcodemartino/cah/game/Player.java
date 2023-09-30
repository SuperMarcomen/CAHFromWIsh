package it.marcodemartino.cah.game;

import it.marcodemartino.cah.game.cards.Deck;
import it.marcodemartino.cah.game.cards.WhiteCard;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface Player {

    void setPlayedCards(List<WhiteCard> playedCards);
    void sendCardsToAllPlayers(Map<Player, List<WhiteCard>> cardsMap);
    void sendCards(Deck deck);
    void addCard(WhiteCard whiteCard);
    void notifyPlayerJoin(Player player);
    String getName();
    UUID getUuid();
    List<WhiteCard> getCards();
    List<WhiteCard> getPlayedCards();

}
