package com.serializer.impl;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import org.objenesis.strategy.StdInstantiatorStrategy;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.pool.KryoPool;
import com.serializer.MySerializer;

public class KryoSerializer implements MySerializer {
	public static final int DEFAULT_BUFFER_SIZE = 4096;
	KryoPool pool;
	private final KryoOutputPool kryoOutputPool = new KryoOutputPool();
	private final KryoInputPool kryoInputPool = new KryoInputPool();
	boolean preRegisterClass = false;

	public KryoSerializer() {
		pool = new KryoPool.Builder(() -> {
			final Kryo kryo = new Kryo();
			kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(
					new StdInstantiatorStrategy()));
			kryo.setReferences(false); // 关闭循环引用检查
			return kryo;
		}).softReferences().build();
		pool.release(pool.borrow());
	}

	public KryoSerializer(boolean preRegisterClass) {
		this.preRegisterClass = preRegisterClass;
		pool = new KryoPool.Builder(() -> {
            final Kryo kryo = new Kryo();
            kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(
                    new StdInstantiatorStrategy()));
            kryo.setReferences(false);	//关闭循环引用检查
            if (preRegisterClass) {
            	KryoRegisterUtils.register(kryo);
            }
            return kryo;
        }).softReferences().build();
		pool.release(pool.borrow());
	}

	@Override
	public <T extends Serializable> byte[] encode(T obj) throws Exception {
		return kryoOutputPool.run(output -> {
			return pool.run(kryo -> {
				kryo.writeClassAndObject(output, obj);
				output.flush();
				return output.getByteArrayOutputStream().toByteArray();
			});
		}, DEFAULT_BUFFER_SIZE);
	}

	@Override
	public <T extends Serializable> T decode(byte[] buf, Class<T> cls) throws Exception {
		return kryoInputPool.run(input -> {
			input.setInputStream(new ByteArrayInputStream(buf));
			return pool.run(kryo -> {
				@SuppressWarnings("unchecked")
				T obj = (T) kryo.readClassAndObject(input);
				return obj;
			});
		}, DEFAULT_BUFFER_SIZE);
	}
}
