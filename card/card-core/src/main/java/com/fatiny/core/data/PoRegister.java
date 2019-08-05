package com.fatiny.core.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fatiny.core.server.db.dao.TableObject;
import com.fatiny.core.util.ClassPathScanner;

/**
 * po注册
 */
public class PoRegister {

	private final static Map<Class<?>, TableObject> TABLE_OBJECTS = new HashMap<>();

	public static Set<Class<?>> scan(String path) {
		ClassPathScanner scan = new ClassPathScanner(false, true, null);
		Set<Class<?>> classes = scan.getPackageAllClasses(path, true);
		for (Class<?> cls : classes) {
			if (BasePo.class.isAssignableFrom(cls)) {
				PO po = cls.getAnnotation(PO.class);
				if (po == null) {
					continue;
				}
				TableObject table = new TableObject((Class<? extends BasePo>) cls);
				synchronized (TABLE_OBJECTS) {
					TABLE_OBJECTS.put(cls, table);
				}
			}
		}
		return TABLE_OBJECTS.keySet();
	}

	public static TableObject getTableObject(Class<?> cls){
		return TABLE_OBJECTS.get(cls);
	}

}
