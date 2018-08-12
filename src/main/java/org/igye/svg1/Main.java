package org.igye.svg1;

import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.igye.svg1.Constants.STANDARD_EX;

public class Main {
    public static void main(String[] args) throws IOException {
        Scene1 scene1 = new Scene1();
        StringBuilder sb = new StringBuilder();
        Serializer serializer = new Serializer();
        serializer.writeSvg(sb, 500, 500, scene1.scene1(STANDARD_EX.scale(5).moveX(30).moveY(50)));
        IOUtils.write(
                sb.toString(),
                new FileOutputStream("D:/programs/js/svg-learn/svg-scene1.html"),
                Charset.forName("UTF-8")
        );
    }
}
