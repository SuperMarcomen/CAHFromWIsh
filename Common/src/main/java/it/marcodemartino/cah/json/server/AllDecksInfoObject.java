package it.marcodemartino.cah.json.server;

import it.marcodemartino.cah.json.JSONObject;

import java.util.List;

public class AllDecksInfoObject implements JSONObject {

    private final String method = "all_decks_info";
    private final List<DeckInfoObject> deckInfos;

    public AllDecksInfoObject(List<DeckInfoObject> deckInfos) {
        this.deckInfos = deckInfos;
    }

    @Override
    public String getMethod() {
        return method;
    }

    public List<DeckInfoObject> getDeckInfos() {
        return deckInfos;
    }
}
