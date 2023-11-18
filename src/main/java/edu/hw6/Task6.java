package edu.hw6;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task6 {
    private Task6() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    private final static String PORT_STRING = "Порт ";

    private static final int PORTS_COUNT = 49151;
    private static final int HTTP_PORT = 80;
    private static final int FTP_PORT = 21;
    private static final int SMTP_PORT = 25;
    private static final int SSH_PORT = 22;
    private static final int HTTPS_PORT = 443;
    private static final int DNS_PORT = 53;

    private static final Map<Integer, String> KNOWN_PORTS = new HashMap<>() {{
        put(HTTP_PORT, "HTTP (HyperText Transfer Protocol)");
        put(FTP_PORT, "FTP (File Transfer Protocol)");
        put(SMTP_PORT, "SMTP (Simple Mail Transfer Protocol)");
        put(SSH_PORT, "SSH (Secure Shell)");
        put(HTTPS_PORT, "HTTPS (HyperText Transfer Protocol Secure)");
        put(DNS_PORT, "DNS (Domain Name System)");
    }};

    public static void doTask() {
        for (int port = 0; port <= PORTS_COUNT; port++) {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                LOGGER.trace(PORT_STRING + port + " свободен");
            } catch (IOException e) {
                String service = KNOWN_PORTS.getOrDefault(port, "Неизвестный сервис");
                LOGGER.trace(PORT_STRING + port + " занят. Сервис: " + service);
            }
        }
    }

//    public static void main(String[] args) {
//        doTask();
//    }
}
