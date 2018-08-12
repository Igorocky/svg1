package org.igye.svg1;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Line implements SvgElem {
    private Vector vector;
}
