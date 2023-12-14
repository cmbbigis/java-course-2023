package edu.hw8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class QuoteClient {
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    public QuoteClient(String serverAddress, int serverPort) throws IOException {
        socket = new Socket(serverAddress, serverPort);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public String getQuote(String topic) {
        out.println(topic);
        try {
            return in.readLine();
        } catch (IOException e) {
            return "Ошибка при получении цитаты: " + e.getMessage();
        }
    }

    public void close() throws IOException {
        socket.close();
    }
}
