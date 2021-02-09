package com.serializer.utils;

import java.io.File;
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

    public static void main(String[] args) throws Exception {
            File[] disks = File.listRoots();
            for(File file : disks)
            {
                System.out.print(file.getPath() + "    ");
                System.out.print("空闲未使用 = " + file.getFreeSpace() / 1024 / 1024 + "M" + "    ");// 空闲空间
                System.out.print("可以使用 = " + file.getUsableSpace() / 1024 / 1024 + "M" + "    ");// 可用空间
                System.out.print("总容量 = " + file.getTotalSpace() / 1024 / 1024 + "M" + "    ");// 总空间
                System.out.println();
            }
    }
}
