package com.serializer;

import java.io.Serializable;

public interface MySerializer {
	<T extends Serializable> byte[] encode(T obj) throws Exception;
	<T extends Serializable> T decode(byte[] buf, Class<T> cls) throws Exception;
}
