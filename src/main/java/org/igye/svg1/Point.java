package org.igye.svg1;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Point {
    private double x;
    private double y;

    public Point move(Vector vector) {
        double dx = vector.getEnd().getX() - vector.getStart().getX();
        double dy = vector.getEnd().getY() - vector.getStart().getY();
        return Point.builder().x(x + dx).y(y + dy).build();
    }

    public Point moveX(double dx) {
        return Point.builder().x(x + dx).y(y).build();
    }

    public Point moveY(double dy) {
        return Point.builder().x(x).y(y + dy).build();
    }
}
