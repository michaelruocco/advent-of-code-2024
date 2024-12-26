package uk.co.mruoc.day17;

public class BxcInstruction extends AbstractBxInstruction {

    @Override
    public int getOpCode() {
        return 4;
    }

    @Override
    protected int toOperand(int literalOperand, Registry registry) {
        return registry.getC();
    }
}
