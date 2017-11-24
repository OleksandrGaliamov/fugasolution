package com.github.vvv1559.reader;

import com.github.vvv1559.image.Image;

import java.io.File;

public interface ImageReader {
    char POINT_SYMBOL = 'o';

    Image readImage() throws Exception;

    static ImageReader newConsoleImageReader() {
        return new ConsoleImageReader();
    }

    static ImageReader newFileImageReader(File file) {
        return new FileImageReader(file);
    }
}
