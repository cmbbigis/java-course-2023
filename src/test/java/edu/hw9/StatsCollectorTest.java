package edu.hw9;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StatsCollectorTest {
    private Map<String, Map<String, Double>> stats;

    @BeforeEach
    public void setup() {
        StatsCollector collector = new StatsCollector();
        collector.push("metric1", new double[] {1.0, 2.0, 3.0, 4.0, 5.0});
        collector.push("metric2", new double[] {2.0, 3.0, 4.0, 5.0, 6.0});
        stats = collector.stats();
    }

    @Test
    public void testSum() {
        assertThat(stats.get("metric1").get("sum")).isEqualTo(15.0);

        assertThat(stats.get("metric2").get("sum")).isEqualTo(20.0);
    }

    @Test
    public void testAverage() {
        assertThat(stats.get("metric1").get("avg")).isEqualTo(3.0);

        assertThat(stats.get("metric2").get("avg")).isEqualTo(4.0);
    }

    @Test
    public void testMaximum() {
        assertThat(stats.get("metric1").get("max")).isEqualTo(5.0);

        assertThat(stats.get("metric2").get("max")).isEqualTo(6.0);
    }

    @Test
    public void testMinimum() {
        assertThat(stats.get("metric1").get("min")).isEqualTo(1.0);

        assertThat(stats.get("metric2").get("min")).isEqualTo(2.0);
    }
}
