package uk.co.mruoc.day17;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InstructionPointer {

    private final int[] program;
    private int pointer;

    public InstructionPointer(int... program) {
        this(program, 0);
    }

    public boolean atEnd() {
        return pointer >= program.length - 1;
    }

    public int get() {
        return program[pointer++];
    }

    public void jumpTo(int literalOperand) {}
}
