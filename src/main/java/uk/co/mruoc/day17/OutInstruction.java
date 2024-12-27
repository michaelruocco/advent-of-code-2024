package uk.co.mruoc.day17;

public class OutInstruction extends AbstractBsInstruction {

    @Override
    public int getOpCode() {
        return 5;
    }

    @Override
    protected ProgramState storeResult(long result, ProgramState state) {
        return state.addToOutput(result);
    }
}
