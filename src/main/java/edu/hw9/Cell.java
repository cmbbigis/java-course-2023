package edu.hw9;

public record Cell(int row, int col, Type type) {
    public enum Type { WALL, FREE, PATH }
}
