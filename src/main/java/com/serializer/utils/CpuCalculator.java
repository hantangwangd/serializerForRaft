package com.serializer.utils;

import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static com.google.common.base.MoreObjects.toStringHelper;

public class CpuCalculator {
    ConcurrentHashMap<Long, SingleThreadCpu> threadCpus = new ConcurrentHashMap<>();

    public void startCalculate() {
        long id = Thread.currentThread().getId();
        SingleThreadCpu stc = threadCpus.computeIfAbsent(id, (k) -> new SingleThreadCpu());
        stc.startCalculate();
    }

    public void stopCalculate() {
        long id = Thread.currentThread().getId();
        SingleThreadCpu stc = threadCpus.get(id);
        if (stc == null || stc.startUserTime < 0) {
            throw new RuntimeException("--have not start calculate....");
        }
        stc.stopCalculate();
    }

    public void printCpuTime(boolean detail) {
        if (detail) {
            threadCpus.entrySet().stream().forEach(entry -> System.out.println(entry));
        }
        SingleThreadCpu stc0 = threadCpus.values().stream().reduce((a, b) -> a.add(b)).get();
        NumberFormat format = NumberFormat.getPercentInstance();
        format.setMaximumFractionDigits(2);//设置保留几位小数
        long totalCpuTime = stc0.cpuTimes.stream().reduce((a, b) -> a + b).orElse(0L);
        long totalUserTime = stc0.userTimes.stream().reduce((a, b) -> a + b).orElse(0L);
        long totalSysTime = stc0.sysTimes.stream().reduce((a, b) -> a + b).orElse(0L);
        System.out.println("--cpu time: "
                + format.format(new BigDecimal(totalCpuTime).multiply(new BigDecimal(threadCpus.size()))
                    .divide(new BigDecimal(totalSysTime), 4, BigDecimal.ROUND_HALF_DOWN).doubleValue())
                + "--user time: " + format.format(new BigDecimal(totalUserTime).multiply(new BigDecimal(threadCpus.size()))
                    .divide(new BigDecimal(totalSysTime), 4, BigDecimal.ROUND_HALF_DOWN).doubleValue()));
    }

    private class SingleThreadCpu {
        long startCpuTime = -1;
        long startUserTime = -1;
        long startSysTime = -1;
        List<Long> cpuTimes = new ArrayList<>();
        List<Long> userTimes = new ArrayList<>();
        List<Long> sysTimes = new ArrayList<>();

        void startCalculate() {
            this.startCpuTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
            this.startUserTime = ManagementFactory.getThreadMXBean().getCurrentThreadUserTime();
            this.startSysTime = System.nanoTime();
        }

        void stopCalculate() {
            cpuTimes.add(ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - this.startCpuTime);
            userTimes.add(ManagementFactory.getThreadMXBean().getCurrentThreadUserTime() - this.startUserTime);
            sysTimes.add(System.nanoTime() - this.startSysTime);
            this.startCpuTime = -1;
            this.startUserTime = -1;
            this.startSysTime = -1;
        }

        SingleThreadCpu add(SingleThreadCpu stc) {
            SingleThreadCpu nstc = new SingleThreadCpu();
            nstc.cpuTimes.addAll(this.cpuTimes);
            nstc.cpuTimes.addAll(stc.cpuTimes);
            nstc.userTimes.addAll(this.userTimes);
            nstc.userTimes.addAll(stc.userTimes);
            nstc.sysTimes.addAll(this.sysTimes);
            nstc.sysTimes.addAll(stc.sysTimes);
            return nstc;
        }

        @Override
        public String toString() {
            return toStringHelper(this)
                    .add("cpuTimes", cpuTimes.stream().reduce((a, b) -> a + b).orElse(0L))
                    .add("userTimes", userTimes.stream().reduce((a, b) -> a + b).orElse(0L))
                    .add("sysTimes", sysTimes.stream().reduce((a, b) -> a + b).orElse(0L))
                    .toString();
        }
    }
}
