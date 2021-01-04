package com.serializer.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.serializer.MySerializer;

public class HessianSerializer implements MySerializer {
	@Override
	public <T extends Serializable> byte[] encode(T obj) throws Exception {
		if (obj  == null){
            throw new NullPointerException();
        }
		ByteArrayOutputStream out = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(out);
        try{
            ho.writeObject(obj);
            ho.flush();
            return out.toByteArray();
        } catch(Exception ex){
            throw new  RuntimeException("HessianSerializeUtil序列化发生异常!" + ex);
        } finally {
        	ho.close();
        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Serializable> T decode(byte[] buf, Class<T> cls) throws Exception {
		if (buf == null){
            throw new NullPointerException();
        }
		ByteArrayInputStream bis = new ByteArrayInputStream(buf);
        HessianInput hi = new HessianInput(bis);
        try{
            return (T)hi.readObject();
        } catch(Exception ex){
            throw new  RuntimeException("HessianSerializeUtil反序列化发生异常!" + ex);
        } finally {
        	hi.close();
        }
	}
}
