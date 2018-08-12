package org.igye.svg1;

import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.igye.svg1.SvgUtils.*;

public class Scenes {
    public static List<SvgElem> scene1(Vector ex) {
        List<SvgElem> res = new LinkedList<>();
        Vector line1 = ex.scale(10);
        Vector line2 = ex.scale(3).move(line1.getEnd()).rotate(ANG_180 + ANG_30);
        Vector line3 = line2.rotate(-ANG_60);

        res.add(Line.builder().vector(line1).build());
        res.add(Line.builder().vector(line2).build());
        res.add(Line.builder().vector(line3).build());
        return res;
    }

    public static List<Line> lines(Vector ex, double width, double height) {
        List<Line> res = new LinkedList<>();
        Style mainStyle = Style.builder()
                .stroke("black")
                .strokeWidth(1.0)
                .build();
        Style secondaryStyle = mainStyle
                .withStrokeDasharray(asList(ex.length() * 0.1))
                .withStrokeWidth(0.5*mainStyle.getStrokeWidth());
        Vector vVector = ex.rotate(ANG_90).scale(height);
        res.add(Line.builder().vector(vVector).style(mainStyle).build());
        vVector = vVector.move(ex);
        double dist = ex.length();
        double step = ex.length();
        double widthPx = ex.scale(width).length();
        while (dist <= widthPx) {
            res.add(Line.builder().vector(vVector).style(secondaryStyle).build());
            vVector = vVector.move(ex);
            dist += step;
        }
        return res;
    }

    public static List<SvgElem> coordinates(Vector ex, double minX, double maxX, double minY, double maxY) {
        List<SvgElem> res = new LinkedList<>();
        res.addAll(lines(ex, maxX, maxY));
        res.addAll(lines(ex, maxX, minY));
        res.addAll(lines(ex.rotate(ANG_180), -minX, -minY));
        res.addAll(lines(ex.rotate(ANG_180), -minX, -maxY));

        res.addAll(lines(ex.rotate(ANG_90), maxY, -minX));
        res.addAll(lines(ex.rotate(ANG_90), maxY, -maxX));
        res.addAll(lines(ex.rotate(-ANG_90), -minY, maxX));
        res.addAll(lines(ex.rotate(-ANG_90), -minY, minX));
        res.add(
                Rect.builder()
                        .leftTopCorner(ex.rotate(ANG_90).scale(maxY).move(ex.scale(minX)).getEnd())
                        .width(ex.scale(maxX - minX).length())
                        .height(ex.scale(maxY - minY).length())
                        .build()
        );
        return res;
    }
}
