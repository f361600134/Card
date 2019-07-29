package com.fatiny.core.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class IpUtil {
	
	static String[] localIp = {//局域网ip的起始
			"10.",
			"192.168.",
			"172.16.",
			"172.17.",
			"172.18.",
			"172.19.",
			"172.20.",
			"172.21.",
			"172.22.",
			"172.23.",
			"172.24.",
			"172.25.",
			"172.26.",
			"172.27.",
			"172.28.",
			"172.29.",
			"172.30.",
			"172.31."
	};
	
	/**
	 * 
	 * @param dest_url
	 *            -- 地址
	 * @param commString
	 *            -- 参数
	 * @param requestMode
	 *            -- 请求方式 POST or GET
	 * @return
	 */
	private static String connectURL(String dest_url, String commString,
			String requestMode) {
		String rec_string = "";

		URL url = null;
		HttpURLConnection urlconn = null;
		try {
			url = new URL(dest_url);
			urlconn = (HttpURLConnection) url.openConnection();
			urlconn.setRequestProperty("content-type", "text/plain");
			urlconn.setRequestMethod(requestMode);
			urlconn.setDoInput(true);
			if (requestMode.equalsIgnoreCase("POST")) {
				urlconn.setDoOutput(true);
				OutputStream out = urlconn.getOutputStream();
				out.write(commString.getBytes("UTF8"));
				out.flush();
				out.close();
			}
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					urlconn.getInputStream()));

			StringBuffer sb = new StringBuffer();
			int ch;
			while ((ch = rd.read()) > -1)
				sb.append((char) ch);
			rec_string = sb.toString();
			rd.close();
			urlconn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return rec_string;
	}
	
	/**
	 * 是否局域网ip
	 * @param ip
	 * @return
	 */
	public static boolean isLocalIp(String ip) {
		//内网IP是以下面几个段开头的: 
		//10.x.x.x
		//192.168.x.x
		//172.16.x.x至172.31.x.x
		for (String ipStart : localIp) {
			if(ip.startsWith(ipStart)){//内网ip不检查
//				System.out.println("内网ip不检查:" + pa.ip);
				return true;
			}
		}
		
		return false;
	}

	/**
	 * 根据ip获取信息
	 * @param ip
	 * @param key:country/region/city...
	 * @return
	 */
	public static String getIpInfo(String ip,String key) {
		try {
			if(isLocalIp(ip)){
				return "局域网ip:" + ip;
			}
			
			String rec = connectURL(
					"http://ip.taobao.com/service/getIpInfo.php?ip=" + ip,"", "GET");
			
			HashMap<?, ?> fromJson = JSON.parseObject(rec, HashMap.class);
			
//		System.out.println("fromJson=" + fromJson.toString());
			//fromJson={code=0, data={"area":"华南","area_id":"800000","city":"惠州市","city_id":"441300","country":"中国","country_id":"CN","county":"","county_id":"-1","ip":"183.61.71.118","isp":"电信","isp_id":"100017","region":"广东省","region_id":"440000"}}
			
			com.alibaba.fastjson.JSONObject dataMap = (com.alibaba.fastjson.JSONObject) fromJson.get("data");

			return dataMap.get(key).toString();
		} catch (Exception e) {
			e.printStackTrace();
			
			return "无法识别的ip:" + ip;
		}
	}
	
	/**
	 * getLocalHostName
	 * @return
	 */
	public static String getLocalHostName() {
		String hostName;
		try {
			InetAddress addr = InetAddress.getLocalHost();
			hostName = addr.getHostName();
		} catch (Exception ex) {
			hostName = "";
		}
		return hostName;
	}

	/**
	 * 获取本地所有ip,包括多网卡
	 * @return
	 */
	public static String[] getAllLocalHostIPv4() {
		System.out.println("开始获取ip地址:");
		List<String> ipList = new ArrayList<>();
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
		    while (netInterfaces.hasMoreElements()) {
		    	NetworkInterface ni = netInterfaces.nextElement();//网络接口
		    	if(ni.isLoopback() || ni.isPointToPoint() || !ni.isUp() || ni.isVirtual()){
		    		System.out.println("无效网卡:" + ni.getDisplayName() 
		    				+ ",isLoopback()=" + ni.isLoopback()
		    				+ ",isPointToPoint()=" + ni.isPointToPoint()
		    				+ ",isUp()=" + ni.isUp()
		    				+ ",isVirtual()=" + ni.isVirtual()
		    				);
		    		continue;
		    	}
//		        System.out.println("DisplayName:" + ni.getDisplayName());
//		        System.out.println("Name:" + ni.getName());  
		        Enumeration<InetAddress> ips = ni.getInetAddresses();
		        while (ips.hasMoreElements()) {
		        	InetAddress ip = ips.nextElement();
		        	if(isIpv6(ip)){
		        		continue;
		        	}
		        	ipList.add(ip.getHostAddress());
					System.out.println("ip=" + ip);
		        }  
		    }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		String[] ret = null;
		if(ipList.size() > 0){
			ret = new String[ipList.size()];
			for (int i = 0; i < ret.length; i++) {
				ret[i] = ipList.get(i);
			}
		}else{
			System.out.println("获取ip地址失败!");
		}
		
		return ret;
	}

	/**
	 * 判断是不是ipv6
	 * @param ip
	 * @return
	 */
	private static boolean isIpv6(InetAddress ip) {
		return (ip instanceof Inet6Address);
	}

	public static void main(String[] args) {
//		System.out.println("ip=" + getIpInfo("121.14.199.236","city"));
		String[] ips = getAllLocalHostIPv4();
		if(ips != null){
			for (String ip : ips) {
				System.out.println("ip=" + ip);
			}
		}
	}

}
