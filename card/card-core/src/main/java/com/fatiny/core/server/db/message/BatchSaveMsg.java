package com.fatiny.core.server.db.message;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Objects;

import com.fatiny.core.data.BasePo;


/**
 * 批量保存
 * 
 * @author huachp
 */
public class BatchSaveMsg extends AbstractDbServerMsg {
	
	/** 业务实体ID */
	private long id;
	/** 实体列表 */
	private LinkedHashMap<PoInfo, Object> components = new LinkedHashMap<>();
	
	public BatchSaveMsg() {
		
	}
	
	public BatchSaveMsg(long id) {
		this.id = id;
	}
	
	
	public void addComponent(Command cmd, BasePo basePo) {
		PoInfo poInfo = new PoInfo(cmd, basePo);
		components.put(poInfo, true);
	}
	
	
	public Iterator<PoInfo> getComponents() {
		return components.keySet().iterator();
	}
	
	
	public int size() {
		return components.size();
	}
	
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public Command getCmd() {
		return Command.BATCH_SAVE;
	}
	
	
	public static class PoInfo {
		
		private Command cmd;
		private BasePo po;
		
		public PoInfo() {}
		
		PoInfo(Command cmd, BasePo po) {
			this.cmd = cmd;
			this.po = po;
		}

		public Command getCmd() {
			return cmd;
		}

		public BasePo getPo() {
			return po;
		}

		@Override
		public int hashCode() {
			return cmd.hashCode() + po.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			PoInfo other = (PoInfo) obj;
			if (this.cmd != other.cmd) {
				return false;
			}
			if (this.po.getClass() != other.po.getClass()) {
				return false;
			}
			if (this.po == other.po) {
				return true;
			}
			if (Objects.deepEquals(this.po.idValues(), other.po.idValues())) {
				return true;
			}
			return false;
		}

	}
	
}
