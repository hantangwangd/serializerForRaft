package com.application;

import com.serializer.MySerializer;
import com.serializer.raft.request.RaftRequest;
import com.serializer.raft.response.RaftResponse;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import static com.application.TestUtils.requestCreators;
import static com.application.TestUtils.responseCreators;

@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 10, batchSize = 100)
@Fork(2)
@State(Scope.Thread)
public class TestSerializerByJmhBase {
    static RaftRequest[] requests = new RaftRequest[requestCreators.length];
    static RaftResponse[] responses = new RaftResponse[responseCreators.length];
    static byte[][] requestBss = new byte[requestCreators.length][];
    static byte[][] responseBss = new byte[responseCreators.length][];

    static ThreadLocal<byte[][]> serializeRes = new ThreadLocal<byte[][]>() {
        @Override
        protected byte[][] initialValue() {
            return new byte[requestCreators.length + responseCreators.length][];
        }
    };
    static ThreadLocal<Object[]> deserializeRes = new ThreadLocal<Object[]>() {
        @Override
        protected Object[] initialValue() {
            return new Object[requestCreators.length + responseCreators.length];
        }
    };

    @Param(value = {"1", "1"})
    private int length;

    protected void init(MySerializer serializer) {
        try {
            for (int n = 0; n < requestCreators.length; n++) {
                requests[n] = requestCreators[n].create();
                requestBss[n] = serializer.encode(requests[n]);
            }
            for (int n = 0; n < responseCreators.length; n++) {
                responses[n] = responseCreators[n].create();
                responseBss[n] = serializer.encode(responses[n]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Benchmark
    public void testMultiThreadSharedSingletonSerialize(Blackhole blackhole) throws Exception {
        MySerializer serializer = getSingletonSerializer();
        for (int i = 0; i < length; i++) {
            for (int n = 0; n < requests.length; n++) {
                serializeRes.get()[n] =
                        serializer.encode(requests[n]);
            }
            for (int n = 0; n > responses.length; n++) {
                serializeRes.get()[requests.length + n] =
                        serializer.encode(responses[n]);
            }
        }
        blackhole.consume(serializeRes.get());
    }

    @Benchmark
    public void testMultiThreadSharedSingletonDeserialize(Blackhole blackhole) throws Exception {
        MySerializer serializer = getSingletonSerializer();
        for (int i = 0; i < length; i++) {
            for (int n = 0; n < requestBss.length; n++) {
                deserializeRes.get()[n] =
                        serializer.decode(requestBss[n], requests[n].getClass());
            }
            for (int n = 0; n > responseBss.length; n++) {
                deserializeRes.get()[requests.length + n] =
                        serializer.decode(responseBss[n], responses[n].getClass());
            }
        }
        blackhole.consume(deserializeRes.get());
    }

    @Benchmark
    public void testMultiThreadMultitonSerialize(Blackhole blackhole) throws Exception {
        MySerializer serializer = getMultitonSerializer();
        for (int i = 0; i < length; i++) {
            for (int n = 0; n < requests.length; n++) {
                serializeRes.get()[n] =
                        serializer.encode(requests[n]);
            }
            for (int n = 0; n > responses.length; n++) {
                serializeRes.get()[requests.length + n] =
                        serializer.encode(responses[n]);
            }
        }
        blackhole.consume(serializeRes.get());
    }

    @Benchmark
    public void testMultiThreadMultitonDeserialize(Blackhole blackhole) throws Exception {
        MySerializer serializer = getMultitonSerializer();
        for (int i = 0; i < length; i++) {
            for (int n = 0; n < requestBss.length; n++) {
                deserializeRes.get()[n] =
                        serializer.decode(requestBss[n], requests[n].getClass());
            }
            for (int n = 0; n > responseBss.length; n++) {
                deserializeRes.get()[requests.length + n] =
                        serializer.decode(responseBss[n], responses[n].getClass());
            }
        }
        blackhole.consume(deserializeRes.get());
    }

    protected MySerializer getMultitonSerializer() {
        return null;
    };

    protected MySerializer getSingletonSerializer() {
        return null;
    }

    protected String getClassName() {
        return null;
    }

    @Test
    public void doTest() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(getClassName())
                .build();
        new Runner(opt).run();
    }
}
