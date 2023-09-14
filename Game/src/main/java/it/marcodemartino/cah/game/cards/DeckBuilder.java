package it.marcodemartino.cah.game.cards;

import java.nio.file.Path;

public interface DeckBuilder {

    Deck build(Path path);
}
