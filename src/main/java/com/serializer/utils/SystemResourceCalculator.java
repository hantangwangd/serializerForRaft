package com.serializer.utils;

import java.lang.management.*;

public class SystemResourceCalculator {
    public static CpuCalculator cpuCalculator = null;

    public static MemoryUsageSnapshot getMemorySnapshot() {
        MemoryUsage memoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        return new MemoryUsageSnapshot(memoryUsage.getInit(),
                memoryUsage.getMax(), memoryUsage.getUsed(), memoryUsage.getCommitted());
    }

    public static CpuCalculator getCpuCalculator() {
        if(cpuCalculator == null) {
            synchronized (SystemResourceCalculator.class) {
                if (cpuCalculator == null) {
                    cpuCalculator = new CpuCalculator();
                }
            }
        }
        return cpuCalculator;
    }

    public static GcInfo getGcInfo() {
        GcInfo info = new GcInfo();
        for (GarbageCollectorMXBean gc : ManagementFactory.getGarbageCollectorMXBeans()) {
            long count = gc.getCollectionCount();
            long time = gc.getCollectionTime();
            String name = gc.getName();
            info.addGcCollection(name, count, time);
        }
        return info;
    }
}
