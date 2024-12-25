package uk.co.mruoc.day17;

public class BdvInstruction extends AbstractDvInstruction {

    private final Registers registers;

    public BdvInstruction(Registers registers) {
        super(registers);
        this.registers = registers;
    }

    @Override
    public int getOpCode() {
        return 6;
    }

    @Override
    protected void storeResult(int result) {
        registers.setB(result);
    }
}
