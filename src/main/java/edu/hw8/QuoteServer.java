package edu.hw8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuoteServer implements Runnable {
    private static final int MAX_CONNECTIONS = 5;
    private static final ExecutorService POOL = Executors.newFixedThreadPool(MAX_CONNECTIONS);
    private static final ConcurrentHashMap<String, String> QUOTES = new ConcurrentHashMap<>();
    private final ServerSocket serverSocket;

    static {
        QUOTES.put("личности", "Не переходи на личности там, где их нет");
        QUOTES.put("оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами");
        QUOTES.put("глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.");
        QUOTES.put("интеллект", "Чем ниже интеллект, тем громче оскорбления");
    }

    public QuoteServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                POOL.execute(new ClientHandler(clientSocket));
            } catch (IOException e) {
                System.err.println("Server error: " + e.getMessage());
                break;
            }
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String request;
                while ((request = in.readLine()) != null) {
                    String response = QUOTES.getOrDefault(request, "Неизвестная тема");
                    out.println(response);
                }

                clientSocket.close();
            } catch (IOException e) {
                System.err.println("ClientHandler error: " + e.getMessage());
            }
        }
    }
}
