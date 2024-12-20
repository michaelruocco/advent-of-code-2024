package uk.co.mruoc.day6;

public class GridLoader {

    public Grid loadGrid(String path) {
        return new Grid(uk.co.mruoc.GridLoader.load(path));
    }
}
