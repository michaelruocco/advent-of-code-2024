package uk.co.mruoc.day6;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Step {

    final Location location;
    final Direction direction;
}
