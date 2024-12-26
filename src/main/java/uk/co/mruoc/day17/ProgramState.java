package uk.co.mruoc.day17;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProgramState {

    private final Pointer pointer;
    private final Registry registry;
    private final Output output;
}
