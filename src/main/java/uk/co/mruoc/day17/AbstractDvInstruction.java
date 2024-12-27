package uk.co.mruoc.day17;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractDvInstruction implements Instruction {

    @Override
    public ProgramState execute(int literalOperand, ProgramState state) {
        long comboOperand = state.toComboOperand(literalOperand);
        long result = (long) (state.getA() / Math.pow(2, comboOperand));
        return storeResult(result, state);
    }

    protected abstract ProgramState storeResult(long result, ProgramState state);
}
