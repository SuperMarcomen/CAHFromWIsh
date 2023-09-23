package it.marcodemartino.cah.game.cards;

public class BlackCard {

    private final String text;
    private final int numberOfParameters;

    public BlackCard(String text, int numberOfParameters) {
        this.text = text;
        this.numberOfParameters = numberOfParameters;
    }

    public String getText() {
        return text;
    }

    public int getNumberOfParameters() {
        return numberOfParameters;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BlackCard other)) return false;
        return text.equals(other.getText()) && numberOfParameters == other.getNumberOfParameters();
    }
}
