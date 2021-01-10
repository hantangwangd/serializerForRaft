package com.application;

import com.serializer.MySerializer;
import com.serializer.raft.creator.TestSerializerCreator;
import com.serializer.raft.request.RaftRequest;
import com.serializer.raft.response.RaftResponse;
import com.serializer.utils.CpuCalculator;
import com.serializer.utils.SystemResourceCalculator;

import java.util.concurrent.*;

import static com.application.TestUtils.*;

public class TestResourceCalculate {
    public static void main(String[] args) throws Exception {
        resourceCalculate(16, javaSerializerCreator);
    }

    public static void resourceCalculate(int threadNumber, TestSerializerCreator serializerCreator) throws Exception{
        MySerializer serializer = serializerCreator.create();
        CpuCalculator ccc = SystemResourceCalculator.getCpuCalculator();
        System.out.println(SystemResourceCalculator.getMemorySnapshot());

        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);
        CyclicBarrier cb = new CyclicBarrier(threadNumber + 1);
        CyclicBarrier cb2 = new CyclicBarrier(threadNumber + 1);
        ccc.startCalculate();
        for (int i = 0; i < threadNumber; i++) {
            executorService.submit(() -> {
                ccc.startCalculate();
                RaftRequest[] requests = new RaftRequest[requestCreators.length];
                byte[][] requestBss = new byte[requestCreators.length][];
                RaftResponse[] responses = new RaftResponse[responseCreators.length];
                byte[][] responseBss = new byte[responseCreators.length][];
                for (int n = 0; n < requestCreators.length; n++) {
                    requests[n] = requestCreators[n].create();
                }
                for (int n = 0; n < responseCreators.length; n++) {
                    responses[n] = responseCreators[n].create();
                }
                for (int idx = 0; idx < 100000; idx++) {
                    for (int n = 0; n < requestCreators.length; n++) {
                        requests[n].random();
                    }
                    for (int n = 0; n < responseCreators.length; n++) {
                        responses[n].random();
                    }
                    try {
                        for (int n = 0; n < requestCreators.length; n++) {
                            requestBss[n] = serializer.encode(requests[n]);
                        }
                        for (int n = 0; n < responseCreators.length; n++) {
                            responseBss[n] = serializer.encode(responses[n]);
                        }
                        for (int n = 0; n < requestCreators.length; n++) {
                            serializer.decode(requestBss[n], requests[n].getClass());
                        }
                        for (int n = 0; n < responseCreators.length; n++) {
                            serializer.decode(responseBss[n], responses[n].getClass());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ccc.stopCalculate();
                try {
                    cb.await();
                    cb2.await();
                } catch (Exception e) {

                }
            });
        }

        cb.await();
        System.out.println(SystemResourceCalculator.getMemorySnapshot());
        cb2.await();

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);

        ccc.stopCalculate();
        ccc.printCpuTime(false);

        SystemResourceCalculator.getGcInfo().printGcInfo();
    }
}
