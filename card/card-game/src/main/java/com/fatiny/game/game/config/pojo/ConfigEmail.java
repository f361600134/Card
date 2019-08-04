package com.fatiny.game.game.config.pojo;

public class ConfigEmail {

	private int ID;//序号
	private String Sender;//发件人
	private String EmailSubject;//邮件主题
	private int StorageTime;//邮件保存时间
	private String EmailContent;//邮件内容

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public String getSender(){
        return Sender;
    }
    public void setSender(String Sender){
        this.Sender = Sender;
    }
    
	public String getEmailSubject(){
        return EmailSubject;
    }
    public void setEmailSubject(String EmailSubject){
        this.EmailSubject = EmailSubject;
    }
    
	public int getStorageTime(){
        return StorageTime;
    }
    public void setStorageTime(int StorageTime){
        this.StorageTime = StorageTime;
    }
    
	public String getEmailContent(){
        return EmailContent;
    }
    public void setEmailContent(String EmailContent){
        this.EmailContent = EmailContent;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}