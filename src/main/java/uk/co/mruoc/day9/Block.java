package uk.co.mruoc.day9;

import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class Block {

    private final Integer id;

    public Block() {
        this(null);
    }

    public String getState() {
        return getId().map(i -> Integer.toString(i)).orElse(".");
    }

    private Optional<Integer> getId() {
        return Optional.ofNullable(id);
    }

    public boolean isFree() {
        return Objects.isNull(id);
    }

    public long toChecksum(long position) {
        if (isFree()) {
            return 0;
        }
        return position * id;
    }
}
