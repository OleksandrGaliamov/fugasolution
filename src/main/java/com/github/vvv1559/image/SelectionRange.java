package com.github.vvv1559.image;

import java.util.Objects;

public class SelectionRange {
    private final int leftTopX;
    private final int leftTopY;
    private final int height;
    private final int width;

    public SelectionRange(int leftTopX, int leftTopY, int width, int height) {
        this.leftTopX = leftTopX;
        this.leftTopY = leftTopY;
        this.height = height;
        this.width = width;
    }

    public boolean pointInRange(int x, int y) {
        return x >= leftTopX && x <= rightBottomX() && y >= leftTopY && y <= rightBottomY();
    }

    public int rightBottomY() {
        return leftTopY + height - 1;
    }

    public int rightBottomX() {
        return leftTopX + width - 1;
    }

    public int leftTopX() {
        return leftTopX;
    }

    public int leftTopCornerY() {
        return leftTopY;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public String toString() {
        return "[" +
            "(" + leftTopX + ", " + leftTopY + ")," +
            "(" + rightBottomX() + "," + rightBottomY() + ")" +
            "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectionRange that = (SelectionRange) o;
        return leftTopX == that.leftTopX &&
            leftTopY == that.leftTopY &&
            height == that.height &&
            width == that.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftTopX, leftTopY, height, width);
    }
}
