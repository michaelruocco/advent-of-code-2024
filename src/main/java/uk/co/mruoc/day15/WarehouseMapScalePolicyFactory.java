package uk.co.mruoc.day15;

public class WarehouseMapScalePolicyFactory {

    public WarehouseMapScalePolicy build(int xScale) {
        if (xScale == 2) {
            return new WarehouseMapXScale2Policy();
        }
        return new WarehouseMapXScale1Policy();
    }
}
