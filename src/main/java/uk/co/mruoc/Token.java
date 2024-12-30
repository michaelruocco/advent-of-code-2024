package uk.co.mruoc;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Token {

    public static final char WALL = '#';
    public static final char START = 'S';
    public static final char END = 'E';
    public static final char FREE = '.';

    public static boolean notWall(char token) {
        return WALL != token;
    }

    public static boolean isStart(char token) {
        return START == token;
    }

    public static boolean isEnd(char token) {
        return END == token;
    }
}
