package uk.co.mruoc.day17;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractBsInstruction implements Instruction {

    @Override
    public ProgramState execute(int literalOperand, ProgramState state) {
        long comboOperand = state.toComboOperand(literalOperand);
        long result = comboOperand % 8;
        return storeResult(result, state);
    }

    protected abstract ProgramState storeResult(long result, ProgramState state);
}
