package uk.co.mruoc.day17;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JnzInstruction implements Instruction {

    private final Registers registers;
    private final InstructionPointer pointer;

    @Override
    public int getOpCode() {
        return 3;
    }

    @Override
    public void accept(Integer literalOperand) {
        if (registers.getA() == 0) {
            return;
        }
        pointer.jump(literalOperand);
    }
}
