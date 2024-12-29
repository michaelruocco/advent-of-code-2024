package uk.co.mruoc;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Point {
    public final int y;
    public final int x;

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Point north() {
        return north(1);
    }

    public Point east() {
        return east(1);
    }

    public Point south() {
        return south(1);
    }

    public Point west() {
        return west(1);
    }

    public Point northEast() {
        return northEast(1);
    }

    public Point northWest() {
        return northWest(1);
    }

    public Point southEast() {
        return southEast(1);
    }

    public Point southWest() {
        return southWest(1);
    }

    public Point north(int times) {
        return new Point(y - times, x);
    }

    public Point east(int times) {
        return new Point(y, x + times);
    }

    public Point south(int times) {
        return new Point(y + times, x);
    }

    public Point west(int times) {
        return new Point(y, x - times);
    }

    public Point northEast(int times) {
        return new Point(y - times, x + times);
    }

    public Point northWest(int times) {
        return new Point(y - times, x - times);
    }

    public Point southEast(int times) {
        return new Point(y + times, x + times);
    }

    public Point southWest(int times) {
        return new Point(y + times, x - times);
    }

    public boolean northOf(Point other) {
        return x == other.x && y - 1 == other.y;
    }

    public boolean eastOf(Point other) {
        return x + 1 == other.x && y == other.y;
    }

    public boolean southOf(Point other) {
        return x == other.x && y + 1 == other.y;
    }

    public boolean westOf(Point other) {
        return x - 1 == other.x && y == other.y;
    }

    public Point getVectorTo(Point other) {
        return new Point(other.y - y, other.x - x);
    }

    public int getManhattanDistance() {
        return Math.abs(x) + Math.abs(y);
    }
}
