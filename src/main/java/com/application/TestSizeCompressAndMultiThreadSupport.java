package com.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

import com.serializer.MySerializer;
import com.serializer.raft.creator.RaftRequestCreator;
import com.serializer.raft.creator.RaftResponseCreator;
import com.serializer.raft.creator.TestSerializerCreator;
import com.serializer.raft.request.RaftRequest;
import com.serializer.raft.response.RaftResponse;
import com.serializer.utils.MemoryMeasureTool;

import static com.application.TestUtils.*;

public class TestSizeCompressAndMultiThreadSupport {

	
	public static void main(String[] args) throws Exception {
		calculateSizeAndVarify();
		varifyMultiThread();
	}
	
	public static void calculateSizeAndVarify() throws Exception {
		System.out.println("-----calculateSizeAndVarify------");
		calculateSizeAndVarify(javaSerializerCreator, "JavaSerializer");
		calculateSizeAndVarify(hessianSerializerCreator, "HessianSerializer");
		calculateSizeAndVarify(kryoNotPreRegisterClassCreator, "KryoSerializer");
		calculateSizeAndVarify(kryoPreRegisterClassCreator, "KryoSerializer_preRegister_class");
		calculateSizeAndVarify(protostuffSerializerCreator, "ProtostuffSerializer");
		System.out.println();
	}
	
	public static void varifyMultiThread() throws Exception {
		System.out.println("-----varifyMultiThread support------");
		varifyMultiThreadSupport(javaSerializerCreator, "JavaSerializer");
		varifyMultiThreadSupport(hessianSerializerCreator, "HessianSerializer");
		varifyMultiThreadSupport(kryoNotPreRegisterClassCreator, "KryoSerializer");
		varifyMultiThreadSupport(kryoPreRegisterClassCreator, "KryoSerializer_preRegister_class");
		varifyMultiThreadSupport(protostuffSerializerCreator, "ProtostuffSerializer");
		System.out.println();
	}

	public static void calculateSizeAndVarify(TestSerializerCreator s, String mess) throws Exception {
		MySerializer ts = s.create();
		long sizeBefore = 0L;
		long sizeAfter = 0L;
		for (int i = 0; i < 1000; i++) {
			for (RaftRequestCreator requestCreator : requestCreators) {
				RaftRequest request = requestCreator.create();
				sizeBefore += MemoryMeasureTool.measure(request);
				byte[] bb = ts.encode(request);
				sizeAfter += bb.length;

				RaftRequest nrequest = ts.decode(bb, request.getClass());
				if (!Objects.equals(request, nrequest)) {
					System.err.println("--cannot deserialize " + request.getClass().getSimpleName());
				}
			}
			
			for (RaftResponseCreator responseCreator : responseCreators) {
				RaftResponse response = responseCreator.create();
				sizeBefore += MemoryMeasureTool.measure(response);
				byte[] bb = ts.encode(response);
				sizeAfter += bb.length;

				RaftResponse nresponse = ts.decode(bb, response.getClass());
				if (!Objects.equals(response, nresponse)) {
					System.err.println("--cannot deserialize " + response.getClass().getSimpleName());
				}
				
			}
		}
		System.out.println(mess + ": sizeInMemory: " + sizeBefore + ", sizeWhenSerialized: " + sizeAfter);
	}

	public static void varifyMultiThreadSupport(TestSerializerCreator s, String mess) throws Exception {
		MySerializer ts = s.create();
		ExecutorService executorService = Executors.newFixedThreadPool(8);
		List<Future<Boolean>> futures = new ArrayList<>();
		for (int t = 0; t < 10; t++) {
			futures.add(executorService.submit(() -> {
				boolean flag = true;
				for (int i = 0; i < 1000; i++) {
					for (RaftRequestCreator requestCreator : requestCreators) {
						RaftRequest request = requestCreator.create();
						try {
							byte[] bb = ts.encode(request);
							RaftRequest nrequest = ts.decode(bb, request.getClass());
							if (!Objects.equals(request, nrequest)) {
								System.err.println("--cannot deserialize " + request.getClass().getSimpleName());
								flag = false;
							}
						} catch (Exception e) {
							System.err.println(e.getMessage());
							flag = false;
						}

					}
				}
				for (int i = 0; i < 100; i++) {
					for (RaftResponseCreator responseCreator : responseCreators) {
						RaftResponse response = responseCreator.create();
						try {
							byte[] bb = ts.encode(response);
							RaftResponse nresponse = ts.decode(bb, response.getClass());
							if (!Objects.equals(response, nresponse)) {
								System.err.println("--cannot deserialize " + response.getClass().getSimpleName());
								flag = false;
							}
						} catch (Exception e) {
							System.err.println(e.getMessage());
							flag = false;
						}

					}
				}
				return flag;
			}));
		}

		boolean flag = true;
		for (Future<Boolean> future : futures) {
			flag &= future.get();
		}
		System.out.println(mess + ": " + flag);
		executorService.shutdown();
		executorService.awaitTermination(100, TimeUnit.SECONDS);
	}
}
