package com.github.vvv1559.reader;


import com.github.vvv1559.image.Image;
import com.google.common.io.Resources;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class FileImageReaderTest {

    @Test
    public void testReadThin() throws Exception {
        ImageReader imageReader = ImageReader.newFileImageReader(new File(Resources.getResource("invaders/thin").toURI()));
        Image image = imageReader.readImage();
        Assert.assertEquals(8, image.width());
        Assert.assertEquals(8, image.height());
    }

    @Test
    public void testReadFat() throws Exception {
        ImageReader imageReader = ImageReader.newFileImageReader(new File(Resources.getResource("invaders/fat").toURI()));
        Image image = imageReader.readImage();
        Assert.assertEquals(11, image.width());
        Assert.assertEquals(8, image.height());
    }
}