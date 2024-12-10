package uk.co.mruoc.day9;

import java.util.Collection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class File {

    final Collection<Block> blocks;

    public int size() {
        return blocks.size();
    }
}
