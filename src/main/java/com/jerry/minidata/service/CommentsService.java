package com.jerry.minidata.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import weibo4j.Comments;
import weibo4j.Users;
import weibo4j.model.Comment;
import weibo4j.model.CommentWapper;
import weibo4j.model.Paging;
import weibo4j.model.WeiboException;

/**
 * 进行comments相关方面的操作
 * 
 * @author Jerry Weng
 * @since 2013-2-22
 */
@Service
public class CommentsService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(CommentsService.class);

	/**
	 * 获取给当前用户收到的所有评论，按评论数量降序排列
	 * 
	 * @author Jerry Weng
	 * @param token
	 * 			传入当前用户的access_token
	 * @return list 
	 */
	public ArrayList<Map.Entry<String, Integer>> getCommentsToMe(String token) {
		
		ArrayList<Map.Entry<String, Integer>> list =  null;
		// 实例化两个用来获取数据的类
		Comments cm = new Comments();
		cm.client.setToken(token);
		Users um = new Users();
		um.client.setToken(token);
		try {
//			Long loopTimes = getLoopTimes(cm);
			Long loopTimes = 2L;
			Long commentsCount = 0L;
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for (int i = 1; i <= loopTimes; i++) {
				CommentWapper comment = cm.getCommentToMe(new Paging(i, 150),
						0, 0);
				for (Comment c : comment.getComments()) {
					String id = c.getUser().getScreenName();
					commentsCount++;
					if (map.containsKey(id)) {
						Integer count = map.get(id);
						count++;
						map.put(id, count);
					} else {
						map.put(id, 1);
					}

				}

			}

			// 对map中数据按value进行降序排序
			list = new ArrayList<Map.Entry<String, Integer>>(
					map.entrySet());
			Collections.sort(list,
					new Comparator<Map.Entry<String, Integer>>() {
						public int compare(Map.Entry<String, Integer> o1,
								Map.Entry<String, Integer> o2) {
							return (o2.getValue() - o1.getValue());
						}
					});

			
		} catch (WeiboException e) {
			logger.error(e.getMessage(), e);
		}
		
		return list;
	}
	
	private Long getLoopTimes(Comments cm) throws WeiboException {
		CommentWapper comment = cm.getCommentToMe();
		Long totalNumber = comment.getTotalNumber();
		return totalNumber/150 + 1;
	}
}
