package uk.co.mruoc.day6;

public class IsStuckException extends RuntimeException {

    private final Step step;

    public IsStuckException(Step step) {
        super(String.format("guard stuck on step %s", step.toString()));
        this.step = step;
    }
}
