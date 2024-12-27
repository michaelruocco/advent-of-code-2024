package uk.co.mruoc.day17;

import lombok.Builder;
import lombok.With;

@Builder
public class ProgramState {

    @With
    private final Pointer pointer;

    @With
    private final Registry registry;

    @With
    private final Output output;

    public ProgramState jump(int operand) {
        return withPointer(pointer.jump(operand));
    }

    public ProgramState setA(long a) {
        return withRegistry(registry.withA(a));
    }

    public ProgramState setB(long b) {
        return withRegistry(registry.withB(b));
    }

    public ProgramState setC(long c) {
        return withRegistry(registry.withC(c));
    }

    public ProgramState addToOutput(long value) {
        return withOutput(output.add(value));
    }

    public ProgramState movePointer() {
        return withPointer(pointer.next());
    }

    public String programAsString() {
        return new Output(pointer.getProgram()).asString();
    }

    public int[] getNext() {
        return pointer.get();
    }

    public boolean atEnd() {
        return pointer.atEnd();
    }

    public long toComboOperand(int literalOperand) {
        return registry.toComboOperand(literalOperand);
    }

    public long getA() {
        return registry.getA();
    }

    public long getB() {
        return registry.getB();
    }

    public long getC() {
        return registry.getC();
    }

    public String outputAsString() {
        return output.asString();
    }

    public int getProgramLength() {
        return pointer.getProgramLength();
    }

    public long getFirstOutput() {
        return output.getFirst();
    }

    public long getProgramValueAtIndex(int index) {
        return pointer.getProgramValueAt(index);
    }
}
