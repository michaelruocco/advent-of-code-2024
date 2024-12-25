package uk.co.mruoc.day17;

import java.util.Map;
import lombok.Builder;

@Builder
public class Program {

    private final InstructionPointer pointer;
    private final Map<Integer, Instruction> instructions;

    public void execute() {
        while (!pointer.atEnd()) {
            int[] next = pointer.get();
            int opCode = next[0];
            Instruction instruction = instructions.get(opCode);
            int operand = next[1];
            instruction.accept(operand);
        }
    }
}
