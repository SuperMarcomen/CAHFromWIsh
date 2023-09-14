package it.marcodemartino.cah.server.entity;

import it.marcodemartino.cah.game.Player;
import it.marcodemartino.cah.game.cards.BlackCard;
import it.marcodemartino.cah.game.cards.Deck;
import it.marcodemartino.cah.game.cards.WhiteCard;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RemotePlayer extends Player {

    private final PrintWriter out;

    public RemotePlayer(String name, UUID uuid, PrintWriter out) {
        super(name, uuid);
        this.out = out;
    }

    public void sendCards(List<WhiteCard> whiteCards, BlackCard blackCard) {

    }

    public void sendResults(Map<Player, Deck> results) {

    }
}
