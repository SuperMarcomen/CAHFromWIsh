package it.marcodemartino.cah.game.cards;

import java.nio.file.Path;
import java.util.Map;

public interface DeckBuilder {

    Map<String, Deck> build(Path path);
}
