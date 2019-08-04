package com.fatiny.game.game.config.pojo;

public class ConfigNickname {

	private String suffixName;//昵称后缀
	private String prefixName;//昵称前缀

	public String getSuffixName(){
        return suffixName;
    }
    public void setSuffixName(String suffixName){
        this.suffixName = suffixName;
    }
    
	public String getPrefixName(){
        return prefixName;
    }
    public void setPrefixName(String prefixName){
        this.prefixName = prefixName;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}