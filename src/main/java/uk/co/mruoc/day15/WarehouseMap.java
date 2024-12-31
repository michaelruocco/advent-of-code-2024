package uk.co.mruoc.day15;

import uk.co.mruoc.Direction;

public interface WarehouseMap {
    void move(Directions directions);

    void move(Direction direction);

    String getState();

    int sumOfAllBoxesGPS();
}
