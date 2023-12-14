package edu.hw6;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class HackerNewsTest {
    private HackerNews hackerNews;

    @BeforeEach
    public void setUp() {
        hackerNews = new HackerNews();
    }

    @Test
    public void hackerNewsTopStories() {
        long[] topStories = hackerNews.hackerNewsTopStories();
        assertThat(topStories).isNotNull();
        assertThat(topStories.length > 0).isTrue();
    }

    @Test
    public void news() {
        long[] topStories = hackerNews.hackerNewsTopStories();
        if (topStories.length > 0) {
            String newsTitle = hackerNews.news(topStories[0]);
            assertThat(newsTitle).isNotNull();
            assertThat(newsTitle.isEmpty()).isFalse();
        }
    }
}
