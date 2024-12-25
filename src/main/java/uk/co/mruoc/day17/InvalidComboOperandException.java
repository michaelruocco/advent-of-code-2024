package uk.co.mruoc.day17;

public class InvalidComboOperandException extends RuntimeException {

    public InvalidComboOperandException(int operand) {
        super(Integer.toString(operand));
    }
}
