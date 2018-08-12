package org.igye.svg1;

import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.lang.Math.PI;

public class SvgUtils {
    public static double ANG_30 = PI / 6.0;
    public static double ANG_60 = PI / 3.0;
    public static double ANG_90 = PI / 2.0;
    public static double ANG_180 = PI;

    public static Vector STANDARD_EX = new Vector(new Point(0.0, 0.0), new Point(1.0,0.0));

    public static void write(double width, double height, List<Supplier<List<SvgElem>>> sceneBuilders) throws IOException {
        List<SvgElem> elems = sceneBuilders.stream()
                .flatMap(sceneBuilder -> sceneBuilder.get().stream())
                .collect(Collectors.toList());
        Serializer serializer = new Serializer();
        StringBuilder sb = new StringBuilder();
        serializer.writeSvg(sb, (int) width, (int) height, elems);
        IOUtils.write(
                sb.toString(),
                new FileOutputStream("D:/programs/js/svg-learn/svg-scene1.html"),
                Charset.forName("UTF-8")
        );
    }
}
