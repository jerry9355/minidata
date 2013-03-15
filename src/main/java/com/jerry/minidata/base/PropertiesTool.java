package com.jerry.minidata.base;

import java.util.ResourceBundle;

public final class PropertiesTool {

	private final static ResourceBundle environment;
	private final static ResourceBundle dealerurl;
	private final static ResourceBundle cityurl;
	private final static ResourceBundle mail;

	// 2012-02-15 区分 NISSAN 与 启辰
	private static ResourceBundle nissanVechile = null;

	static {
		environment = ResourceBundle.getBundle("config.environment");
		dealerurl = ResourceBundle.getBundle("config.dealer");
		cityurl = ResourceBundle.getBundle("config.city");
		mail = ResourceBundle.getBundle("config.mail");

		// 2012-02-15 区分 NISSAN 与 启辰
		nissanVechile = ResourceBundle.getBundle("config.nissanvechile");
	}

	public static String getEnvironmentValue(String key) {
		return environment.getString(key);
	}

	public static String getDealerUrl(String key) {
		return dealerurl.getString(key);
	}

	public static String getCityUrl(String key) {
		return cityurl.getString(key);
	}

	public static String getMailProperty(String key) {
		return mail.getString(key);
	}

	// 2012-02-15 区分 NISSAN 与 启辰
	public static String getNissanVechile(String key) {
		return nissanVechile.getString(key);
	}
}
