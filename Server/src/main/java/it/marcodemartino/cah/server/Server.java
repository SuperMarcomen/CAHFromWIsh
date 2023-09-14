package it.marcodemartino.cah.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start(6666);
    }


    public void start(int port) throws IOException {
        GameManager gameManager = new GameManager();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Server listening on " + serverSocket.getInetAddress());
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket, gameManager).start();
            }
        }
    }
}
