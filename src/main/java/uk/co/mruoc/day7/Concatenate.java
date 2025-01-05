package uk.co.mruoc.day7;

public class Concatenate implements Operator {
    @Override
    public Long apply(Long a, Long b) {
        return Long.parseLong(a + String.valueOf(b));
    }
}
