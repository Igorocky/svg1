package org.igye.svg1;


import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Builder
public class Circle implements SvgElem {
    @Wither
    private Point center;
    private Double radius;

    @Override
    public SvgElem flipY(double height) {
        return withCenter(center.flipY(height));
    }
}
