package it.marcodemartino.cah.game.cards;

public class WhiteCard {

    private final String text;

    public WhiteCard(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WhiteCard other)) return false;
        return text.equals(other.getText());
    }
}
