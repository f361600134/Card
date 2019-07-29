package com.fatiny.core.client.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fatiny.core.util.GameLog;
import com.fatiny.core.util.XMLUtils;

/**
 * 数据服客户端配置
 * 
 * @author huachp
 */
public class DbServerClientConfig {
	
	private static final String PATH = "config/main/dbserver-mapping.xml";
	
	private static DbServerClientConfig config = new DbServerClientConfig();
	
	public static DbServerClientConfig instance() {
		return config;
	}
	
	
	/** {数据服标识=数据服配置} */
	private Map<Integer, Properties> dbServerConfigs = new HashMap<>();
	
	public void loadConfig() {
		GameLog.info("加载(游戏服->数据服)映射表, {}", PATH);
		Element element = XMLUtils.getElementMyXML(PATH);
        NodeList fatherNodes = element.getChildNodes();		// 父节点<dbConfig>
		List<Node> childNodes1 = XMLUtils.getAllChildNodesMyXML(fatherNodes); // 一级子节点
        for (int i = 0; i < childNodes1.size(); i++) {
        	Node node = childNodes1.get(i);
			List<Node> childNodes2 = XMLUtils.getAllChildNodesMyXML(node.getChildNodes()); // 二级子节点
        	if (childNodes2.isEmpty()) {
        		continue;
			}
        	Properties p = new Properties();
        	for (int j = 0; j < childNodes2.size(); j++) {
        		NamedNodeMap nodeMap = childNodes2.get(j).getAttributes();
        		if (nodeMap == null) {
					continue;
				}
        		p.setProperty(nodeMap.getNamedItem("key").getNodeValue(), 
        				nodeMap.getNamedItem("value").getNodeValue());
			}
        	
        	int dbServerId = Integer.parseInt(p.getProperty("id"));
        	dbServerConfigs.put(dbServerId, p);
		}
        GameLog.info("(游戏服->数据服)映射表加载完成");
	}
	
	
	public String getIp(int dbServerId) {
		Properties config = dbServerConfigs.get(dbServerId);
		return config.getProperty("ip");
	}
	
	public int getPort(int dbServerId) {
		Properties config = dbServerConfigs.get(dbServerId);
		return Integer.parseInt(config.getProperty("port"));
	}
	
	public List<Integer> allServerIds() {
		return new ArrayList<>(dbServerConfigs.keySet());
	}
	
}
