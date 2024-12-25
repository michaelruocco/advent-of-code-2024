package uk.co.mruoc.day17;

public class AdvInstruction extends AbstractDvInstruction {

    private final Registers registers;

    public AdvInstruction(Registers registers) {
        super(registers);
        this.registers = registers;
    }

    @Override
    public int getOpCode() {
        return 0;
    }

    @Override
    protected void storeResult(int result) {
        registers.setA(result);
    }
}
