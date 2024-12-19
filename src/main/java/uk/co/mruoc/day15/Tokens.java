package uk.co.mruoc.day15;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Tokens {
    public static final char WALL = '#';
    public static final char BOX = 'O';
    public static final char ROBOT = '@';
    public static final char WEST_BOX = '[';
    public static final char EAST_BOX = ']';
    public static final char EMPTY = '.';
}
