package it.marcodemartino.cah.json;

public class EmptyJsonObject implements JSONObject {

    private final String method;

    public EmptyJsonObject(String method) {
        this.method = method;
    }

    @Override
    public String getMethod() {
        return method;
    }
}
