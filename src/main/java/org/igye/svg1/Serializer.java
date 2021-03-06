package org.igye.svg1;

import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.asList;

public class Serializer {
    public void writeSvg(StringBuilder sb, int width, int height, List<SvgElem> svgElems) {
        sb.append("<svg height=\"" + height + "\" width=\"" + width + "\">");
        svgElems.stream().map(elem -> elem.flipY(height)).forEach(svgElem -> {
            sb.append("\n    ");
            writeElem(sb, svgElem);
        });
        sb.append("\n</svg>");
    }

    private void writeElem(StringBuilder sb, SvgElem svgElem) {
        if (svgElem instanceof Circle) {
            writeCircle(sb, (Circle) svgElem);
        } else if (svgElem instanceof Line) {
            writeLine(sb, (Line) svgElem);
        } else if (svgElem instanceof Rect) {
            writeRect(sb, (Rect) svgElem);
        }
    }

    private void writeCircle(StringBuilder sb, Circle circle) {
        putElem(
                sb,
                "circle",
                asList(
                        attr("cx", circle.getCenter().getX()),
                        attr("cy", circle.getCenter().getY()),
                        attr("r", circle.getRadius()),
                        attr("style", circle.getStyle())
                )
        );
    }

    private void writeLine(StringBuilder sb, Line line) {
        putElem(
                sb,
                "line",
                asList(
                        attr("x1", line.getVector().getStart().getX()),
                        attr("y1", line.getVector().getStart().getY()),
                        attr("x2", line.getVector().getEnd().getX()),
                        attr("y2", line.getVector().getEnd().getY()),
                        attr("style", line.getStyle())
                )
        );
    }

    private void writeRect(StringBuilder sb, Rect rect) {
        putElem(
                sb,
                "rect",
                asList(
                        attr("x", rect.getLeftTopCorner().getX()),
                        attr("y", rect.getLeftTopCorner().getY()),
                        attr("width", rect.getWidth()),
                        attr("height", rect.getHeight()),
                        attr("style", "fill:blue;stroke:pink;stroke-width:5;fill-opacity:0.1")
                )
        );
    }

    private Consumer<StringBuilder> attr(String attrName, Object value) {
        return sb -> {
            if (value != null) {
                sb.append(attrName).append("=\"").append(value).append("\"");
            }
        };
    }

    private void putElem(StringBuilder sb, String tagName, List<Consumer<StringBuilder>> attrWriters) {
        sb.append("<").append(tagName);
        attrWriters.forEach(attrWriter -> attrWriter.accept(sb.append(" ")));
        sb.append("/>");
    }
}
