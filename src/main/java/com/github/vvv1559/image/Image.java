package com.github.vvv1559.image;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Image {
    private final List<ImageRow> rows = new ArrayList<>();

    public Image() {
    }

    protected Image(Image fromImage) {
        rows.addAll(fromImage.rows);
    }

    public void addRow(String row) {
        rows.add(new ImageRow(row));
    }

    public void addRow(char[] row) {
        rows.add(new ImageRow(row));
    }

    public void addRow(ImageRow imageRow) {
        rows.add(imageRow);
    }

    public ImageRow getRowAt(int index) {
        return rows.get(index);
    }

    public int height() {
        return rows.size();
    }

    public int width() {
        if (rows.size() == 0) {
            return 0;
        }

        return rows.get(0).getRowLength();
    }

    @Override
    public String toString() {
        return rows.stream().map(ImageRow::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image1 = (Image) o;
        return Objects.equals(rows, image1.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows);
    }
}
