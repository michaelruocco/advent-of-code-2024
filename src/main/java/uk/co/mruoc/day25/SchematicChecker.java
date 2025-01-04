package uk.co.mruoc.day25;

import java.util.Collection;
import org.apache.commons.collections4.CollectionUtils;

public class SchematicChecker {

    public int countPotentialLockAndKeyPairs(Collection<Schematic> schematics) {
        Collection<Schematic> locks =
                schematics.stream().filter(Schematic::isLock).toList();
        Collection<Schematic> keys = CollectionUtils.subtract(schematics, locks);
        return findPotentialLockAndKeyPairs(locks, keys);
    }

    private static int findPotentialLockAndKeyPairs(Collection<Schematic> locks, Collection<Schematic> keys) {
        int count = 0;
        for (Schematic lock : locks) {
            for (Schematic key : keys) {
                if (lock.accepts(key)) {
                    count++;
                }
            }
        }
        return count;
    }
}
