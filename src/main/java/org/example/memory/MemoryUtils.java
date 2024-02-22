package org.example.memory;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MemoryUtils {

    private long getGcCount() {
        long sum = 0;
        for (GarbageCollectorMXBean b : ManagementFactory.getGarbageCollectorMXBeans()) {
            long count = b.getCollectionCount();
            if (count != -1) {sum += count;}
        }
        return sum;
    }

    public long getReallyUsedMemory() {
        long before = getGcCount();
        System.gc();
        while (getGcCount() == before) ;
        return getCurrentlyUsedMemory();
    }

    private long getCurrentlyUsedMemory() {
        return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed()
                + ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed();
    }
}
