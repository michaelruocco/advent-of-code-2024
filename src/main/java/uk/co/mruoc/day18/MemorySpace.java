package uk.co.mruoc.day18;

import java.util.Collection;
import lombok.Builder;
import lombok.Getter;
import lombok.With;
import uk.co.mruoc.Point;

@Builder
public class MemorySpace {

    private final int size;

    @Getter
    @With
    private final Collection<Point> bytes;

    public Point getStart() {
        return new Point(0, 0);
    }

    public MemorySpace withMaximumNumberOfBytes(int maximumNumberOfBytes) {
        return withBytes(bytes.stream().limit(maximumNumberOfBytes).toList());
    }

    public boolean isInBoundsAndFree(Point point) {
        return inBounds(point) && !byteAt(point);
    }

    public boolean endsAt(Point point) {
        return point.y == size && point.x == size;
    }

    private boolean byteAt(Point point) {
        return bytes.contains(point);
    }

    private boolean inBounds(Point point) {
        return point.y >= 0 && point.x >= 0 && point.y <= size && point.x <= size;
    }
}
