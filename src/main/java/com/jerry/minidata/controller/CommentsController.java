package com.jerry.minidata.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jerry.minidata.pojo.UserCount;
import com.jerry.minidata.service.CommentsService;
/**
 * 获取用户评论方面的数据
 * 
 * @author Jerry Weng
 * @since 2013-2-22
 */
@Controller
public class CommentsController {
	
	@Resource
	CommentsService commentsService;
	
	/**
	 * 获取给我的评论中评论人数最多的前三人
	 * 
	 * @author Jerry Weng
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "getCommentsToMe.do", 
			method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, UserCount> getCommentsToMe(
			HttpSession session) {
		String token = session.getAttribute("token").toString();
		
		ArrayList<Map.Entry<String, UserCount>> list = commentsService
				.getCommentsToMe(token);
		Map<String, UserCount> map 
				= new HashMap<String, UserCount>(3);
		
		map.put("first", list.get(0).getValue());
		map.put("second", list.get(1).getValue());
		map.put("third", list.get(2).getValue());
		return map;
	}
	
	@RequestMapping(value = "getMentions.do", 
			method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, UserCount> getMentions(
			HttpSession session) {
		String token = session.getAttribute("token").toString();
		ArrayList<Map.Entry<String, UserCount>> list = commentsService
				.getMentions(token);
		Map<String, UserCount> map 
				= new HashMap<String, UserCount>(3);
		map.put("first", list.get(0).getValue());
		map.put("second", list.get(1).getValue());
		map.put("third", list.get(2).getValue());
		return map;
	} 

}
