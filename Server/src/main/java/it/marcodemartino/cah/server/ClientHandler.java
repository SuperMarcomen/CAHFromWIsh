package it.marcodemartino.cah.server;

import it.marcodemartino.cah.server.commands.Command;
import it.marcodemartino.cah.server.commands.CreateNewGameCommand;
import it.marcodemartino.cah.server.commands.JoinGameCommand;
import it.marcodemartino.cah.server.commands.PlayCardsCommand;
import it.marcodemartino.cah.server.commands.QuitCommand;
import it.marcodemartino.cah.server.commands.StartGameCommand;
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

public class ClientHandler extends Thread {

    private static final Logger logger = LogManager.getLogger(ClientHandler.class);
    private final Socket client;
    private final Map<String, Command> commands;
    private final GameManager gameManager;
    private BufferedReader in;
    private PrintWriter out;


    public ClientHandler(Socket client, GameManager gameManager) {
        this.client = client;
        this.gameManager = gameManager;
        commands = new HashMap<>();
        logger.info("New connection with IP: {}", client.getRemoteSocketAddress().toString());
    }

    @Override
    public void run() {
        if (!initInOut()) {
            logger.error(("An error happened during the initialization of th I/O stream"));
            return;
        }
        registerCommands();

        Optional<String> inputLine;
        while ((inputLine = getInput()).isPresent()) {
            String input = inputLine.get();

            if (input.isEmpty()) return;

            String methodName = new JSONObject(input)
                    .getString("method");
            Command command = commands.get(methodName);
            if (command == null) continue;
            command.execute(input);
        }

    }

    private void registerCommands() {
        commands.put("new_game", new CreateNewGameCommand(in, out, gameManager));
        commands.put("join_game", new JoinGameCommand(in, out, gameManager));
        commands.put("start_game", new StartGameCommand(in, out, gameManager));
        commands.put("play_cards", new PlayCardsCommand(in, out, gameManager));
        commands.put("quit", new QuitCommand(in, out, gameManager, client.getRemoteSocketAddress().toString()));
    }

    private Optional<String> getInput() {
        Optional<String> inputLine = Optional.empty();
        try {
            inputLine = Optional.ofNullable(in.readLine());
        } catch (IOException e) {
            logger.error("An error happened while reading for a new input");
        }
        return inputLine;
    }

    private boolean initInOut() {
        try {
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
