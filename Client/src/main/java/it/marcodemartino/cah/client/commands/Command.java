package it.marcodemartino.cah.client.commands;

import com.google.gson.Gson;
import it.marcodemartino.cah.json.GsonInstance;
import it.marcodemartino.cah.json.JSONObject;

public abstract class Command<T extends JSONObject> {

    private final Gson gson;
    private final Class<T> typeClass;

    public Command(Class<T> typeClass) {
        this.typeClass = typeClass;
        this.gson = GsonInstance.get();
    }

    public void execute(String input) {
        execute(gson.fromJson(input, typeClass));
    }

    protected abstract void execute(T t);

}
