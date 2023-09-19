package it.marcodemartino.cah.client;

import it.marcodemartino.cah.client.commands.Command;
import it.marcodemartino.cah.client.commands.GameCreatedCommand;
import it.marcodemartino.cah.client.commands.ReceiveCardsCommand;
import it.marcodemartino.cah.client.game.GameManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Client implements Runnable {

    private static final Logger logger = LogManager.getLogger();
    private final Map<String, Command> commands;

    private final GameManager gameManager;

    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private boolean running;

    public Client() {
        this.commands = new HashMap<>();
        running = true;
        gameManager = new GameManager();
        registerActions();
    }

    public void run() {
        System.out.println("hi");
        while (running) {
            Optional<String> input = getInput();
            if (input.isEmpty()) continue;
            System.out.println(input.get());
            String methodName = new JSONObject(input.get())
                    .getString("method");
            Command command = commands.get(methodName);
            if (command == null) continue;

            command.execute(input.get());
        }
    }

    public void startConnection(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socket.setKeepAlive(true);
    }

    public void stopConnection() {
        out.println("quit");
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        running = false;
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendOutput(String string) {
        out.println(string);
        out.flush();
    }

    public Optional<String> getInput() {
        Optional<String> inputLine = Optional.empty();
        try {
            inputLine = Optional.ofNullable(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("An error happened while reading for a new input");
        }

        return inputLine;
    }

    private void registerActions() {
        commands.put("game_created", new GameCreatedCommand(out, gameManager));
        commands.put("send_cards", new ReceiveCardsCommand(out, gameManager));
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
