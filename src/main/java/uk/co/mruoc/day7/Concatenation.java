package uk.co.mruoc.day7;

public class Concatenation implements Operator {
    @Override
    public Long apply(Long i1, Long i2) {
        return Long.parseLong(String.valueOf(i1) + i2);
    }
}
