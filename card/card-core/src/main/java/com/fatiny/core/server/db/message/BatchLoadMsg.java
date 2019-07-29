package com.fatiny.core.server.db.message;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import com.fatiny.core.client.db.Response;
import com.fatiny.core.data.BasePo;

/**
 * 批量加载(同一个玩家的批量数据加载)
 * 
 * @author huachp
 */
public class BatchLoadMsg extends AbstractDbServerMsg {
	
	/** 业务实体ID */
	private long id;
	/** 实体对象信息 */
	private LinkedHashSet<Class> components = new LinkedHashSet<>();
	/** 加载成功的数据 */
	private Map<Class, List> loadData = new HashMap<>();
	
	public BatchLoadMsg() {
		
	}
	
	public BatchLoadMsg(long id) {
		this.id = id;
	}
	
	public void addComponent(Class<? extends BasePo> cls) {
		components.add(cls);
	}
	
	public LinkedHashSet<Class> getComponents() {
		return components;
	}
	
	public Map<Class, List> getLoadData() {
		return loadData;
	}
	
	public void addData(Class cls, List data) {
		loadData.put(cls, data);
	}
	
	public boolean isEmpty() {
		return components.isEmpty();
	}
	

	@Override
	public long getId() {
		return id;
	}

	@Override
	public Command getCmd() {
		return Command.BATCH_LOAD;
	}
	
	@Override
	public void doResponse(Response response) {
		response.success(this);
	}

}
