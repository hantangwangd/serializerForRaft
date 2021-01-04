package com.serializer.raft.creator;

import com.serializer.MySerializer;
import com.serializer.impl.HessianSerializer;
import com.serializer.impl.JavaSerializer;
import com.serializer.impl.KryoSerializer;
import com.serializer.impl.ProtostuffSerializer;

public class TestSerializerCreatorFactory {

	public static class JavaSerializerCreator implements TestSerializerCreator {
		public static final JavaSerializerCreator INSTANCE = new JavaSerializerCreator();
		public MySerializer create() {
			return new JavaSerializer();
		}
	}
	
	public static class HessianSerializerCreator implements TestSerializerCreator {
		public static final HessianSerializerCreator INSTANCE = new HessianSerializerCreator();
		public MySerializer create() {
			return new HessianSerializer();
		}
	}
	
	public static class KryoSerializerCreator implements TestSerializerCreator {
		public static final KryoSerializerCreator INSTANCE = new KryoSerializerCreator();
		public MySerializer create() {
			return new KryoSerializer();
		}
	}
	
	public static class kryoSerializerNotWriteClassCreator implements TestSerializerCreator {
		public static final kryoSerializerNotWriteClassCreator INSTANCE = new kryoSerializerNotWriteClassCreator();
		public MySerializer create() {
			return new KryoSerializer(true);
		}
	}
	
	public static class ProtostuffSerializerCreator implements TestSerializerCreator {
		public static final ProtostuffSerializerCreator INSTANCE = new ProtostuffSerializerCreator();
		public MySerializer create() {
			return new ProtostuffSerializer();
		}
	}
}
