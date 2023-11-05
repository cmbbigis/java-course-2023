package edu.project2;

public class Maze {
    public final int width;
    public final int height;
    public final int[][] grid;

    private static final int S = 2;
    private static final int E = 4;

    public Cell[][] toNormalView() {
        var result = new Cell[grid.length * 2][grid[0].length * 2];
        for (var y = 0; y <= grid.length * 2 - 2; y += 2) {
            for (var x = 0; x <= grid[0].length * 2 - 2; x += 2) {
                var x1 = x / 2;
                var y1 = y / 2;
                var cell = grid[y1][x1];
                if ((cell & S) != 0) {
                    result[y][x] = new Cell(y, x, Cell.Type.FREE);
                    result[y + 1][x] = new Cell(y, x, Cell.Type.FREE);
                } else {
                    result[y][x] = new Cell(y, x, Cell.Type.FREE);
                    result[y + 1][x] = new Cell(y, x, Cell.Type.WALL);
                }
                if ((cell & E) != 0) {
                    if (((cell | grid[y1][x1 + 1]) & S) != 0) {
                        result[y][x + 1] = new Cell(y, x, Cell.Type.FREE);
                        result[y + 1][x + 1] = new Cell(y, x, Cell.Type.FREE);
                    } else {
                        result[y][x + 1] = new Cell(y, x, Cell.Type.FREE);
                        result[y + 1][x + 1] = new Cell(y, x, Cell.Type.WALL);
                    }
                } else {
                    result[y][x + 1] = new Cell(y, x, Cell.Type.WALL);
                    result[y + 1][x + 1] = new Cell(y, x, Cell.Type.WALL);
                }
            }
        }
        return result;
    }

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new int[height][width];
    }
}
