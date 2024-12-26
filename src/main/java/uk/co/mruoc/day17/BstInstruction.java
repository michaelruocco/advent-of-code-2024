package uk.co.mruoc.day17;

public class BstInstruction extends AbstractBsInstruction {

    @Override
    public int getOpCode() {
        return 2;
    }

    @Override
    protected void storeResult(int result, ProgramState programState) {
        Registry registry = programState.getRegistry();
        registry.setB(result);
    }
}
