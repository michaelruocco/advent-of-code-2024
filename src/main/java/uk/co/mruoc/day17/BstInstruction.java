package uk.co.mruoc.day17;

public class BstInstruction extends AbstractBsInstruction {

    @Override
    public int getOpCode() {
        return 2;
    }

    @Override
    protected ProgramState storeResult(long result, ProgramState state) {
        return state.setB(result);
    }
}
