package org.igye.svg1;

import java.util.LinkedList;
import java.util.List;

import static org.igye.svg1.Constants.*;

public class Scene1 {
    public List<SvgElem> scene1(Vector ex) {
        List<SvgElem> res = new LinkedList<>();
        Vector line1 = ex.scale(10);
        Vector line2 = ex.scale(3).move(line1.getEnd()).rotate(ANG_180 + ANG_30);
        Vector line3 = line2.rotate(-ANG_60);

        res.add(new Line(line1));
        res.add(new Line(line2));
        res.add(new Line(line3));
        return res;
    }
}
