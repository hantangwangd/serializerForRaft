package com.serializer.utils;

import java.util.List;

public class CollectionUtils {
	public static <T> boolean equals(List<T> list1, List<T> list2) {
		if (list1 == null && list2 == null) {
			return true;
		} else if (list1 == null || list2 == null) {
			return false;
		}
		
		if (list1.size() == list2.size()) {
			for (int i = 0; i < list1.size(); i++) {
    			T t1 = list1.get(i);
    			T t2 = list2.get(i);
    			if (!t1.equals(t2)) {
    				return false;
    			}
    		}
    		return true;
		}
		return false;
	}
}
