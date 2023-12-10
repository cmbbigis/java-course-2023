package edu.hw9;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MultiThreadDFSTest {
    Solver solver = new Solver();
    @Test
    void solvedMazeSolve() {
        final int[][] MAZE_GRID = new int[][]{
            {4, 12, 14, 8, 2},
            {6, 14, 13, 8, 3},
            {3, 5, 10, 4, 11},
            {1, 6, 13, 14, 11},
            {4, 13, 8, 1, 1},
        };
        var maze = new Maze(5, 5, MAZE_GRID);

        assertThat(solver.solve(maze)).isNotEqualTo(null);
    }

    @Test
    void unsolvedMazeSolve() {
        final int[][] MAZE_GRID = new int[][]{
            {4, 12, 14, 8, 2},
            {6, 14, 13, 8, 3},
            {3, 5, 10, 4, 11},
            {1, 6, 13, 14, 8},
            {4, 13, 8, 1, 1},
        };
        var maze = new Maze(5, 5, MAZE_GRID);
        assertThat(solver.solve(maze)).isEqualTo(null);
    }
}
