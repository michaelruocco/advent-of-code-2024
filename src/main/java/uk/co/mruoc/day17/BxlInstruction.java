package uk.co.mruoc.day17;

public class BxlInstruction extends AbstractBxInstruction {

    @Override
    public int getOpCode() {
        return 1;
    }

    @Override
    protected long toOperand(int literalOperand, ProgramState state) {
        return literalOperand;
    }
}
