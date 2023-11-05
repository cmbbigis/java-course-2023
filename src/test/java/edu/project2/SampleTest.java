package edu.project2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SampleTest {
    @Test
    void filterEvenNumbers() {
        var maze = Generator.generate(5, 10);

        Renderer.render(maze);

        var solver = new Solver();
        var path = solver.solve(maze);
        if (path != null) {
            Renderer.render(maze, path);
        } else {
            System.out.println("Нет решений!");
        }
    }
}
