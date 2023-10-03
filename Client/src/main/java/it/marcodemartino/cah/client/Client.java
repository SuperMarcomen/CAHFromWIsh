package it.marcodemartino.cah.client;

import it.marcodemartino.cah.client.commands.Command;
import it.marcodemartino.cah.client.commands.GameCreatedCommand;
import it.marcodemartino.cah.client.commands.JoinGameResultCommand;
import it.marcodemartino.cah.client.commands.NotifyPlayerJoinCommand;
import it.marcodemartino.cah.client.commands.NotifyPlayerPlayedCommand;
import it.marcodemartino.cah.client.commands.ReceiveCardsCommand;
import it.marcodemartino.cah.client.commands.ReceivePlayedCardsCommand;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.scenes.SceneController;
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

public class Client extends Thread {

    private static final Logger logger = LogManager.getLogger(Client.class);
    private final Map<String, Command> commands;

    private final GameManager gameManager;
    private final SceneController sceneController;

    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private boolean running;

    public Client(GameManager gameManager, SceneController sceneController) {
        this.sceneController = sceneController;
        this.commands = new HashMap<>();
        running = true;
        this.gameManager = gameManager;
        registerActions();
    }

    public void run() {
       logger.info("Starting to listening to inputs");
        while (running) {
            Optional<String> input = getInput();
            if (input.isEmpty()) continue;

            if (input.get().equals("quit")) break;

            String methodName = new JSONObject(input.get())
                    .getString("method");
            Command command = commands.get(methodName);
            if (command == null) continue;

            command.execute(input.get());
        }
    }

    @Override
    public synchronized void start() {
        try {
//            startConnection("127.0.0.1", 6666);
            startConnection("129.152.19.2", 6666);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        super.start();
    }

    public void startConnection(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socket.setKeepAlive(true);
    }

    public void stopConnection() {
        running = false;
        try {
            in.close();
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
        commands.put("game_created", new GameCreatedCommand(out, gameManager, this, sceneController));
        commands.put("send_cards", new ReceiveCardsCommand(out, gameManager, sceneController));
        commands.put("send_all_cards", new ReceivePlayedCardsCommand(out, gameManager, sceneController));
        commands.put("notify_player_join", new NotifyPlayerJoinCommand(out, gameManager, sceneController));
        commands.put("join_game_result", new JoinGameResultCommand(out, sceneController));
        commands.put("notify_player_played", new NotifyPlayerPlayedCommand(out, gameManager));
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
