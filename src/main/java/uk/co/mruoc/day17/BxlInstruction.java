package uk.co.mruoc.day17;

public class BxlInstruction extends AbstractBxInstruction {

    private final Registers registers;

    public BxlInstruction(Registers registers) {
        super(registers);
        this.registers = registers;
    }

    @Override
    public int getOpCode() {
        return 1;
    }

    @Override
    public int toOperand(int literalOperand) {
        return literalOperand;
    }
}
