package it.marcodemartino.cah.server.commands;

import java.io.BufferedReader;
import java.io.PrintWriter;

public abstract class Command {

    protected final BufferedReader in;
    protected final PrintWriter out;

    public Command(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    public abstract void execute(String input);

}
