package edu.hw8;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.ServerSocket;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class QuoteServerTest {
    private static Thread serverThread;
    private static ServerSocket serverSocket;
    private static QuoteClient quoteClient;

    @BeforeAll
    public static void setup() throws IOException {
        serverSocket = new ServerSocket(12345);
        QuoteServer server = new QuoteServer(serverSocket);
        serverThread = new Thread(server);
        serverThread.start();

        quoteClient = new QuoteClient("localhost", 12345);
    }

    @AfterAll
    public static void tearDown() throws IOException {
        quoteClient.close();
        serverSocket.close();
        serverThread.interrupt();
    }

    @Test
    public void allRequestsGetQuotes() {
        assertThat(quoteClient.getQuote("личности")).isEqualTo("Не переходи на личности там, где их нет");

        assertThat(quoteClient.getQuote("оскорбления")).isEqualTo("Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами");

        assertThat(quoteClient.getQuote("глупый")).isEqualTo("А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.");

        assertThat(quoteClient.getQuote("интеллект")).isEqualTo("Чем ниже интеллект, тем громче оскорбления");
    }
}
