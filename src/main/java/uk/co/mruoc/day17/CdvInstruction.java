package uk.co.mruoc.day17;

public class CdvInstruction extends AbstractDvInstruction {

    private final Registers registers;

    public CdvInstruction(Registers registers) {
        super(registers);
        this.registers = registers;
    }

    @Override
    public int getOpCode() {
        return 7;
    }

    @Override
    protected void storeResult(int result) {
        registers.setC(result);
    }
}
