package uk.co.mruoc.day17;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractBxInstruction implements Instruction {

    private final Registers registers;

    @Override
    public void accept(Integer input) {
        int b = registers.getB();
        int operand = toOperand(input);
        registers.setB(b ^ operand);
    }

    protected abstract int toOperand(int input);
}
