package uk.co.mruoc.day12;

import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class Region {

    @Getter
    private final char plant;

    private final Collection<Plot> plots;

    public Region(char plant) {
        this(plant, new ArrayList<>());
    }

    public int getArea() {
        return plots.size();
    }

    public void add(Plot plot) {
        plots.add(plot);
    }
}
