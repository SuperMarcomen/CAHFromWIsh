package it.marcodemartino.cah.client.commands;

import java.io.PrintWriter;

public abstract class Command {

    protected final PrintWriter out;

    public Command(PrintWriter out) {
        this.out = out;
    }

    public abstract void execute(String input);

}
