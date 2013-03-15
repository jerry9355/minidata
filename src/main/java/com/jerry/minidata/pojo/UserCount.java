package com.jerry.minidata.pojo;


/**
 * 存放一个User对象和一个计数器
 * 
 * @author Jerry Weng
 * @since 2013-3-13
 */
public class UserCount {
	private int count;
	private String id;                      //用户UID
	private String screenName;            //微博昵称
	private String avatarLarge;           //大头像地址
	
	
	
	
	public UserCount(int count, String id, String screenName, String avatarLarge) {
		super();
		this.count = count;
		this.id = id;
		this.screenName = screenName;
		this.avatarLarge = avatarLarge;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getAvatarLarge() {
		return avatarLarge;
	}
	public void setAvatarLarge(String avatarLarge) {
		this.avatarLarge = avatarLarge;
	}
	
	
	
}
