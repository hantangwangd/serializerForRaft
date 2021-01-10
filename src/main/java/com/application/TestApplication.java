package com.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

import com.serializer.MySerializer;
import com.serializer.raft.creator.RaftRequestCreator;
import com.serializer.raft.creator.RaftResponseCreator;
import com.serializer.raft.creator.TestSerializerCreator;
import com.serializer.raft.request.RaftRequest;
import com.serializer.raft.response.RaftResponse;
import com.serializer.utils.MemoryMeasureTool;

import static com.application.TestUtils.*;

public class TestApplication {

	
	public static void main(String[] args) throws Exception {
		calculateSizeAndVarify();
		
//		varifyMultiThread();

//		calculateSingleThreadSpeed();

		calculateMultiThreadSpeed(8);
		
//		calculateMultiThreadWithMultiSerializerSpeed(8);
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
	
	public static void calculateSingleThreadSpeed() throws Exception {
		calculateSingleThreadSpeed(5, javaSerializerCreator, "JavaSerializer");
		calculateSingleThreadSpeed(5, hessianSerializerCreator, "HessianSerializer");
		calculateSingleThreadSpeed(6, kryoNotPreRegisterClassCreator, "KryoSerializer");
		calculateSingleThreadSpeed(6, kryoPreRegisterClassCreator, "KryoSerializer_preRegister_class");
		calculateSingleThreadSpeed(6, protostuffSerializerCreator, "ProtostuffSerializer");
	}
	
	public static void calculateMultiThreadSpeed(int threadNumber) throws Exception {
		calculateMultiThreadSpeed(5, threadNumber, javaSerializerCreator, "JavaSerializer");
		calculateMultiThreadSpeed(5, threadNumber, hessianSerializerCreator, "HessianSerializer");
		calculateMultiThreadSpeed(6, threadNumber, kryoNotPreRegisterClassCreator, "KryoSerializer");
		calculateMultiThreadSpeed(6, threadNumber, kryoPreRegisterClassCreator, "KryoSerializer_preRegister_class");
		calculateMultiThreadSpeed(6, threadNumber, protostuffSerializerCreator, "ProtostuffSerializer");
	}
	
	public static void calculateMultiThreadWithMultiSerializerSpeed(int threadNumber) throws Exception {
		calculateMultiThreadWithMultiSerializerSpeed(5, threadNumber, javaSerializerCreator, "JavaSerializer");
		calculateMultiThreadWithMultiSerializerSpeed(5, threadNumber, hessianSerializerCreator, "HessianSerializer");
		calculateMultiThreadWithMultiSerializerSpeed(6, threadNumber, kryoNotPreRegisterClassCreator, "KryoSerializer");
		calculateMultiThreadWithMultiSerializerSpeed(6, threadNumber, kryoPreRegisterClassCreator, "KryoSerializer_preRegister_class");
		calculateMultiThreadWithMultiSerializerSpeed(6, threadNumber, protostuffSerializerCreator, "ProtostuffSerializer");
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

	public static void calculateSingleThreadSpeed(int power, TestSerializerCreator s, String mess) throws Exception {
		AtomicLong serializeTime = new AtomicLong(0);
		AtomicLong deserializeTime = new AtomicLong(0);
		AtomicLong initTime = new AtomicLong(0);
		MySerializer ts = s.create();
		System.out.println("------" + mess);
		RaftRequest[] requests = new RaftRequest[requestCreators.length * 100];
		RaftRequest[] nrequests = new RaftRequest[requestCreators.length * 100];
		byte[][] requestBss = new byte[requestCreators.length * 100][];
		RaftResponse[] responses = new RaftResponse[responseCreators.length * 100];
		RaftResponse[] nresponses = new RaftResponse[responseCreators.length * 100];
		byte[][] responseBss = new byte[responseCreators.length * 100][];
		for (int c = 0; c < power; c++) {
			System.gc();
			serializeTime.set(0);
			deserializeTime.set(0);
			initTime.set(0);
			if (c > 0 && c <= 3) {
				continue;
			}
			long ss = System.currentTimeMillis();

			long s0 = System.currentTimeMillis();
			for (int n = 0; n < requestCreators.length * 100; n++) {
				requests[n] = requestCreators[n % requestCreators.length].create();
			}
			for (int n = 0; n < responseCreators.length * 100; n++) {
				responses[n] = responseCreators[n % responseCreators.length].create();
			}
			long e0 = System.currentTimeMillis();
			initTime.addAndGet(e0 - s0);

			for (int i = 0; i < Math.pow(10, c - 2); i++) {
				try {

					s0 = System.currentTimeMillis();
					for (int n = 0; n < requestCreators.length * 100; n++) {
						requests[n].random();
					}
					for (int n = 0; n < responseCreators.length * 100; n++) {
						responses[n].random();
					}
					e0 = System.currentTimeMillis();
					initTime.addAndGet(e0 - s0);
					
					long s1 = System.currentTimeMillis();
					for (int n = 0; n < requestCreators.length * 100; n++) {
						requestBss[n] = ts.encode(requests[n]);
					}
					for (int n = 0; n < responseCreators.length * 100; n++) {
						responseBss[n] = ts.encode(responses[n]);
					}
					long e1 = System.currentTimeMillis();
					serializeTime.addAndGet(e1 - s1);

					long s2 = System.currentTimeMillis();
					for (int n = 0; n < requestCreators.length * 100; n++) {
//						nrequests[n] = ts.decode(requestBss[n], requests[n].getClass());
						ts.decode(requestBss[n], requests[n].getClass());
					}
					for (int n = 0; n < responseCreators.length * 100; n++) {
//						nresponses[n] = ts.decode(responseBss[n], responses[n].getClass());
						ts.decode(responseBss[n], responses[n].getClass());
					}
					long e2 = System.currentTimeMillis();
					deserializeTime.addAndGet(e2 - s2);
					
//					if (!Arrays.deepEquals(nrequests, requests) || !Arrays.deepEquals(nresponses, responses)) {
//						throw new Exception("反序列化后不一致！！");
//					}
					
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println(Thread.currentThread().getName() + ": " + e.getMessage());
					break;
				}
			}
			long ee = System.currentTimeMillis();
//			System.out.println(
//					Math.pow(10, c) + " serialize time: " + serializeTime
//					+ ", deserialize time: " + deserializeTime
//					+ ", init time: " + initTime
//					+ ", total time: " + (ee - ss));

			if (c != 0) {
				int number = new Double(Math.pow(10, c)).intValue();
				System.out.println(Math.pow(10, c)
						+ " serialize speed: " + (number * 1000) / serializeTime.get()
						+ ", deserialize time: " + (number * 1000) / deserializeTime.get()
						+ ", init time " + initTime.get());
			}
		}
	}

	public static void calculateMultiThreadSpeed(int power, int threadNumber, TestSerializerCreator s, String mess)
			throws Exception {
		MySerializer ts = s.create();
		ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);
		AtomicLong serializeTime = new AtomicLong(0);
		AtomicLong deserializeTime = new AtomicLong(0);
		AtomicLong initObjectCost = new AtomicLong(0);
		System.out.println(mess + " start... ============================================");
		for (int c = 0; c < power; c++) {
			if (c > 0 && c <= 3) {
				continue;
			}
			System.gc();
			serializeTime.set(0);
			deserializeTime.set(0);
			initObjectCost.set(0);
			List<Future<Boolean>> futures = new ArrayList<>();
			long start = System.currentTimeMillis();
			final CyclicBarrier cb = new CyclicBarrier(threadNumber);
			for (int t = 0; t < threadNumber; t++) {
				final int ic = c;
				futures.add(executorService.submit(() -> {
					boolean flag = true;
					RaftRequest[] requests = new RaftRequest[requestCreators.length * 100];
					byte[][] requestBss = new byte[requestCreators.length * 100][];
					RaftResponse[] responses = new RaftResponse[responseCreators.length * 100];
					byte[][] responseBss = new byte[responseCreators.length * 100][];
					long istart = System.currentTimeMillis();
					for (int n = 0; n < requestCreators.length * 100; n++) {
						requests[n] = requestCreators[n % requestCreators.length].create();
					}
					for (int n = 0; n < responseCreators.length * 100; n++) {
						responses[n] = responseCreators[n % responseCreators.length].create();
					}
					long iend = System.currentTimeMillis();
					initObjectCost.addAndGet(iend - istart);
					cb.await();
					for (int i = 0; i < Math.pow(10, ic - 2); i++) {
						try {
							istart = System.currentTimeMillis();
							for (int n = 0; n < requestCreators.length * 100; n++) {
								requests[n].random();
							}
							for (int n = 0; n < responseCreators.length * 100; n++) {
								responses[n].random();
							}
							iend = System.currentTimeMillis();
							initObjectCost.addAndGet(iend - istart);
							long s1 = System.currentTimeMillis();
							for (int n = 0; n < requestCreators.length * 100; n++) {
								requestBss[n] = ts.encode(requests[n]);
							}
							for (int n = 0; n < responseCreators.length * 100; n++) {
								responseBss[n] = ts.encode(responses[n]);
							}
							long e1 = System.currentTimeMillis();
							serializeTime.addAndGet(e1 - s1);

							long s2 = System.currentTimeMillis();
							for (int n = 0; n < requestCreators.length * 100; n++) {
//								RaftRequest nrequest =
										ts.decode(requestBss[n], requests[n].getClass());
							}
							for (int n = 0; n < responseCreators.length * 100; n++) {
//								RaftResponse nresponse =
										ts.decode(responseBss[n], responses[n].getClass());
							}
							long e2 = System.currentTimeMillis();
							deserializeTime.addAndGet(e2 - s2);
						} catch (Exception e) {
							e.printStackTrace();
							System.err.println(Thread.currentThread().getName() + ": " + e.getMessage());
							flag = false;
							break;
						}
					}
					return flag;
				}));
			}

			for (Future future : futures) {
				future.get();
			}
			long end = System.currentTimeMillis();
			int number = new Double(Math.pow(10, c)).intValue();
//			System.out.println(Math.pow(10, c)
//					+ " serialize time: " + serializeTime.get()
//					+ ", deserialize time: " + deserializeTime.get()
//					+ " init objects time: " + initObjectCost.get()
//					+ ", real time " + (end - start));

			if (c != 0) {
				System.out.println(Math.pow(10, c)
						+ " serialize speed: " + (number * threadNumber * threadNumber) / (serializeTime.get()/1000)
						+ ", deserialize speed: " + (number * threadNumber * threadNumber) / (deserializeTime.get()/1000)
						+ ", real time " + (end - start));
			}
		}
		executorService.shutdown();
		executorService.awaitTermination(100, TimeUnit.SECONDS);
	}
	
	public static void calculateMultiThreadWithMultiSerializerSpeed(int power, int threadNumber, TestSerializerCreator s, 
			String mess) throws Exception {
		MySerializer[] tss = new MySerializer[threadNumber];
		for (int i = 0; i < threadNumber; i++) {
			tss[i] = s.create();
		}
		ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);
		AtomicLong serializeTime = new AtomicLong(0);
		AtomicLong deserializeTime = new AtomicLong(0);
		AtomicLong initObjectCost = new AtomicLong(0);
		System.out.println(mess + " start... ============================================");
		for (int c = 0; c < power; c++) {
			if (c > 0 && c <= 3) {
				continue;
			}
			serializeTime.set(0);
			deserializeTime.set(0);
			initObjectCost.set(0);
			List<Future> futures = new ArrayList<>();
			long start = System.currentTimeMillis();
			for (int t = 0; t < threadNumber; t++) {
				final int ic = c;
				final int it = t;
				futures.add(executorService.submit(() -> {
					boolean flag = true;
					RaftRequest[] requests = new RaftRequest[requestCreators.length * 100];
					byte[][] requestBss = new byte[requestCreators.length * 100][];
					RaftResponse[] responses = new RaftResponse[responseCreators.length * 100];
					byte[][] responseBss = new byte[responseCreators.length * 100][];
					
					long istart = System.currentTimeMillis();
					for (int n = 0; n < requestCreators.length * 100; n++) {
						requests[n] = requestCreators[n % requestCreators.length].create();
					}
					for (int n = 0; n < responseCreators.length * 100; n++) {
						responses[n] = responseCreators[n % responseCreators.length].create();
					}
					long iend = System.currentTimeMillis();
					initObjectCost.addAndGet(iend - istart);
					
					for (int i = 0; i < Math.pow(10, ic - 2); i++) {
						istart = System.currentTimeMillis();
						for (int n = 0; n < requestCreators.length * 100; n++) {
							requests[n].random();
						}
						for (int n = 0; n < responseCreators.length * 100; n++) {
							responses[n].random();
						}
						iend = System.currentTimeMillis();
						initObjectCost.addAndGet(iend - istart);
						try {
							long s1 = System.currentTimeMillis();
							for (int n = 0; n < requestCreators.length * 100; n++) {
								requestBss[n] = tss[it].encode(requests[n]);
							}
							for (int n = 0; n < responseCreators.length * 100; n++) {
								responseBss[n] = tss[it].encode(responses[n]);
							}
							long e1 = System.currentTimeMillis();
							serializeTime.addAndGet(e1 - s1);

							long s2 = System.currentTimeMillis();
							for (int n = 0; n < requestCreators.length * 100; n++) {
								tss[it].decode(requestBss[n], requests[n].getClass());
							}
							for (int n = 0; n < responseCreators.length * 100; n++) {
								tss[it].decode(responseBss[n], responses[n].getClass());
							}
							long e2 = System.currentTimeMillis();
							deserializeTime.addAndGet(e2 - s2);
						} catch (Exception e) {
							e.printStackTrace();
							System.err.println(Thread.currentThread().getName() + ": " + e.getMessage());
							flag = false;
							break;
						}
					}
				}));
			}

			for (Future future : futures) {
				future.get();
			}
			long end = System.currentTimeMillis();

			int number = new Double(Math.pow(10, c)).intValue();
			if (c != 0) {
//				System.out.println(Math.pow(10, c) + " serialize time: " + serializeTime.get() + ", deserialize time: "
//						+ deserializeTime.get() + " init objects time: " + initObjectCost.get()
//						+ ", total time " + (end - start));
				System.out.println(Math.pow(10, c)
						+ " serialize speed: " + (1000L * number * threadNumber * threadNumber) / serializeTime.get()
						+ ", deserialize speed: " + (1000L * number * threadNumber * threadNumber) / deserializeTime.get()
						+ ", real time " + (end - start));
			}
		}
		executorService.shutdown();
		executorService.awaitTermination(100, TimeUnit.SECONDS);
	}
}
