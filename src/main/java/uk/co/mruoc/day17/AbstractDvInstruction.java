package uk.co.mruoc.day17;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractDvInstruction implements Instruction {

    @Override
    public void execute(int literalOperand, ProgramState state) {
        Registry registry = state.getRegistry();
        int comboOperand = registry.toComboOperand(literalOperand);
        int result = (int) (registry.getA() / Math.pow(2, comboOperand));
        storeResult(result, registry);
    }

    protected abstract void storeResult(int result, Registry registry);
}
