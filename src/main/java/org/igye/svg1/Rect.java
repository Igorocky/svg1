package org.igye.svg1;


import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Builder
public class Rect implements SvgElem {
    @Wither
    private Point leftTopCorner;
    private Double width;
    private Double height;

    @Override
    public SvgElem flipY(double height) {
        return withLeftTopCorner(leftTopCorner.flipY(height));
    }
}
