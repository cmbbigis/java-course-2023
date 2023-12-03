package edu.hw8;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class QuoteServerTest {
    private static Thread serverThread;
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;

    @BeforeAll
    public static void setup() throws IOException {
        serverSocket = new ServerSocket(12345);
        QuoteServer server = new QuoteServer(serverSocket);
        serverThread = new Thread(server);
        serverThread.start();

        socket = new Socket("localhost", 12345);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @AfterAll
    public static void tearDown() throws IOException {
        socket.close();
        serverSocket.close();
        serverThread.interrupt();
    }

    @Test
    public void allRequestsGetQuotes() throws IOException {
        out.println("личности");
        assertThat(in.readLine()).isEqualTo("Не переходи на личности там, где их нет");

        out.println("оскорбления");
        assertThat(in.readLine()).isEqualTo("Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами");

        out.println("глупый");
        assertThat(in.readLine()).isEqualTo("А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.");

        out.println("интеллект");
        assertThat(in.readLine()).isEqualTo("Чем ниже интеллект, тем громче оскорбления");
    }
}
