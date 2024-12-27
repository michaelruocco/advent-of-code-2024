package uk.co.mruoc.day17;

import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Program {

    private final Map<Integer, Instruction> instructions;

    public Program() {
        this(new Instructions().asMap());
    }

    public ProgramState execute(ProgramState originalState) {
        ProgramState state = originalState;
        while (!state.atEnd()) {
            int[] next = state.getNext();
            state = state.movePointer();
            int opCode = next[0];
            Instruction instruction = instructions.get(opCode);
            int literalOperand = next[1];
            state = instruction.execute(literalOperand, state);
        }
        return state;
    }
}
