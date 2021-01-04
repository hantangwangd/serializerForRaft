package com.serializer.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.serializer.MySerializer;

public class JavaSerializer implements MySerializer {
	@Override
	public <T extends Serializable> byte[] encode(T obj) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(out);
		try {
			oo.writeObject(obj);
			oo.flush();
			byte[] buffer = out.toByteArray();
			return buffer;
		} finally {
			oo.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Serializable> T decode(byte[] buf, Class<T> cls) throws Exception {
		ObjectInputStream ii = new ObjectInputStream(new ByteArrayInputStream(buf));
		T res = (T)ii.readObject();
		ii.close();
		return res;
	}
}
