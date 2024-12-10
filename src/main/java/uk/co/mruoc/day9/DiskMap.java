package uk.co.mruoc.day9;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DiskMap {

    private final List<Block> blocks;

    public DiskMap(String input) {
        this(toBlocks(input));
    }

    public String getState() {
        return blocks.stream().map(Block::getState).collect(Collectors.joining());
    }

    private static List<Block> toBlocks(String input) {
        int[] ints = toInts(input);
        List<Block> blocks = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < ints.length; i++) {
            int size = ints[i];
            if (isOdd(i)) {
                blocks.addAll(buildFreeBlocks(size));
            } else {
                blocks.addAll(buildFileBlocks(id, size));
                id++;
            }
        }
        return blocks;
    }

    public DiskMap compact(Compactor compactor) {
        return compactor.compact(blocks);
    }

    public long checksum() {
        long checksum = 0;
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            if (!block.isFree()) {
                checksum += block.toChecksum(i);
            }
        }
        return checksum;
    }

    private static int[] toInts(String input) {
        return input.chars().map(c -> c - '0').toArray();
    }

    private static boolean isOdd(int i) {
        return (i & 1) != 0;
    }

    private static Collection<Block> buildFreeBlocks(int size) {
        return IntStream.range(0, size).mapToObj(i -> new Block()).toList();
    }

    private static Collection<Block> buildFileBlocks(int id, int size) {
        return IntStream.range(0, size).mapToObj(i -> new Block(id)).toList();
    }
}