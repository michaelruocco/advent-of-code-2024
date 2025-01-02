package uk.co.mruoc.day24;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WireConverter {

    public static char toWireId(String wire) {
        return wire.charAt(0);
    }
}
