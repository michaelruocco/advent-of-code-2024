package uk.co.mruoc.day17;

import java.util.function.Consumer;

public interface Instruction extends Consumer<Integer> {

    int getOpCode();
}
