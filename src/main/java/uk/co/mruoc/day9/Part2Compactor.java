package uk.co.mruoc.day9;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
public class Part2Compactor implements Compactor {

    private List<Block> temp;

    @Override
    public DiskMap compact(Collection<Block> blocks) {
        this.temp = new ArrayList<>(blocks);
        Optional<Group> lastFileGroup = findLastFileGroup(temp.size() - 1);
        while (lastFileGroup.isPresent()) {
            trySwap(lastFileGroup.get());
            lastFileGroup = findLastFileGroup(lastFileGroup.get().start);
        }
        return new DiskMap(temp);
    }

    private void trySwap(Group lastFileGroup) {
        Optional<Group> nextFreeGroup = findNextFreeGroup(0);
        while (nextFreeGroup.isPresent()) {
            if (nextFreeGroup.get().start > lastFileGroup.end) {
                return;
            }
            if (nextFreeGroup.get().canAccommodate(lastFileGroup)) {
                performSwap(lastFileGroup, nextFreeGroup.get());
                return;
            } else {
                nextFreeGroup = findNextFreeGroup(nextFreeGroup.get().end + 1);
            }
        }
    }

    private void performSwap(Group lastFileGroup, Group nextFreeGroup) {
        for (int i = 0; i < lastFileGroup.size; i++) {
            int fileIndex = lastFileGroup.start + i + 1;
            Block fileBlock = temp.get(fileIndex);
            int freeIndex = nextFreeGroup.start + i;
            temp.set(fileIndex, temp.get(freeIndex));
            temp.set(freeIndex, fileBlock);
        }
    }

    private Optional<Group> findLastFileGroup(int index) {
        String state = null;
        int end = -1;
        for (int i = index; i > -1; i--) {
            Block block = temp.get(i);
            boolean stateChanged = !Objects.equals(state, block.getState());
            if (isNewFileBlock(state, block)) {
                state = block.getState();
                end = i;
            } else if (isEndOfFileBlock(state, stateChanged)) {
                return Optional.of(new Group(state, i, end));
            }
        }
        return Optional.empty();
    }

    private Optional<Group> findNextFreeGroup(int index) {
        int start = -1;
        for (int i = index; i < temp.size(); i++) {
            Block block = temp.get(i);
            if (isNewFreeBlock(start, block)) {
                start = i;
            } else if (isEndFreeBlock(start, block)) {
                return Optional.of(new Group(".", start, i));
            }
        }
        return Optional.empty();
    }

    private static boolean isNewFileBlock(String state, Block block) {
        return Objects.isNull(state) && !block.isFree();
    }

    private static boolean isEndOfFileBlock(String state, boolean stateChanged) {
        return Objects.nonNull(state) && stateChanged;
    }

    private static boolean isNewFreeBlock(int start, Block block) {
        return start == -1 && block.isFree();
    }

    private static boolean isEndFreeBlock(int start, Block block) {
        return start != -1 && !block.isFree();
    }

    @ToString
    private static class Group {
        final String state;
        final int start;
        final int end;
        final int size;

        public Group(String state, int start, int end) {
            this.state = state;
            this.start = start;
            this.end = end;
            this.size = end - start;
        }

        public boolean canAccommodate(Group otherGroup) {
            return size >= otherGroup.size;
        }
    }
}
