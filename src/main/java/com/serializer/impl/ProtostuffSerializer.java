package com.serializer.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import com.serializer.MySerializer;
import com.serializer.utils.ClassInstanceEnhancer;

import io.protostuff.*;
import io.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class ProtostuffSerializer implements MySerializer {
    private static final Set<Class<?>> WRAPPER_SET = new HashSet<>();

    /**
     * 序列化/反序列化包装类 Class 对象
     */
    private static final Class<SerializeDeserializeWrapper> WRAPPER_CLASS = SerializeDeserializeWrapper.class;

    /**
     * 序列化/反序列化包装类 Schema 对象
     */
    private static final Schema<SerializeDeserializeWrapper> WRAPPER_SCHEMA = RuntimeSchema.createFrom(WRAPPER_CLASS);

    /**
     * 缓存对象及对象schema信息集合
     */
    private static final Map<Class<?>, Schema<?>> CACHE_SCHEMA = new HashMap<>(64);

//        private static final ClassInstanceEnhancer instanceCreator = new ClassInstanceEnhancer();
    private static final Objenesis instanceCreator = new ObjenesisStd(true);

    /**
     * 构建LinkedBuffer资源管理池
     * */
    private static final ProtostuffLinkedBufferPool linkedBufferPool = new ProtostuffLinkedBufferPool();

    /**
     * 预定义一些Protostuff无法直接序列化/反序列化的对象
     */
    static {
        WRAPPER_SET.add(List.class);
        WRAPPER_SET.add(ArrayList.class);
        WRAPPER_SET.add(CopyOnWriteArrayList.class);
        WRAPPER_SET.add(LinkedList.class);
        WRAPPER_SET.add(Stack.class);
        WRAPPER_SET.add(Vector.class);

        WRAPPER_SET.add(Map.class);
        WRAPPER_SET.add(HashMap.class);
        WRAPPER_SET.add(TreeMap.class);
        WRAPPER_SET.add(Hashtable.class);
        WRAPPER_SET.add(SortedMap.class);
        WRAPPER_SET.add(Map.class);

        WRAPPER_SET.add(Object.class);
    }

    /**
     * 注册需要使用包装类进行序列化/反序列化的 Class 对象
     *
     * @param clazz 需要包装的类型 Class 对象
     */
    public static void registerWrapperClass(Class<?> clazz) {
        WRAPPER_SET.add(clazz);
    }

    /**
     * 获取序列化对象类型的schema
     *
     * @param cls 序列化对象的class
     * @param <T> 序列化对象的类型
     * @return 序列化对象类型的schema
     */
    @SuppressWarnings({"unchecked"})
    private static <T> Schema<T> getSchema(Class<T> cls) {
        Schema schema = CACHE_SCHEMA.get(cls);
        if (schema == null) {
            synchronized (CACHE_SCHEMA) {
                schema = CACHE_SCHEMA.computeIfAbsent(cls, k -> RuntimeSchema.createFrom(k));
            }
        }
        return schema;
    }
    
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Serializable> byte[] encode(T obj) throws Exception {
        return encodeWithoutPool(obj);
	}

	@Override
	public <T extends Serializable> T decode(byte[] buf, Class<T> cls) throws Exception {
		try {
            if (!WRAPPER_SET.contains(cls)) {
            	T message = instanceCreator.newInstance(cls);
                Schema<T> schema = getSchema(cls);
                ProtostuffIOUtil.mergeFrom(buf, message, schema);
                return message;
            } else {
                SerializeDeserializeWrapper<T> wrapper = new SerializeDeserializeWrapper<>();
                ProtostuffIOUtil.mergeFrom(buf, wrapper, WRAPPER_SCHEMA);
                return wrapper.getData();
            }
        } catch (Exception e) {
            throw new IllegalStateException("反序列化对象异常 [" + cls.getName() + "]", e);
        }
	}

    private <T extends Serializable> byte[] encodeWithPool(T obj) throws Exception {
        return linkedBufferPool.run(buffer -> {
            Class<T> clazz = (Class<T>) obj.getClass();
            try {
                Object serializeObject = obj;
                Schema schema = WRAPPER_SCHEMA;
                if (!WRAPPER_SET.contains(clazz)) {
                    schema = getSchema(clazz);
                } else {
                    serializeObject = SerializeDeserializeWrapper.builder(obj);
                }
                return ProtostuffIOUtil.toByteArray(serializeObject, schema, buffer);
            } catch (Exception e) {
                throw new IllegalStateException("序列化对象异常 [" + obj + "]", e);
            }
        }, LinkedBuffer.DEFAULT_BUFFER_SIZE);
    }

    private <T extends Serializable> byte[] encodeWithoutPool(T obj) throws Exception {
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        Class<T> clazz = (Class<T>) obj.getClass();
        try {
            Object serializeObject = obj;
            Schema schema = WRAPPER_SCHEMA;
            if (!WRAPPER_SET.contains(clazz)) {
                schema = getSchema(clazz);
            } else {
                serializeObject = SerializeDeserializeWrapper.builder(obj);
            }
            return ProtostuffIOUtil.toByteArray(serializeObject, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException("序列化对象异常 [" + obj + "]", e);
        } finally {
            buffer.clear();
        }
    }
}
