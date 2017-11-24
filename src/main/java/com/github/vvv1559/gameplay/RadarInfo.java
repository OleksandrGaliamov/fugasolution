package com.github.vvv1559.gameplay;

import com.github.vvv1559.image.Image;
import com.github.vvv1559.image.SelectionRange;

public class RadarInfo extends Image {

    public RadarInfo(Image fromImage) {
        super(fromImage);
    }

    public Image subImage(SelectionRange range) {
        Image newImage = new Image();

        for (int i = range.leftTopCornerY(); i <= range.rightBottomY() && i < this.height(); i++) {
            newImage.addRow(getRowAt(i).subData(range.leftTopX(), range.rightBottomX()));
        }

        return newImage;
    }
}
