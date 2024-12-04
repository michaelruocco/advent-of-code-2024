package uk.co.mruoc.day4;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Builder
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Point {
    public final int x;
    public final int y;
}
