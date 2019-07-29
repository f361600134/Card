package com.fatiny.core.akka.remote;

import java.util.List;
import java.util.Properties;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fatiny.core.util.GameLog;
import com.fatiny.core.util.XMLUtils;

/**
 * actor服务配置
 * 
 * @author huachp
 */
public class ActorServerConfig {
	
	private static final String PATH = "config/akka/actor-remote.xml";
	
	private static ActorServerConfig instance = new ActorServerConfig();
	
	private Properties config;
	
	ActorServerConfig() {
		
	}
	
	public void loadConfig() {
		GameLog.info("初始化actor server配置 -> {}", PATH);
		Element element = XMLUtils.getElementMyXML(PATH);
        NodeList fatherNodes = element.getChildNodes();		// 父节点<dbConfig>
		List<Node> childNodes1 = XMLUtils.getAllChildNodesMyXML(fatherNodes); // 一级子节点
		
		Properties p = new Properties();
        for (int i = 0; i < childNodes1.size(); i++) {
        	Node node = childNodes1.get(i);
    		NamedNodeMap nodeMap = node.getAttributes();
    		if (nodeMap == null) {
    			continue;
    		}
    		p.setProperty(nodeMap.getNamedItem("key").getNodeValue(), 
    					nodeMap.getNamedItem("value").getNodeValue());
		}
        this.config = p;
        GameLog.info("actor server配置初始化完成");
	}
	
	
	public Properties getConfig() {
		return config;
	}
	

	public String getIp() {
		return config.getProperty("ip");
	}
	
	public int getPort() {
		return Integer.parseInt(config.getProperty("port"));
	}
	
	public int getActorCount() {
		return Integer.parseInt(config.getProperty("actorCount"));
	}
	
	
	public static ActorServerConfig instance() {
		return instance;
	}
	
}
