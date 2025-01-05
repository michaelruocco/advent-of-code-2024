package uk.co.mruoc.day7;

public class Sum implements Operator {
    @Override
    public Long apply(Long a, Long b) {
        return a + b;
    }
}
