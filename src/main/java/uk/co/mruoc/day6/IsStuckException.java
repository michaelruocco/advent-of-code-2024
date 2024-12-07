package uk.co.mruoc.day6;

public class IsStuckException extends RuntimeException {

    public IsStuckException(Step step) {
        super(String.format("guard stuck on step %s", step.toString()));
    }
}
