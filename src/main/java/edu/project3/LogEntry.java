package edu.project3;

import java.time.LocalDateTime;

class LogEntry {
    String ip;
    LocalDateTime date;
    String request;
    int status;
    int bytes;
    String userAgent;

    LogEntry(String ip, LocalDateTime date, String request, int status, int bytes, String userAgent) {
        this.ip = ip;
        this.date = date;
        this.request = request;
        this.status = status;
        this.bytes = bytes;
        this.userAgent = userAgent;
    }
}
