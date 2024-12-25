package uk.co.mruoc.day17;

public class InvalidOperandException extends RuntimeException {

    public InvalidOperandException(int operand) {
        super(Integer.toString(operand));
    }
}
