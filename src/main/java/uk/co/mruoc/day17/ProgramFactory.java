package uk.co.mruoc.day17;

import java.util.Map;

public class ProgramFactory {

    public Program build(int[] input, Registers registers, Output output) {
        InstructionPointer pointer = new InstructionPointer(input);
        return Program.builder()
                .pointer(pointer)
                .instructions(buildInstructions(pointer, registers, output))
                .build();
    }

    private Map<Integer, Instruction> buildInstructions(
            InstructionPointer pointer, Registers registers, Output output) {
        return InstructionFactory.builder()
                .output(output)
                .pointer(pointer)
                .registers(registers)
                .build()
                .buildMap();
    }
}
