package uk.co.mruoc.day6;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class Step {

    private final LabMap.Point location;
    private final char direction;
}
