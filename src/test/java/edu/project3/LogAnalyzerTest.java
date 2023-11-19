package edu.project3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LogAnalyzerTest {

    private static final DateTimeFormatter ISO8601 = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss Z", Locale.ENGLISH);
    private static final String FROM = "2015-05-1700:00:00 +0000";
    private static final String TO = "2015-05-1800:00:00 +0000";
    private final static Logger LOGGER = LogManager.getLogger();

    @Test
    void fromAndToReadAndAnalyzeLogs() {
        final String path = "logs.txt";

        List<LogEntry> logEntries = LogAnalyzer.readAndAnalyzeLogs(path, FROM, TO);

        assertThat(logEntries.isEmpty()).isFalse();

        assertThat(logEntries.stream().noneMatch(entry -> entry.date.isBefore(LocalDateTime.parse(FROM, ISO8601)))).isTrue();
        assertThat(logEntries.stream().noneMatch(entry -> entry.date.isAfter(LocalDateTime.parse(TO, ISO8601)))).isTrue();
    }

    @Test
    void pathReadAndAnalyzeLogs() {
        final String path = "logs.txt";

        List<LogEntry> logEntries = LogAnalyzer.readAndAnalyzeLogs(path, FROM, TO);

        assertThat(logEntries.isEmpty()).isFalse();
    }

    @Test
    void twoPathsReadAndAnalyzeLogs() {
        final String path = "logs.txt,logs1.txt";

        List<LogEntry> logEntries = LogAnalyzer.readAndAnalyzeLogs(path, FROM, TO);

        assertThat(logEntries.isEmpty()).isFalse();
    }

    @Test
    void URLReadAndAnalyzeLogs() {
        final String path = "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";

        List<LogEntry> logEntries = LogAnalyzer.readAndAnalyzeLogs(path, FROM, TO);

        assertThat(logEntries.isEmpty()).isFalse();
    }

    @Test
    void anotherFormatGenerateCommonReport() {
        final String path = "logs.txt";

        List<LogEntry> logEntries = LogAnalyzer.readAndAnalyzeLogs(path, FROM, TO);

        String report = LogAnalyzer.generateReport(logEntries, path, "another");

        assertThat(logEntries.isEmpty()).isFalse();

        assertThat(report.contains("Общая информация")).isTrue();
        assertThat(report.contains("Запрашиваемые ресурсы")).isTrue();
        assertThat(report.contains("Коды ответа")).isTrue();
    }

    @Test
    void markdownFormatGenerateMarkdownReport() {
        final String path = "logs.txt";

        List<LogEntry> logEntries = LogAnalyzer.readAndAnalyzeLogs(path, FROM, TO);

        String report = LogAnalyzer.generateReport(logEntries, path, "markdown");

        assertThat(logEntries.isEmpty()).isFalse();

        assertThat(report.contains("Общая информация")).isTrue();
        assertThat(report.contains("Запрашиваемые ресурсы")).isTrue();
        assertThat(report.contains("Коды ответа")).isTrue();
    }

    @Test
    void adocFormatGenerateAdocReport() {
        final String path = "logs.txt";

        List<LogEntry> logEntries = LogAnalyzer.readAndAnalyzeLogs(path, FROM, TO);

        String report = LogAnalyzer.generateReport(logEntries, path, "adoc");

        assertThat(logEntries.isEmpty()).isFalse();

        assertThat(report.contains("== Общая информация")).isTrue();
        assertThat(report.contains("== Запрашиваемые ресурсы")).isTrue();
        assertThat(report.contains("== Коды ответа")).isTrue();
    }
}
