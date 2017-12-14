package com.github.vvv1559.detector;

import com.github.vvv1559.gameplay.RadarInfo;
import com.github.vvv1559.image.Image;
import com.github.vvv1559.image.SelectionRange;
import com.github.vvv1559.reader.ImageReader;
import com.google.common.io.Resources;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class InvaderDetectorTest {
    @Test
    public void testDetectInvadersOnMap() throws Exception {
        Assert.assertTrue(getDetectionResult("test-map").imageHasInvaders());
        Assert.assertTrue(getDetectionResult("map-with-invaders-and-noise").imageHasInvaders());
        Assert.assertFalse(getDetectionResult("map-without-invaders").imageHasInvaders());

        DetectionResult positiveDetection = getDetectionResult("map-with-invaders");
        SelectionRange firstDetection = new SelectionRange(1, 0, 11, 8);
        SelectionRange secondDetection = new SelectionRange(12, 8, 8, 8);
        Assert.assertTrue(positiveDetection.imageHasInvaders());

        List<SelectionRange> selectionRanges = positiveDetection.getDetectionsMap().values().stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        Assert.assertEquals(2, selectionRanges.size());
        Assert.assertTrue(selectionRanges.contains(firstDetection));
        Assert.assertTrue(selectionRanges.contains(secondDetection));
    }

    private DetectionResult getDetectionResult(String resourceName) throws Exception {
        File testMap = new File(Resources.getResource(resourceName).toURI());
        RadarInfo radarInfo = new RadarInfo(ImageReader.newFileImageReader(testMap).readImage());
        InvaderDetector invaderDetector = new InvaderDetector();
        return invaderDetector.detectInvadersOnMap(radarInfo);
    }

    @Test
    public void imageMatchesPattern() throws Exception {
        Image image = new Image();
        image.addRow("ooo");
        image.addRow("-o-");
        image.addRow("ooo");

        Assert.assertTrue(InvaderDetector.imageMatchesPattern(image, getPattern()));
    }

    @Test
    public void imageWithNoiseMatchesPattern() throws Exception {
        Image image = new Image();
        image.addRow("ooo");
        image.addRow("oo-");
        image.addRow("ooo");

        Assert.assertTrue(InvaderDetector.imageMatchesPattern(image, getPattern()));
    }

    @Test
    public void imageWithNegativeNoiseMatchesPattern() throws Exception {
        Image image = new Image();
        image.addRow("o-o");
        image.addRow("-o-");
        image.addRow("ooo");

        Assert.assertTrue(InvaderDetector.imageMatchesPattern(image, getPattern()));
    }

    @Test
    public void imageNotMatchesPattern() throws Exception {
        Image image = new Image();
        image.addRow("o--");
        image.addRow("-o-");
        image.addRow("-oo");

        Assert.assertFalse(InvaderDetector.imageMatchesPattern(image, getPattern()));
    }

    private Image getPattern() {
        Image pattern = new Image();
        pattern.addRow("ooo");
        pattern.addRow("-o-");
        pattern.addRow("ooo");

        return pattern;
    }


}