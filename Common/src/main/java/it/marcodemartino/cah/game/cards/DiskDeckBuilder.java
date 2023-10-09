package it.marcodemartino.cah.game.cards;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.marcodemartino.cah.json.GsonInstance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;

public class DiskDeckBuilder implements DeckBuilder {

    private static final Logger logger = LogManager.getLogger(DiskDeckBuilder.class);

    @Override
    public Map<String, Deck> build(Path path) {
        try {
            String content = new String(Files.readAllBytes(path));
            Gson gson = GsonInstance.get();

            Type deckMapType = new TypeToken<Map<String, Deck>>() {}.getType();
            Map<String, Deck> deckMap = gson.fromJson(content, deckMapType);
            logger.info("Loaded {} decks from disk", deckMap.values().size());
            return deckMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyMap();
    }
}
