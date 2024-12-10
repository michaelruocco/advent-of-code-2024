package uk.co.mruoc.day6;

import uk.co.mruoc.GridLoader;

public class LabMapLoader {

    public LabMap loadMap(String path) {
        return new LabMap(GridLoader.load(path));
    }
}
