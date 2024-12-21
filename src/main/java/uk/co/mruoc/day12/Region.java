package uk.co.mruoc.day12;

import static uk.co.mruoc.day12.CornerCalculator.calculateCorners;
import static uk.co.mruoc.day12.PerimeterCalculator.calculatePerimeter;

import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
@Getter
@ToString
public class Region {

    private final char plant;
    private final Collection<Point> plots;

    public Region(char plant) {
        this(plant, new ArrayList<>());
    }

    public int getArea() {
        return plots.size();
    }

    public int getPerimeter() {
        return plots.stream().mapToInt(plot -> calculatePerimeter(plot, plots)).sum();
    }

    public int getSides() {
        return plots.stream().mapToInt(plot -> calculateCorners(plot, plots)).sum();
    }

    public int getFencePrice() {
        return getArea() * getPerimeter();
    }

    public int getDiscountedFencePrice() {
        return getArea() * getSides();
    }

    public void add(Point plot) {
        plots.add(plot);
    }
}
