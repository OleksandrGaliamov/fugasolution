package com.github.vvv1559.detector;

import com.github.vvv1559.gameplay.Invader;
import com.github.vvv1559.gameplay.RadarInfo;
import com.github.vvv1559.image.Image;
import com.github.vvv1559.image.SelectionRange;
import com.github.vvv1559.reader.ImageReader;
import com.google.common.io.Resources;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class InvaderDetector {
    private static final int MATCH_DETECTION_PERCENT = 90;

    private final Set<Invader> invaders;

    public InvaderDetector() {
        invaders = readDefaultInvaders();
    }

    private Set<Invader> readDefaultInvaders() {
        Set<Invader> result = new HashSet<>();

        try {
            File invadersPath = new File(Resources.getResource("invaders").toURI());
            File[] patterns = invadersPath.listFiles();
            if (patterns == null) {
                throw new IllegalStateException("Invader patterns not found");
            }

            for (File pattern : patterns) {
                ImageReader imageReader = ImageReader.newFileImageReader(pattern);
                result.add(new Invader(imageReader.readImage()));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public DetectionResult detectInvadersOnMap(RadarInfo radarInfo) {
        DetectionResult detectionResult = new DetectionResult();

        for (int y = 0; y < radarInfo.height(); y++) {
            for (int x = 0; x < radarInfo.width(); x++) {
                if (detectionResult.dotDetected(x, y)) {
                    continue;
                }

                for (Invader invader : invaders) {
                    SelectionRange selectionRange = new SelectionRange(x, y, invader.width(), invader.height());

                    Image subImage = radarInfo.subImage(selectionRange);
                    if (imageMatchesPattern(subImage, invader)) {
                        detectionResult.addDetection(invader, selectionRange);
                        break;
                    }
                }
            }
        }

        return detectionResult;
    }

    static boolean imageMatchesPattern(Image image, Image pattern) {
        if (image.height() != pattern.height() || image.width() != pattern.width()) {
            return false;
        }

        int checkedPoints = 0;
        int matchesPoints = 0;
        for (int rowIndex = 0; rowIndex < pattern.height(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < pattern.width(); columnIndex++) {
                char patternSymbol = pattern.getRowAt(rowIndex).getDataAt(columnIndex);
                char imageSymbol = image.getRowAt(rowIndex).getDataAt(columnIndex);

                if (patternSymbol == ImageReader.POINT_SYMBOL) {
                    checkedPoints++;
                    if (imageSymbol == patternSymbol) {
                        matchesPoints++;
                    }
                }
            }
        }
        return matchesPoints >= checkedPoints * MATCH_DETECTION_PERCENT / 100;
    }
}
