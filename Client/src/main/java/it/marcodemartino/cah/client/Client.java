package it.marcodemartino.cah.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;

public class Client {

    private static final Logger logger = LogManager.getLogger();
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;

    public void startConnection(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socket.setKeepAlive(true);
    }

    public void stopConnection() {
        out.println("quit");
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
}
