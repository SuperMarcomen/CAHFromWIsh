package it.marcodemartino.cah.server.commands;

import it.marcodemartino.cah.json.client.QuitObject;
import it.marcodemartino.cah.server.GameManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class QuitCommand extends Command<QuitObject> {

    private final Logger logger = LogManager.getLogger(QuitCommand.class);
    private final GameManager gameManager;
    private final String clientIp;

    public QuitCommand(BufferedReader in, PrintWriter out, GameManager gameManager, String clientIp) {
        super(in, out, QuitObject.class);
        this.gameManager = gameManager;
        this.clientIp = clientIp;
    }

    @Override
    public void execute(QuitObject object) {
        logger.info("Connection with IP terminated: {}", clientIp);
        out.println("quit");

        if (object.getPlayerUUID() == null) return;
        gameManager.quitPlayer(object.getPlayerUUID());
    }
}
