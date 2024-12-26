package uk.co.mruoc.day17;

public class BxlInstruction extends AbstractBxInstruction {

    @Override
    public int getOpCode() {
        return 1;
    }

    @Override
    protected int toOperand(int literalOperand, Registry registry) {
        return literalOperand;
    }
}
