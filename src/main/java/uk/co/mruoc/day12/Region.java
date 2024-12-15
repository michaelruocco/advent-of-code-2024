package uk.co.mruoc.day12;

import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class Region {

    private final char plant;
    private final Collection<Plot> plots;

    public Region(char plant) {
        this(plant, new ArrayList<>());
    }

    public int getArea() {
        return plots.size();
    }

    public int getPerimeter() {
        return plots.stream().mapToInt(plot -> plot.calculatePerimeter(plots)).sum();
    }

    public int getSides() {
        return plots.stream().mapToInt(plot -> plot.calculateCorners(plots)).sum();
    }

    public int getFencePrice() {
        return getArea() * getPerimeter();
    }

    public int getDiscountedFencePrice() {
        return getArea() * getSides();
    }

    public void add(Plot plot) {
        plots.add(plot);
    }
}
