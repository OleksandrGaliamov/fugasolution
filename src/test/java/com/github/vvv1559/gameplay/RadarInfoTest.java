package com.github.vvv1559.gameplay;

import com.github.vvv1559.image.Image;
import com.github.vvv1559.image.SelectionRange;
import org.junit.Assert;
import org.junit.Test;

public class RadarInfoTest {

    @Test
    public void testSubImage() throws Exception {
        Image image = new Image();
        image.addRow("-----");
        image.addRow("-o-o-");
        image.addRow("--o--");
        image.addRow("-o-o-");
        image.addRow("-----");

        SelectionRange selectionRange = new SelectionRange(1, 1, 3, 3);
        Image expected = new Image();
        expected.addRow("o-o");
        expected.addRow("-o-");
        expected.addRow("o-o");

        Image actual = new RadarInfo(image).subImage(selectionRange);
        Assert.assertEquals(expected, actual);
    }
}