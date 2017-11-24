package com.github.vvv1559;

import com.github.vvv1559.detector.DetectionResult;
import com.github.vvv1559.detector.InvaderDetector;
import com.github.vvv1559.gameplay.Invader;
import com.github.vvv1559.gameplay.RadarInfo;
import com.github.vvv1559.image.Image;
import com.github.vvv1559.image.SelectionRange;
import com.github.vvv1559.reader.ImageReader;

import java.util.Collection;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("******WELCOME!******");
        System.out.println("Input radar image:");

        ImageReader imageReader = ImageReader.newConsoleImageReader();
        Image image = imageReader.readImage();

        System.out.println("Image loaded");
        System.out.println("Scanning image...");

        InvaderDetector invaderDetector = new InvaderDetector();
        DetectionResult detectionResult = invaderDetector.detectInvadersOnMap(new RadarInfo(image));
        if (detectionResult.imageHasInvaders()) {
            System.out.println("======WARNING!!!=======");
            System.out.println("Invaders detected:");
            for (Map.Entry<Invader, Collection<SelectionRange>> entry : detectionResult.getDetectionsMap().entrySet()) {
                System.out.println("************************");
                System.out.print("==>In ranges:");
                entry.getValue().forEach(System.out::println);
                System.out.println("==>Invader:");
                System.out.println(entry.getKey());
                System.out.println("************************");

            }
        } else {
            System.out.println("Image is clear. Fuuuuuh...");
        }
    }
}
