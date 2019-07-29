package com.fatiny.core.util;

import java.util.List;

/**
 * ID分配器
 * 
 * @author huachp
 */
public interface IdAllocator {
	
	String name();
	
	long allocate(int areaID);
	
	void init(List<Integer> areaDbs);
}	
