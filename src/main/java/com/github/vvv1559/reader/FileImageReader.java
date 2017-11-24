package com.github.vvv1559.reader;

import com.github.vvv1559.image.Image;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class FileImageReader implements ImageReader {

    private final File file;

    FileImageReader(File file) {
        this.file = file;
    }

    @Override
    public Image readImage() throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Image image = new Image();
            reader.lines().forEach(image::addRow);
            return image;
        }
    }
}
