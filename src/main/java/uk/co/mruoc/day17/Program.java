package uk.co.mruoc.day17;

import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Program {

    private final Map<Integer, Instruction> instructions;

    public Program() {
        this(new Instructions().asMap());
    }

    public void execute(ProgramState state) {
        Pointer pointer = state.getPointer();
        while (!pointer.atEnd()) {
            int[] next = pointer.get();
            int opCode = next[0];
            Instruction instruction = instructions.get(opCode);
            int literalOperand = next[1];
            instruction.execute(literalOperand, state);
        }
    }
}
