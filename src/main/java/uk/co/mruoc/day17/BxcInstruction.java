package uk.co.mruoc.day17;

public class BxcInstruction extends AbstractBxInstruction {

    private final Registers registers;

    public BxcInstruction(Registers registers) {
        super(registers);
        this.registers = registers;
    }

    @Override
    public int getOpCode() {
        return 4;
    }

    @Override
    public int toOperand(int input) {
        return registers.getC();
    }
}
