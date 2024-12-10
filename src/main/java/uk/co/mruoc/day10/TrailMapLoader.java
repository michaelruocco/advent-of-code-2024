package uk.co.mruoc.day10;

import uk.co.mruoc.GridLoader;

public class TrailMapLoader {

    public TrailMap load(String path) {
        return new TrailMap(GridLoader.load(path));
    }
}
