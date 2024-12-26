package uk.co.mruoc.day17;

public interface Instruction {

    int getOpCode();

    void execute(int literalOperand, ProgramState state);
}
