package uk.co.mruoc.day17;

public class CdvInstruction extends AbstractDvInstruction {

    @Override
    public int getOpCode() {
        return 7;
    }

    @Override
    protected ProgramState storeResult(long result, ProgramState state) {
        return state.setC(result);
    }
}
