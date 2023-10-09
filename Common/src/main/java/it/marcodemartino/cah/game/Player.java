package it.marcodemartino.cah.game;

import it.marcodemartino.cah.game.cards.BlackCard;
import it.marcodemartino.cah.json.server.DeckInfoObject;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface Player {

    void setPlayedCards(List<String> playedCards);
    void sendCardsToAllPlayers(Map<Player, List<String>> cardsMap);
    void sendCards(List<String> whiteCards, BlackCard blackCard);
    void notifyPlayerJoin(Player player);
    void notifyPlayerPlayed(Player player);
    void sendDecksInfos(List<DeckInfoObject> deckInfos);
    void notifyDeckSelected(String deckName, UUID gameUUID, boolean selected);
    String getName();
    UUID getUuid();
    List<String> getCards();
    List<String> getPlayedCards();

}
