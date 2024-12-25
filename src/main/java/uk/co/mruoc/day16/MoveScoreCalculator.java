package uk.co.mruoc.day16;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MoveScoreCalculator {

    public static long toAheadScore(long score) {
        return score + 1;
    }

    public static long toRotateScore(long score) {
        return score + 1000;
    }
}
