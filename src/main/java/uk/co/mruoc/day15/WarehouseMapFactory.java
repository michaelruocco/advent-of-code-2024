package uk.co.mruoc.day15;

import java.util.List;

public class WarehouseMapFactory {

    public WarehouseMap build(List<String> lines, int xScale) {
        if (xScale == 2) {
            return new XScale2WarehouseMap(lines);
        }
        return new DefaultWarehouseMap(lines);
    }
}
