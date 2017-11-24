package com.github.vvv1559.image;

import java.util.Arrays;

public class ImageRow {
    private final char[] data;

    public ImageRow(char[] data) {
        this.data = data;
    }

    public ImageRow(String data) {
        this.data = data.toCharArray();
    }

    public ImageRow subData(int from, int to) {
        return new ImageRow(Arrays.copyOfRange(data, from, Math.min(to + 1, data.length)));
    }

    public int getRowLength() {
        return data.length;
    }

    public char getDataAt(int index) {
        return data[index];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageRow imageRow = (ImageRow) o;
        return Arrays.equals(data, imageRow.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override
    public String toString() {
        return new String(data);
    }
}
