package uk.co.mruoc.day17;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.With;

@RequiredArgsConstructor
@Getter
public class Pointer {

    private final int[] program;

    @With
    private final int ptr;

    public Pointer(int... program) {
        this(program, 0);
    }

    public int getProgramLength() {
        return program.length;
    }

    public long getProgramValueAt(int index) {
        return program[index];
    }

    public boolean atEnd() {
        return ptr >= program.length - 1;
    }

    public int[] get() {
        int[] next = new int[2];
        next[0] = program[ptr];
        next[1] = program[ptr + 1];
        return next;
    }

    public Pointer next() {
        return withPtr(ptr + 2);
    }

    public Pointer jump(int operand) {
        return withPtr(operand);
    }
}
