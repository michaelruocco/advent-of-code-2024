package uk.co.mruoc.day9;

import java.util.Collection;

public interface Compactor {
    DiskMap compact(Collection<Block> blocks);
}
