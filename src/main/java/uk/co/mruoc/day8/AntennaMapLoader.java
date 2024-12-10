package uk.co.mruoc.day8;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.GridLoader;

@RequiredArgsConstructor
public class AntennaMapLoader {

    private final AntiNodeFinder antiNodeFinder;

    public AntennaMap load(String path) {
        return new AntennaMap(GridLoader.load(path), antiNodeFinder);
    }
}
