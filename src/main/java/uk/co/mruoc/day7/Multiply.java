package uk.co.mruoc.day7;

public class Multiply implements Operator {
    @Override
    public Long apply(Long i1, Long i2) {
        return i1 * i2;
    }
}
