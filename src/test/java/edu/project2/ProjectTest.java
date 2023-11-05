package edu.project2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProjectTest {
    @Test
    void solvedMazeSolveCorrectResult() {
        final int[][] MAZE_GRID = new int[][]{
            {4, 12, 14, 8, 2},
            {6, 14, 13, 8, 3},
            {3, 5, 10, 4, 11},
            {1, 6, 13, 14, 11},
            {4, 13, 8, 1, 1},
        };
        var maze = new Maze(5, 5, MAZE_GRID);

        assertThat(Solver.solve(maze)).isNotEqualTo(null);
    }

    @Test
    void unsolvedMazeSolveCorrectResult() {
        final int[][] MAZE_GRID = new int[][]{
            {4, 12, 14, 8, 2},
            {6, 14, 13, 8, 3},
            {3, 5, 10, 4, 11},
            {1, 6, 13, 14, 8},
            {4, 13, 8, 1, 1},
        };
        var maze = new Maze(5, 5, MAZE_GRID);
        assertThat(Solver.solve(maze)).isEqualTo(null);
    }

    @Test
    void mazeRenderCorrectResult() {
        final int[][] MAZE_GRID = new int[][]{
            {4, 12, 14, 8, 2},
            {6, 14, 13, 8, 3},
            {3, 5, 10, 4, 11},
            {1, 6, 13, 14, 11},
            {4, 13, 8, 1, 1},
        };
        var maze = new Maze(5, 5, MAZE_GRID);
        assertThat(Renderer.render(maze)).isEqualTo("""
                ■■■■■■■■■■■
                ■□□□□□□□■□■
                ■■■■□□□■■□■
                ■□□□□□□□■□■
                ■□□□□■■■■□■
                ■□■□□□■□□□■
                ■□■■□□■■□□■
                ■□■□□□□□□□■
                ■■■□□■□□□□■
                ■□□□□□■□■□■
                ■■■■■■■■■■■
                """);
    }

    @Test
    void mazeWithPathRenderCorrectResult() {
        final int[][] MAZE_GRID = new int[][]{
            {4, 12, 14, 8, 2},
            {6, 14, 13, 8, 3},
            {3, 5, 10, 4, 11},
            {1, 6, 13, 14, 11},
            {4, 13, 8, 1, 1},
        };
        var maze = new Maze(5, 5, MAZE_GRID);
        var path = Solver.solve(maze);
        assertThat(Renderer.render(maze, path)).isEqualTo("""
                ■■■■■■■■■■■
                ■⊡⊡⊡⊡⊡⊡□■□■
                ■■■■□□⊡■■□■
                ■□□□⊡⊡⊡□■□■
                ■□□□⊡■■■■□■
                ■□■□⊡⊡■□□□■
                ■□■■□⊡■■□□■
                ■□■□□⊡⊡⊡⊡⊡■
                ■■■□□■□□□⊡■
                ■□□□□□■□■⊡■
                ■■■■■■■■■■■
                """);
    }

    @Test
    void incorrectInputGenerateCorrectResult() {
        var maze = Generator.generate(0, 0);
        assertThat(maze).isEqualTo(null);
    }
}
