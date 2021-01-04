package com.serializer.impl;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.serializer.MySerializer;

public class FastJsonSerializer implements MySerializer {

	@Override
	public <T extends Serializable> byte[] encode(T obj) throws Exception {
		if (obj  == null){
            throw new NullPointerException();
        }

        String json = JSON.toJSONString(obj);
        byte[] data = json.getBytes();
        return data;
	}

	@Override
	public <T extends Serializable> T decode(byte[] buf, Class<T> cls) throws Exception {
		T obj = JSON.parseObject(new String(buf),cls);
        return obj;
	}

}
