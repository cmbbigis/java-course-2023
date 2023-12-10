package edu.hw9;

import java.util.List;
import java.util.concurrent.RecursiveTask;
import static edu.hw9.Solver.DIRECTIONS;
import static edu.hw9.Solver.grid;
import static edu.hw9.Solver.isSafe;
import static edu.hw9.Solver.visited;

public class MultiThreadDFS extends RecursiveTask<Boolean> {
    private final int row;
    private final int col;
    private final int endY;
    private final int endX;
    private final List<Coordinate> path;

    public MultiThreadDFS(int row, int col, int endY, int endX, List<Coordinate> path) {
        this.row = row;
        this.col = col;
        this.endY = endY;
        this.endX = endX;
        this.path = path;
    }

    @Override
    protected Boolean compute() {
        if (!isSafe(row, col) || visited[row][col] || grid[row][col].type() == Cell.Type.WALL) {
            return false;
        }

        visited[row][col] = true;
        path.add(new Coordinate(row, col));
        if (row == endY && col == endX) {
            return true;
        }

        for (var direction : DIRECTIONS) {
            MultiThreadDFS dfs = new MultiThreadDFS(row + direction.row(), col + direction.col(), endY, endX, path);
            dfs.fork();
            if (dfs.join()) {
                return true;
            }
        }
        path.remove(path.size() - 1);
        return false;
    }
}
