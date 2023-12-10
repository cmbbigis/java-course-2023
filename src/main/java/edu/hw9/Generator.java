package edu.hw9;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

public class Generator {
    private static final int N = 1;
    private static final int S = 2;
    private static final int E = 4;
    private static final int W = 8;
    private static final Map<Integer, Integer> DX = Map.of(E, 1, W, -1, N, 0, S, 0);
    private static final Map<Integer, Integer> DY = Map.of(E, 0, W, 0, N, -1, S, 1);
    private static final Map<Integer, Integer> OPPOSITE = Map.of(E, W, W, E, N, S, S, N);

    private Generator() {}

    public static Maze generate(int height, int width) {
        if (height <= 1 && width <= 1) {
            return null;
        }
        var maze = new Maze(width, height);
        var seed = new Random().nextInt();
        var rand = new Random(seed);

        var x = rand.nextInt(width);
        var y = rand.nextInt(height);
        var remaining = width * height - 1;

        while (remaining > 0) {
            var directions = Arrays.asList(N, S, E, W);
            Collections.shuffle(directions);

            for (var dir : directions) {
                var nx = x + DX.get(dir);
                var ny = y + DY.get(dir);
                if (nx >= 0 && ny >= 0 && nx < width && ny < height) {
                    if (maze.grid[ny][nx] == 0) {
                        maze.grid[y][x] |= dir;
                        maze.grid[ny][nx] |= OPPOSITE.get(dir);
                        remaining--;
                    }
                    x = nx;
                    y = ny;
                    break;
                }
            }
        }
        return maze;
    }
}
