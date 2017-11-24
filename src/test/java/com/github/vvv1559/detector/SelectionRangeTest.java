package com.github.vvv1559.detector;

import com.github.vvv1559.image.SelectionRange;
import org.junit.Assert;
import org.junit.Test;

public class SelectionRangeTest {

    @Test
    public void testEvaluatedDots() throws Exception {
        SelectionRange selectionRange = new SelectionRange(1, 2, 8, 12);
        Assert.assertEquals(8, selectionRange.rightBottomX());
        Assert.assertEquals(13, selectionRange.rightBottomY());

    }

    @Test
    public void testPointInRange() throws Exception {
        SelectionRange selectionRange = new SelectionRange(10, 10, 10, 10);
        Assert.assertTrue(selectionRange.pointInRange(15, 15));
        Assert.assertTrue(selectionRange.pointInRange(10, 10));
        Assert.assertTrue(selectionRange.pointInRange(10, 15));
        Assert.assertFalse(selectionRange.pointInRange(5, 15));
        Assert.assertFalse(selectionRange.pointInRange(15, 5));
        Assert.assertFalse(selectionRange.pointInRange(5, 5));
        Assert.assertFalse(selectionRange.pointInRange(25, 25));
    }

}