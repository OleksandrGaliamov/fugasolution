package com.github.vvv1559.reader;

import com.github.vvv1559.image.Image;

import java.util.Scanner;

class ConsoleImageReader implements ImageReader {

    @Override
    public Image readImage() throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Input number of rows in image");
            int rowsCount = scanner.nextInt();
            System.out.println("Paste image");
            Image image = new Image();
            for (int i = 0; i < rowsCount; i++) {
                image.addRow(scanner.next());
            }

            return image;
        }
    }
}
