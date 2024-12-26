package uk.co.mruoc.day17;

public class OutInstruction extends AbstractBsInstruction {

    @Override
    public int getOpCode() {
        return 5;
    }

    @Override
    protected void storeResult(int result, ProgramState state) {
        Output output = state.getOutput();
        output.add(result);
    }
}
