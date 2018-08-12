package org.igye.svg1;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.util.Arrays.asList;
import static org.igye.svg1.SvgUtils.STANDARD_EX;

public class Main {
    public static void main(String[] args) throws IOException {
//        coordinates();
        circles();
    }

    private static void coordinates() throws IOException {
        Vector ex = STANDARD_EX.scale(40);
        double minX = -3;
        double maxX = 10;
        double minY = -2;
        double maxY = 5;
        SvgUtils.write(
                ex.scale(maxX - minX + 2).length(), ex.scale(maxY - minY + 2).length(),
                asList(
                        () -> Scenes.coordinates(
                                ex
                                        .moveX(ex.scale(1 + Math.abs(minX)).length())
                                        .moveY(ex.scale(1 + Math.abs(minY)).length()),
                                minX, maxX, minY, maxY
                        )
                ));
    }

    private static void circles() throws IOException {
        BufferedImage img = ImageIO.read(new File("D:\\temp\\face2.jpg"));
        double width = img.getWidth();
        double height = img.getHeight();
        double[][] greyScale = greyScale(img);
        flipY(greyScale);
        int num = 50;
        Vector ex = STANDARD_EX.scale(0.8);
        SvgUtils.write(
                width*ex.length(), height*ex.length(),
                asList(
                        () -> Scenes.circles(
                                ex, width, height, num, (c,r) -> getRadius(greyScale, num, c, r)
                        )
                )
        );
    }

    private static double getRadius(double[][] greyScale, int num, int col, int row) {
        int width = greyScale.length / num;
        int startX = width*col;
        int endX = width*(col+1);
        int startY = width*row;
        int endY = width*(row+1);
        double sum = 0.0;
        int cnt = 0;
        for (int x = startX; x <= endX; x++) {
            if (greyScale.length > x) {
                for (int y = startY; y <= endY; y++) {
                    if (greyScale[x].length > y) {
                        sum += greyScale[x][y];
                        cnt++;
                    }
                }
            }
        }
        return sum/cnt;
    }

    private static void flipY(double[][] greyScale) {
        int width = greyScale.length;
        int height = greyScale[0].length;
        int halfHeight = height/2;
        for (int x = 0; x < greyScale.length; x++) {
            for (int y = 0; y <= halfHeight; y++) {
                double tmp = greyScale[x][y];
                int corr = height - y - 1;
                greyScale[x][y] = greyScale[x][corr];
                greyScale[x][corr] = tmp;
            }
        }
    }

    private static double[][] greyScale(BufferedImage img) {
        double[][] res = new double[img.getWidth()][img.getHeight()];
        double min = 2;
        double max = -1;
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                int rgb = img.getRGB(x, y);
                res[x][y] = 0.2126 * red(rgb) + 0.7152 * green(rgb) + 0.0722 * blue(rgb);
                if (res[x][y] < min) {
                    min = res[x][y];
                }
                if (res[x][y] > max) {
                    max = res[x][y];
                }
            }
        }
        double delta = max - min;
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                res[x][y] = 1.0 - (res[x][y] - min) / delta;
            }
        }
        return res;
    }

    private static int colorComponent(int rgb, int shift) {
        return (rgb >> shift) & 0xFF;
    }

    private static int red(int rgb) {
        return colorComponent(rgb, 16);
    }
    private static int green(int rgb) {
        return colorComponent(rgb, 8);
    }
    private static int blue(int rgb) {
        return colorComponent(rgb, 0);
    }

}
