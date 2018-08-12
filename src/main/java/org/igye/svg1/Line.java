package org.igye.svg1;


import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Builder
public class Line implements SvgElem {
    @Wither
    private Vector vector;
    @Wither
    private Style style;

    @Override
    public SvgElem flipY(double height) {
        return withVector(vector.flipY(height));
    }
}
