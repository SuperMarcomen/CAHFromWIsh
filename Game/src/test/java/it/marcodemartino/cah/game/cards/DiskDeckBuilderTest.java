package it.marcodemartino.cah.game.cards;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;

class DiskDeckBuilderTest {

    private static final Logger logger = LogManager.getLogger();

    @Test
    @DisplayName("Read and parse cards from disk")
    void build() throws URISyntaxException {
        DiskDeckBuilder diskDeckBuilder = new DiskDeckBuilder();
        Deck deck = diskDeckBuilder.build(Paths.get(ClassLoader.getSystemResource("test.txt").toURI()));
        assertFalse(deck.getBlackCards().isEmpty());
        assertFalse(deck.getWhiteCards().isEmpty());
    }
}