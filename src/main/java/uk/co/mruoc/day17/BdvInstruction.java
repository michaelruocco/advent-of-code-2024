package uk.co.mruoc.day17;

public class BdvInstruction extends AbstractDvInstruction {

    @Override
    public int getOpCode() {
        return 6;
    }

    @Override
    protected void storeResult(int result, Registry registry) {
        registry.setB(result);
    }
}
