package edu.project2;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Renderer {
    private final static Logger LOGGER = LogManager.getLogger();

    private Renderer() {}

    public static void render(Maze maze) {
        var result = maze.toNormalView();
        var output = new StringBuilder();
        output.append("■".repeat(result[0].length + 1));
        output.append("\n");
        for (var y = 0; y < result.length; y++) {
            output.append("■");
            for (var x = 0; x < result[0].length; x++) {
                output.append(result[y][x] != null && result[y][x].type() == Cell.Type.FREE ? "□" : "■");
            }
            output.append("\n");
        }
        LOGGER.trace("\n" + output);
    }

    public static void render(Maze maze, List<Coordinate> path) {
        var result = maze.toNormalView();
        for (Coordinate coordinate : path) {
            result[coordinate.row()][coordinate.col()] = new Cell(coordinate.row(), coordinate.col(), Cell.Type.PATH);
        }
        var output = new StringBuilder();
        output.append("■".repeat(result[0].length + 1));
        output.append("\n");
        for (var y = 0; y < result.length; y++) {
            output.append("■");
            for (var x = 0; x < result[0].length; x++) {
                if (result[y][x] != null && result[y][x].type() == Cell.Type.PATH) {
                    output.append("⊡");
                } else {
                    output.append(result[y][x] != null && result[y][x].type() == Cell.Type.FREE ? "□" : "■");
                }
            }
            output.append("\n");
        }
        LOGGER.trace("\n" + output);
    }
}
