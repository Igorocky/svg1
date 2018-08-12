package org.igye.svg1;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Circle implements SvgElem {
    private Point center;
    private Double radius;
}
