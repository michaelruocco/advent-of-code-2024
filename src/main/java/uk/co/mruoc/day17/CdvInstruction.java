package uk.co.mruoc.day17;

public class CdvInstruction extends AbstractDvInstruction {

    @Override
    public int getOpCode() {
        return 7;
    }

    @Override
    protected void storeResult(int result, Registry registry) {
        registry.setC(result);
    }
}
