package uk.co.mruoc.day17;

public class BstInstruction extends AbstractBsInstruction {

    private final Registers registers;

    public BstInstruction(Registers registers) {
        super(registers);
        this.registers = registers;
    }

    @Override
    public int getOpCode() {
        return 2;
    }

    @Override
    protected void handleResult(int result) {
        registers.setB(result);
    }
}
