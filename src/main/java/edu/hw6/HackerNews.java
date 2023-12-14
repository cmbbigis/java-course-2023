package edu.hw6;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String ITEM_URL_TEMPLATE = "https://hacker-news.firebaseio.com/v0/item/%d.json";

    public long[] hackerNewsTopStories() {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(TOP_STORIES_URL))
            .build();

        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            body = body.substring(1, body.length() - 1);  // remove brackets
            return Arrays.stream(body.split(","))
                .mapToLong(Long::parseLong)
                .toArray();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String news(long id) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(String.format(ITEM_URL_TEMPLATE, id)))
            .build();

        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            Pattern pattern = Pattern.compile("\"title\":\"(.*?)\"");
            Matcher matcher = pattern.matcher(response.body());
            if (matcher.find()) {
                return matcher.group(1);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
