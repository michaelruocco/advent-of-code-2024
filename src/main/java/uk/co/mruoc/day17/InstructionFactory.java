package uk.co.mruoc.day17;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Builder;

@Builder
public class InstructionFactory {

    private final Output output;
    private final InstructionPointer pointer;
    private final Registers registers;

    public Map<Integer, Instruction> buildMap() {
        return build().stream().collect(Collectors.toMap(Instruction::getOpCode, Function.identity()));
    }

    private Collection<Instruction> build() {
        return List.of(
                new AdvInstruction(registers), // op code 0
                new BxlInstruction(registers), // op code 1
                new BstInstruction(registers), // op code 2
                new BxcInstruction(registers), // op code 4
                new OutInstruction(registers, output), // op code 5
                new BdvInstruction(registers), // op code 6
                new CdvInstruction(registers) // op code 7
                );
    }
}
