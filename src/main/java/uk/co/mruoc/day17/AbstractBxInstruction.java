package uk.co.mruoc.day17;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractBxInstruction implements Instruction {

    @Override
    public ProgramState execute(int literalOperand, ProgramState state) {
        long b = state.getB();
        long operand = toOperand(literalOperand, state);
        return state.setB(b ^ operand);
    }

    protected abstract long toOperand(int literalOperand, ProgramState state);
}
