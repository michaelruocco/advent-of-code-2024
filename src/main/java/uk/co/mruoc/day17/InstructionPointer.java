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

    public int[] get() {
        int[] next = new int[2];
        next[0] = program[pointer];
        next[1] = program[pointer + 1];
        pointer += 2;
        return next;
    }

    public void jump(int operand) {
        pointer = operand;
    }
}
