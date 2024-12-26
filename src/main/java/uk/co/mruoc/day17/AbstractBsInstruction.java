package uk.co.mruoc.day17;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractBsInstruction implements Instruction {

    @Override
    public void execute(int literalOperand, ProgramState state) {
        Registry registry = state.getRegistry();
        int comboOperand = registry.toComboOperand(literalOperand);
        int result = comboOperand % 8;
        storeResult(result, state);
    }

    protected abstract void storeResult(int result, ProgramState state);
}
