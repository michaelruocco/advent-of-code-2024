package uk.co.mruoc.day17;

public interface Instruction {

    int getOpCode();

    ProgramState execute(int literalOperand, ProgramState state);
}
