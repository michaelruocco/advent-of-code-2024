package uk.co.mruoc.day17;

public class BdvInstruction extends AbstractDvInstruction {

    @Override
    public int getOpCode() {
        return 6;
    }

    @Override
    protected ProgramState storeResult(long result, ProgramState state) {
        return state.setB(result);
    }
}
