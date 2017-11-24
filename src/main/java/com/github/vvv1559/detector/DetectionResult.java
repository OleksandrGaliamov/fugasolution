package com.github.vvv1559.detector;

import com.github.vvv1559.gameplay.Invader;
import com.github.vvv1559.image.SelectionRange;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.Map;

public class DetectionResult {
    private Multimap<Invader, SelectionRange> detectedInvaders = ArrayListMultimap.create();

    DetectionResult() {
    }

    boolean dotDetected(int x, int y) {
        return detectedInvaders.values().stream().anyMatch(sr -> sr.pointInRange(x, y));
    }

    void addDetection(Invader invader, SelectionRange selectionRange) {
        detectedInvaders.put(invader, selectionRange);
    }

    public boolean imageHasInvaders() {
        return !detectedInvaders.isEmpty();
    }

    public Map<Invader, Collection<SelectionRange>> getDetectionsMap() {
        return detectedInvaders.asMap();
    }
}
