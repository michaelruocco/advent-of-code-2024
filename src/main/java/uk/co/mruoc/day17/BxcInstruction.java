package uk.co.mruoc.day17;

public class BxcInstruction extends AbstractBxInstruction {

    @Override
    public int getOpCode() {
        return 4;
    }

    @Override
    protected long toOperand(int literalOperand, ProgramState state) {
        return state.getC();
    }
}
