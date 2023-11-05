package edu.project2;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    private static final Coordinate[] DIRECTIONS = {
        new Coordinate(0, 1),
        new Coordinate(1, 0),
        new Coordinate(0, -1),
        new Coordinate(-1, 0)
    };
    private boolean[][] visited;
    private Cell[][] grid;

    public List<Coordinate> solve(Maze maze) {
        if (maze.grid.length == 0) {
            return null;
        }
        List<Coordinate> path = new ArrayList<>();
        grid = maze.toNormalView();
        visited = new boolean[grid.length - 1][grid[0].length - 1];
        if (dfs(0, 0, grid.length - 2, grid[0].length - 2, path)) {
            return path;
        }
        return null;
    }

    private boolean dfs(int row, int col, int endY, int endX, List<Coordinate> path) {
        if (!isSafe(row, col) || visited[row][col] || grid[row][col].type() == Cell.Type.WALL) {
            return false;
        }

        visited[row][col] = true;
        path.add(new Coordinate(row, col));
        if (row == endY && col == endX) {
            return true;
        }

        for (var direction : DIRECTIONS) {
            if (dfs(row + direction.row(), col + direction.col(), endY, endX, path)) {
                return true;
            }
        }
        path.remove(path.size() - 1);
        return false;
    }

    private boolean isSafe(int row, int col) {
        return row >= 0 && row < grid.length - 1 && col >= 0 && col < grid[0].length - 1
            && grid[row][col].type() != Cell.Type.WALL;
    }
}
