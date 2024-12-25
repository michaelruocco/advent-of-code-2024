package uk.co.mruoc.day17;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Builder;

@Builder
public class Program {

    private final InstructionPointer pointer;
    private final Map<Integer, Instruction> instructions;

    public void execute() {
        while (!pointer.atEnd()) {
            int opCode = pointer.get();
            Instruction instruction = instructions.get(opCode);
            int operand = pointer.get();
            instruction.accept(operand);
        }
    }

    private static Map<Integer, Instruction> toMap(Collection<Instruction> instructions) {
        return instructions.stream().collect(Collectors.toMap(Instruction::getOpCode, Function.identity()));
    }
}
