package uk.co.mruoc.day15;

public interface WarehouseMapScalePolicy {

    int getXScale();

    void move(WarehouseMap map, Direction direction);

    char getToken(WarehouseMap map, Point point);
}
