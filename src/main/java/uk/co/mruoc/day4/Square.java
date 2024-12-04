package uk.co.mruoc.day4;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
public class Square {
    public final char token;
    public final Point point;
}
