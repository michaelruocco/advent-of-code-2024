package uk.co.mruoc.day12;

import java.util.Collection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Garden {

    @Getter
    private final Collection<Region> regions;

    public Collection<Integer> getRegionAreas(char plant) {
        return regions.stream()
                .filter(region -> region.getPlant() == plant)
                .map(Region::getArea)
                .toList();
    }
}
