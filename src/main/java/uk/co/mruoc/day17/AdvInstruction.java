package uk.co.mruoc.day17;

public class AdvInstruction extends AbstractDvInstruction {

    @Override
    public int getOpCode() {
        return 0;
    }

    @Override
    protected void storeResult(int result, Registry registry) {
        registry.setA(result);
    }
}
