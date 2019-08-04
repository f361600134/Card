package com.fatiny.game.game.config.pojo;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fatiny.game.util.DateUtils;


public class ConfigActivityControl {
	
	public static Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControl.class);

	private int ID;//活动id
	private int version;//版本号
	private int type;//活动类型
	private String timecnf;//活动时间配置
	private int open;//是否开放

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getVersion(){
        return version;
    }
    public void setVersion(int version){
        this.version = version;
    }
    
	public int getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
    
	public String getTimecnf(){
        return timecnf;
    }
    public void setTimecnf(String timecnf){
        this.timecnf = timecnf;
    }
    
	public int getOpen(){
        return open;
    }
    public void setOpen(int open){
        this.open = open;
    }
    

	////////////////////// 特殊扩展 //////////////
	public void parse(){
    }
	
	
	/////////UserDefine Begin///////////
	private static int TYPE_FOREVER = 0; //永久类型
    private static int TYPE_RANGE = 1; //日期范围类型
	
	private long activityOpenTime;// 活动开启时间
	private long activityCloseTime;// 活动结束时间 
	private String showTime = "";

	public long getActivityOpenTime() {
		return activityOpenTime;
	}
	public void setActivityOpenTime(long activityOpenTime) {
		this.activityOpenTime = activityOpenTime;
	}
	public long getActivityCloseTime() {
		return activityCloseTime;
	}
	public void setActivityCloseTime(long activityCloseTime) {
		this.activityCloseTime = activityCloseTime;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	
	/**
	 * 无限期
	 * @return  
	 * @return boolean  
	 * @date 2019年4月18日下午2:50:10
	 */
	public boolean indefinite() {
		return type == 0 && activityOpenTime <= 0 && activityCloseTime <= 0;
	}
	/**
	 * 判断是否在活动时间内
	 * @return  
	 * @return boolean  
	 * @date 2019年4月18日下午2:50:15
	 */
	public boolean checkOpen() {
		if (open <= 0) //检测是否开启
			return false;
		boolean bool = indefinite(); //检测是否永久开启
		if (bool)
			return bool;
		long now = System.currentTimeMillis();
		return activityOpenTime < now && now < activityCloseTime; //判断是否在有效期内
	}
	
	/**
	 * 解析配置文件	
	 * @return void  
	 * @date 2019年5月9日上午10:38:33
	 */
	public void parseAfter() {
		if (type == TYPE_FOREVER) { //永久类型
			activityOpenTime = -1;
			activityCloseTime = -1;
		}else if(type == TYPE_RANGE && !StringUtils.isBlank(timecnf)){//日期范围
			String[] strArr = timecnf.split("\\|");
			Date openDate = DateUtils.parse(strArr[0], DateUtils.FORMAT_FULL);
			Date closeDate = DateUtils.parse(strArr[1], DateUtils.FORMAT_FULL);
			activityOpenTime = openDate.getTime();
			activityCloseTime = closeDate.getTime();
			
			String open =  DateUtils.format(openDate, DateUtils.FORMAT_DATE_HYPHEN);
			String close =  DateUtils.format(closeDate, DateUtils.FORMAT_DATE_HYPHEN);
			showTime = open.concat("~").concat(close);
			
		}else {
			LOGGER.info("无法解析的事件类型, type:{}, timecnf:{}", type, timecnf);
		}
	}
	
	/////////UserDefine End/////////////
	
}