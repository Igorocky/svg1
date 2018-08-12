package org.igye.svg1;

import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.igye.svg1.SvgUtils.STANDARD_EX;

public class Main {
    public static void main(String[] args) throws IOException {

        Vector ex = STANDARD_EX.scale(40);
        double minX = -3;
        double maxX = 10;
        double minY = -2;
        double maxY = 5;
        write(
                ex.scale(maxX - minX + 2).length(), ex.scale(maxY - minY + 2).length(),
                asList(
//                () -> Scenes.scene1(STANDARD_EX.scale(5).moveX(30).moveY(50).rotate(toRadians(10))),
                () -> Scenes.coordinates(
                        ex
                                .moveX(ex.scale(1 + Math.abs(minX)).length())
                                .moveY(ex.scale(1 + Math.abs(minY)).length()),
                        minX, maxX, minY, maxY
                        )
//                () -> Scenes.lines(STANDARD_EX.scale(50), 100.0, 4)
        ));
    }

    private static void write(double width, double height, List<Supplier<List<SvgElem>>> sceneBuilders) throws IOException {
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
