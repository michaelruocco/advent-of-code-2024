package uk.co.mruoc.day17;

public class AdvInstruction extends AbstractDvInstruction {

    @Override
    public int getOpCode() {
        return 0;
    }

    @Override
    protected ProgramState storeResult(long result, ProgramState state) {
        return state.setA(result);
    }
}
