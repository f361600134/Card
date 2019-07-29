package com.fatiny.core.server.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fatiny.core.util.GameLog;
import com.fatiny.core.util.XMLUtils;

/**
 * 数据服配置
 * 
 * @author huachp
 */
public class DbServerConfig {

	private final static Logger logger = LoggerFactory.getLogger(DbServerConfig.class);
	
	private static final String PATH = "config/db/db-mapping.xml";
	
	private static DbServerConfig instance = new DbServerConfig();
	
	/** jedis连接池配置 */
	private Properties jedisPoolCfg;
	/** druid连接池配置 */
	private Map<Integer, Properties> dataSourceCfgs = new HashMap<>();
	
	private DbServerConfig() {}
	
	public void loadConfig() {
		logger.info("初始化数据服配置 -> {}", PATH);
		Element element = XMLUtils.getElementMyXML(PATH);
        NodeList fatherNodes = element.getChildNodes();		// 父节点<dbConfig>
		List<Node> childNodes1 = XMLUtils.getAllChildNodesMyXML(fatherNodes); // 一级子节点
        for (int i = 0; i < childNodes1.size(); i++) {
        	Node node = childNodes1.get(i);
			List<Node> childNodes2 = XMLUtils.getAllChildNodesMyXML(node.getChildNodes()); // 二级子节点
        	if (childNodes2.isEmpty()) {
        		continue;
			}
        	String dbName = node.getNodeName();
        	Properties p = new Properties();
        	for (int j = 0; j < childNodes2.size(); j++) {
        		NamedNodeMap nodeMap = childNodes2.get(j).getAttributes();
        		if (nodeMap == null) {
        			continue;
        		}
        		p.setProperty(nodeMap.getNamedItem("key").getNodeValue(), 
        					nodeMap.getNamedItem("value").getNodeValue());
			}
        	if (dbName.equals("redis")) {
        		jedisPoolCfg = p;
			} else if (dbName.equals("mysql")) {
        		int serverId = Integer.parseInt(p.getProperty("areaDbId"));
    			dataSourceCfgs.put(serverId, p);
			}
		}
        GameLog.info("数据服配置初始化完成");
	}

	
	public Map<Integer, Properties> getDataSourceCfgs() {
		return dataSourceCfgs;
	}

	public Properties getDataSourceCfg(int serverId) {
		return dataSourceCfgs.get(serverId);
	}

	public Properties getJedisPoolCfg() {
		return jedisPoolCfg;
	}
		
	
	public static DbServerConfig getInstance() {
		return instance;
	}
	
}
