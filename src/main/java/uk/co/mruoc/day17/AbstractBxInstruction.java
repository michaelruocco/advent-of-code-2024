package uk.co.mruoc.day17;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractBxInstruction implements Instruction {

    @Override
    public void execute(int literalOperand, ProgramState programState) {
        Registry registry = programState.getRegistry();
        int b = registry.getB();
        int operand = toOperand(literalOperand, registry);
        registry.setB(b ^ operand);
    }

    protected abstract int toOperand(int literalOperand, Registry registry);
}
