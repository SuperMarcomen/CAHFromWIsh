package it.marcodemartino.cah.json.server;

public class DeckInfoObject {

    private final String name;
    private final int whiteCardsAmount;
    private final int blackCardsAmount;
    private final boolean official;

    public DeckInfoObject(String name, int whiteCardsAmount, int blackCardsAmount, boolean official) {
        this.name = name;
        this.whiteCardsAmount = whiteCardsAmount;
        this.blackCardsAmount = blackCardsAmount;
        this.official = official;
    }

    public String getName() {
        return name;
    }

    public int getWhiteCardsAmount() {
        return whiteCardsAmount;
    }

    public int getBlackCardsAmount() {
        return blackCardsAmount;
    }

    public boolean isOfficial() {
        return official;
    }
}
