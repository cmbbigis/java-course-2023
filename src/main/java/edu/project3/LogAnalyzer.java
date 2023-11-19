package edu.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class LogAnalyzer {
    private LogAnalyzer() {
    }

    private final static String ADOC_SEPARATOR = "|===\n";
    private final static String ADOC_PARAMETERS = "[cols=\"2*\", options=\"header\"]\n";
    private final static String FILES = "| Файл(-ы) | ";
    private final static String LINE_BREAK = "|\n";
    private final static String START_DATE = "| Начальная дата | ";
    private final static String FINAL_DATE = "| Конечная дата | ";
    private final static String REQUESTS_COUNT = "| Количество запросов | ";
    private final static String AVERAGE_RESPONSE_SIZE = "| Средний размер ответа | ";
    private final static String COLUMNS_SEPARATOR = " | ";
    private final static String ROWS_SEPARATOR = "|:---|---:|\\n";


    private final static int MATCHER_GROUP_ONE = 1;
    private final static int MATCHER_GROUP_TWO = 2;
    private final static int MATCHER_GROUP_THREE = 3;
    private final static int MATCHER_GROUP_FOUR = 4;
    private final static int MATCHER_GROUP_FIVE = 5;
    private final static int MATCHER_GROUP_SIX = 6;

    private final static Logger LOGGER = LogManager.getLogger();
    private static final Pattern LOG_ENTRY_PATTERN =
        Pattern.compile("^(\\S+) - - \\[(.+)] \"(.+?)\" (\\d{3}) (\\d+) \"-\" \"(.+?)\"$");
    private static final DateTimeFormatter DATE_FORMAT =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
    private static final DateTimeFormatter ISO8601 =
        DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss Z", Locale.ENGLISH);

//    public static void main(String[] args) {
//        Map<String, String> arguments = parseArguments(args);
//
//        List<LogEntry> logEntries =
//            readAndAnalyzeLogs(arguments.get("path"), arguments.get("from"), arguments.get("to"));
//
//        String report = generateReport(logEntries, arguments.get("path"), arguments.get("format"));
//
//        LOGGER.trace(report);
//    }

    private static Map<String, String> parseArguments(String[] args) {
        Map<String, String> arguments = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            arguments.put(args[i].substring(2), args[i + 1]);
        }
        return arguments;
    }

    protected static List<LogEntry> readAndAnalyzeLogs(String path, String from, String to) {
        LocalDateTime fromDate = from != null ? LocalDateTime.parse(from, ISO8601) : null;
        LocalDateTime toDate = to != null ? LocalDateTime.parse(to, ISO8601) : null;

        List<LogEntry> allLogEntries = new ArrayList<>();
        for (String p : path.split(",")) {
            try {
                Stream<String> lines;
                if (p.startsWith("http://") || p.startsWith("https://")) {
                    URL url = new URL(p);
                    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                    lines = in.lines();
                } else {
                    lines = Files.lines(Paths.get(p));
                }

                List<LogEntry> logEntries = lines.map(LOG_ENTRY_PATTERN::matcher)
                    .filter(Matcher::matches)
                    .map(matcher -> new LogEntry(
                        matcher.group(MATCHER_GROUP_ONE),
                        LocalDateTime.parse(matcher.group(MATCHER_GROUP_TWO), DATE_FORMAT),
                        matcher.group(MATCHER_GROUP_THREE),
                        Integer.parseInt(matcher.group(MATCHER_GROUP_FOUR)),
                        Integer.parseInt(matcher.group(MATCHER_GROUP_FIVE)),
                        matcher.group(MATCHER_GROUP_SIX)))
                    .filter(logEntry -> (fromDate == null || !logEntry.date.isBefore(fromDate))
                        && (toDate == null || !logEntry.date.isAfter(toDate)))
                    .toList();

                allLogEntries.addAll(logEntries);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }

        return allLogEntries;
    }

    protected static String generateReport(List<LogEntry> logEntries, String path, String format) {
        if ("markdown".equals(format)) {
            return generateMarkdownReport(logEntries, path);
        } else if ("adoc".equals(format)) {
            return generateAdocReport(logEntries, path);
        } else {
            return generateCommonReport(logEntries, path);
        }
    }

    private static String generateAdocReport(List<LogEntry> logEntries, String path) {
        AllForReport allForReport = getAllForReport(logEntries);

        allForReport.report().append("== Общая информация\n\n");
        allForReport.report().append(ADOC_PARAMETERS);
        allForReport.report().append(ADOC_SEPARATOR);
        allForReport.report().append("| Метрика | Значение\n");
        allForReport.report().append(FILES).append(path).append(" |\n");
        allForReport.report().append(START_DATE)
            .append(logEntries.get(0).date.format(DateTimeFormatter.ISO_DATE_TIME)).append("\n");
        allForReport.report().append(FINAL_DATE)
            .append(logEntries.get(logEntries.size() - 1).date.format(DateTimeFormatter.ISO_DATE_TIME)).append("\n");
        allForReport.report().append(REQUESTS_COUNT).append(allForReport.totalRequests()).append("\n");
        allForReport.report().append(AVERAGE_RESPONSE_SIZE)
            .append(allForReport.averageResponseSize()).append("b\n");
        allForReport.report().append(ADOC_SEPARATOR);

        allForReport.report().append("\n== Запрашиваемые ресурсы\n\n");
        allForReport.report().append(ADOC_PARAMETERS);
        allForReport.report().append(ADOC_SEPARATOR);
        allForReport.report().append("| Ресурс | Количество\n");
        allForReport.resources().entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .forEach(entry -> allForReport.report().append("| ")
                .append(entry.getKey()).append(COLUMNS_SEPARATOR).append(entry.getValue()).append("\n"));
        allForReport.report().append(ADOC_SEPARATOR);

        allForReport.report().append("\n== Коды ответа\n\n");
        allForReport.report().append("[cols=\"3*\", options=\"header\"]\n");
        allForReport.report().append(ADOC_SEPARATOR);
        allForReport.report().append("| Код | Имя | Количество\n");
        allForReport.statusCodes().entrySet().stream()
            .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
            .forEach(entry -> allForReport.report()
                .append("| ")
                .append(entry.getKey())
                .append(COLUMNS_SEPARATOR)
                .append(entry.getKey())
                .append(COLUMNS_SEPARATOR)
                .append(entry.getValue())
                .append("\n"));
        allForReport.report().append(ADOC_SEPARATOR);

        return allForReport.report().toString();
    }

    private static String generateMarkdownReport(List<LogEntry> logEntries, String path) {
        return generateBaseReport(logEntries, path);
    }

    private static String generateCommonReport(List<LogEntry> logEntries, String path) {
        return generateBaseReport(logEntries, path);
    }

    @NotNull private static String generateBaseReport(List<LogEntry> logEntries, String path) {
        AllForReport allForReport = getAllForReport(logEntries);

        allForReport.report.append("#### Общая информация\n\n");
        allForReport.report.append("| Метрика | Значение |\n");
        allForReport.report.append(ROWS_SEPARATOR);
        allForReport.report.append(FILES).append(path).append(LINE_BREAK);
        allForReport.report.append(START_DATE)
            .append(logEntries.get(0).date.format(DateTimeFormatter.ISO_DATE_TIME)).append(LINE_BREAK);
        allForReport.report.append(FINAL_DATE)
            .append(logEntries.get(logEntries.size() - 1).date.format(DateTimeFormatter.ISO_DATE_TIME))
            .append(LINE_BREAK);
        allForReport.report.append(REQUESTS_COUNT).append(allForReport.totalRequests).append(LINE_BREAK);
        allForReport.report.append(AVERAGE_RESPONSE_SIZE)
            .append(allForReport.averageResponseSize).append("b |\n");

        allForReport.report.append("\n#### Запрашиваемые ресурсы\n\n");
        allForReport.report.append("| Ресурс | Количество |\n");
        allForReport.report.append(ROWS_SEPARATOR);
        allForReport.resources.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .forEach(entry -> allForReport.report.append("| ")
                .append(entry.getKey()).append(COLUMNS_SEPARATOR).append(entry.getValue()).append(LINE_BREAK));

        allForReport.report.append("\n#### Коды ответа\n\n");
        allForReport.report.append("| Код | Имя | Количество |\n");
        allForReport.report.append("|:---|:---|---:|\n");
        allForReport.statusCodes.entrySet().stream()
            .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
            .forEach(entry -> allForReport.report.append("| ").append(entry.getKey()).append(COLUMNS_SEPARATOR).append(
                entry.getKey()).append(COLUMNS_SEPARATOR).append(entry.getValue()).append(LINE_BREAK));

        return allForReport.report.toString();
    }

    @NotNull private static AllForReport getAllForReport(List<LogEntry> logEntries) {
        long totalRequests = logEntries.size();
        double averageResponseSize = logEntries.stream().mapToLong(logEntry -> logEntry.bytes).average()
            .orElse(0);

        Map<String, Long> resources = logEntries.stream()
            .collect(Collectors.groupingBy(logEntry -> logEntry.request, Collectors.counting()));

        Map<Integer, Long> statusCodes = logEntries.stream()
            .collect(Collectors.groupingBy(logEntry -> logEntry.status, Collectors.counting()));

        StringBuilder report = new StringBuilder();
        return new AllForReport(totalRequests, averageResponseSize, resources, statusCodes, report);
    }

    private record AllForReport(long totalRequests, double averageResponseSize,
                                Map<String, Long> resources, Map<Integer, Long> statusCodes, StringBuilder report) {
    }
}
