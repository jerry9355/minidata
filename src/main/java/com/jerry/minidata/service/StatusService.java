package com.jerry.minidata.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import weibo4j.Timeline;
import weibo4j.model.Status;

/**
 * 进行微博发送方面的操作
 * 
 * @author Jerry Weng
 * @since 2013-3-15
 */
@Service
public class StatusService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(StatusService.class);
	
	/**
	 * 发送新微博
	 * 
	 * @author Jerry Weng
	 * @param token
	 * 			token令牌
	 * @param statuses
	 * 			微博内容，需要进行URLEncode
	 * @return
	 */
	public Status updateStatus(String token, String statuses) {
		Timeline tm = new Timeline();
		tm.client.setToken(token);
		Status status = null;
		try {
			status = tm.UpdateStatus(statuses);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return status;
	}
}
