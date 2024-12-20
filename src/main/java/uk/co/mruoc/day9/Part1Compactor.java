package uk.co.mruoc.day9;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Part1Compactor implements Compactor {

    private List<Block> temp;

    @Override
    public DiskMap compact(DiskMap map) {
        this.temp = new ArrayList<>(map.getBlocks());
        int firstFreeIndex = getFirstFreeBlockIndex(0);
        int lastFileIndex = getLastFileBlockIndex(temp.size() - 1);

        while (lastFileIndex > firstFreeIndex && firstFreeIndex != -1) {
            Block firstFreeBlock = temp.get(firstFreeIndex);
            temp.set(firstFreeIndex, temp.get(lastFileIndex));
            temp.set(lastFileIndex, firstFreeBlock);

            firstFreeIndex = getFirstFreeBlockIndex(firstFreeIndex + 1);
            lastFileIndex = getLastFileBlockIndex(lastFileIndex - 1);
        }
        return new DiskMap(temp);
    }

    private int getFirstFreeBlockIndex(int start) {
        for (int i = start; i < temp.size(); i++) {
            if (temp.get(i).isFree()) {
                return i;
            }
        }
        return -1;
    }

    private int getLastFileBlockIndex(int end) {
        for (int i = end; i > -1; i--) {
            if (!temp.get(i).isFree()) {
                return i;
            }
        }
        return -1;
    }
}
