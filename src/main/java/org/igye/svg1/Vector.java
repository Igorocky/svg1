package org.igye.svg1;

import lombok.Builder;
import lombok.Value;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

@Value
@Builder
public class Vector {
    private Point start;
    private Point end;

    public Vector(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Vector rotate(Double angle) {
        double x = end.getX() - start.getX();
        double y = end.getY() - start.getY();
        return Vector.builder()
                .start(
                        Point.builder().x(start.getX()).y(start.getY()).build()

                )
                .end(
                        Point.builder()
                                .x(x*cos(angle) - y*sin(angle) + start.getX())
                                .y(x*sin(angle) + y*cos(angle) + start.getY())
                                .build()
                )
                .build();
    }

    public Vector move(Vector vector) {
        return Vector.builder()
                .start(start.move(vector))
                .end(end.move(vector))
                .build();
    }

    public Vector move(Point point) {
        return move(new Vector(start, point));
    }

    public Vector moveX(double dx) {
        return new Vector(start.moveX(dx), end.moveX(dx));
    }

    public Vector moveY(double dy) {
        return new Vector(start.moveY(dy), end.moveY(dy));
    }

    public Vector scale(double factor) {
        double dx = (end.getX() - start.getX()) * factor;
        double dy = (end.getY() - start.getY()) * factor;
        return Vector.builder()
                .start(start)
                .end(Point.builder().x(start.getX() + dx).y(start.getY() + dy).build())
                .build();

    }

    public Vector flipY(double height) {
        return new Vector(start.flipY(height), end.flipY(height));
    }

    public double length() {
        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }

}
