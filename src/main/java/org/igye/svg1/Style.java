package org.igye.svg1;


import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Value
@Builder
public class Style {
    @Wither
    private String stroke;
    @Wither
    private Double strokeWidth;
    @Wither
    private List<Double> strokeDasharray;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (stroke != null) {
            sb.append("stroke:").append(stroke).append(";");
        }
        if (strokeWidth != null) {
            sb.append("stroke-width:").append(strokeWidth).append(";");
        }
        if (strokeDasharray != null && !strokeDasharray.isEmpty()) {
            sb.append("stroke-dasharray:").append(StringUtils.join(strokeDasharray, ' ')).append(";");
        }
        return sb.toString();
    }
}
