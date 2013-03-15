package com.jerry.minidata.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import weibo4j.model.Status;

import com.jerry.minidata.service.StatusService;

/**
 * 发送微博方面的controller
 * 
 * @author Jerry Weng
 * @since 2013-3-15
 */
public class UpdateController {
	@Autowired
	StatusService statusService;
	
	@RequestMapping(value = "getCommentsToMe.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	public boolean updateNewStatuses(
			@RequestParam(value = "statuses", required = false) String statuses,
			HttpSession session) {
		String token = session.getAttribute("token").toString();
		Status status = statusService.updateStatus(token, statuses);
		return (status != null);
	}

}
