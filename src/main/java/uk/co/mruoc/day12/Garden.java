package uk.co.mruoc.day12;

import java.util.Collection;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Garden {

    @Getter
    private final Collection<Region> regions;

    public int getFencePrice() {
        return regions.stream().mapToInt(Region::getFencePrice).sum();
    }

    public int getDiscountedPrice() {
        return regions.stream().mapToInt(Region::getDiscountedFencePrice).sum();
    }

    public Collection<Integer> getRegionAreas(char plant) {
        return getRegions(plant).map(Region::getArea).toList();
    }

    public Collection<Integer> getRegionPerimeters(char plant) {
        return getRegions(plant).map(Region::getPerimeter).toList();
    }

    public Collection<Integer> getRegionSides(char plant) {
        return getRegions(plant).map(Region::getSides).toList();
    }

    private Stream<Region> getRegions(char plant) {
        return regions.stream().filter(region -> region.getPlant() == plant);
    }
}
