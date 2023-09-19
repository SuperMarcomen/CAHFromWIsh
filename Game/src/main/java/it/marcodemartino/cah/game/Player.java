package it.marcodemartino.cah.game;

import it.marcodemartino.cah.game.cards.Deck;
import it.marcodemartino.cah.game.cards.WhiteCard;

import java.util.List;
import java.util.UUID;

public interface Player {

    void setPlayedCards(List<WhiteCard> playedCards);
    void sendCards(Deck deck);
    void addCard(WhiteCard whiteCard);
    String getName();
    UUID getUuid();
    List<WhiteCard> getCards();

}
