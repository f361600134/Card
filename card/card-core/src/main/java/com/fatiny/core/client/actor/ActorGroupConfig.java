package com.fatiny.core.client.actor;

import java.util.Collection;
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
 * Actor配置 
 * 
 * @author huachp
 */
public class ActorGroupConfig {
	
	private static final String PATH = "config/akka/actor-mapping.xml";
	
	private static ActorGroupConfig groupConfig = new ActorGroupConfig();
	
	public static ActorGroupConfig instance() {
		return groupConfig;
	}
	
	/** {Actor名字=Actor远程配置} */
	private Map<String, ActorConfig> configs = new HashMap<>();
	
	public void loadConfig() {
		GameLog.info("加载Actor远程配置, {}", PATH);
		Element element = XMLUtils.getElementMyXML(PATH);
        NodeList fatherNodes = element.getChildNodes();		// 父节点
		List<Node> childNodes1 = XMLUtils.getAllChildNodesMyXML(fatherNodes); // 一级子节点
        for (int i = 0; i < childNodes1.size(); i++) {
        	Node node = childNodes1.get(i);
        	String nodeName = node.getNodeName().trim();
        	String actorName = nodeName.replace("-", "");
        	
        	ActorConfig actorConfig = getActorConf(actorName);
			List<Node> childNodes2 = XMLUtils
					.getAllChildNodesMyXML(node.getChildNodes()); // 二级子节点
        	if (childNodes2.isEmpty()) {
        		continue;
			}
        	
        	Properties p = new Properties();
        	for (int j = 0; j < childNodes2.size(); j++) {
        		NamedNodeMap nodeMap = childNodes2.get(j).getAttributes();
        		if (nodeMap == null) {
					continue;
				}
        		String key = nodeMap.getNamedItem("key").getNodeValue();
        		String value = nodeMap.getNamedItem("value").getNodeValue();
        		p.setProperty(key, value);
			}
        	
        	int serverId = Integer.parseInt(p.getProperty("id"));
        	actorConfig.put(serverId, p);
		}
        GameLog.info("Actor远程配置加载完成");
	}
	
	private ActorConfig getActorConf(String actorName) {
		ActorConfig actorConf = configs.get(actorName);
		if (actorConf == null) {
			actorConf = new ActorConfig(actorName);
			configs.put(actorName, actorConf);
		}
		return actorConf;
	}
	
	public ActorConfig getConfig(String actorName) {
		return configs.get(actorName);
	}
	
	public Collection<String> getActorNames() {
		return configs.keySet();
	}
	
}
