package uk.co.mruoc.day17;

public class OutInstruction extends AbstractBsInstruction {

    private final Output output;

    public OutInstruction(Registers registers, Output output) {
        super(registers);
        this.output = output;
    }

    @Override
    public int getOpCode() {
        return 5;
    }

    @Override
    protected void handleResult(int result) {
        output.add(result);
    }
}
