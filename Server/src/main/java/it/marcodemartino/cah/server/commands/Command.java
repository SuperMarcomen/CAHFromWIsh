package it.marcodemartino.cah.server.commands;

import com.google.gson.Gson;
import it.marcodemartino.cah.json.GsonInstance;
import it.marcodemartino.cah.json.JSONObject;

import java.io.BufferedReader;
import java.io.PrintWriter;

public abstract class Command<T extends JSONObject> {

    protected final BufferedReader in;
    protected final PrintWriter out;
    private final Gson gson;
    private final Class<T> typeClass;

    public Command(BufferedReader in, PrintWriter out, Class<T> typeClass) {
        this.in = in;
        this.out = out;
        this.typeClass = typeClass;
        this.gson = GsonInstance.get();
    }

    public void execute(String input) {
        execute(gson.fromJson(input, typeClass));
    }

    protected abstract void execute(T t);

    public void send(JSONObject messageObject) {
        String message = gson.toJson(messageObject);
        out.println(message);
        out.flush();
    }

}
