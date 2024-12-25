package uk.co.mruoc.day17;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractBsInstruction implements Instruction {

    private final Registers registers;

    @Override
    public void accept(Integer operand) {
        handleResult(registers.toValue(operand) % 8);
    }

    protected abstract void handleResult(int result);
}
