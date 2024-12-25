package uk.co.mruoc.day17;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractDvInstruction implements Instruction {

    private final Registers registers;

    @Override
    public void accept(Integer operand) {
        storeResult((int) (registers.getA() / Math.pow(2, registers.toComboOperand(operand))));
    }

    protected abstract void storeResult(int result);
}
