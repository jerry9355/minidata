package com.jerry.minidata.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import weibo4j.Comments;
import weibo4j.Timeline;
import weibo4j.Users;
import weibo4j.model.Comment;
import weibo4j.model.CommentWapper;
import weibo4j.model.Paging;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

import com.jerry.minidata.pojo.UserCount;

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
	
	//分页读取时每页数据条数
	private static final int COUNTS_PER_PAGE = 200;

	/**
	 * 获取给当前用户收到的所有评论，按评论数量降序排列
	 * 
	 * @author Jerry Weng
	 * @param token
	 * 			传入当前用户的access_token
	 * @return list 
	 */
	public ArrayList<Map.Entry<String, UserCount>> getCommentsToMe(String token) {
		
		ArrayList<Map.Entry<String, UserCount>> list =  null;
		// 实例化两个用来获取数据的类
		Comments cm = new Comments();
		cm.client.setToken(token);
		Users um = new Users();
		um.client.setToken(token);
		try {
//			Long loopTimes = getLoopTimes(cm);
			//测试用，只循环2次，读取400条
			Long loopTimes = 2L;
			HashMap<String, UserCount> map = new HashMap<String, UserCount>();
			for (int i = 1; i <= loopTimes; i++) {
				CommentWapper comment = cm.getCommentToMe(new Paging(i, COUNTS_PER_PAGE),
						0, 0);
				for (Comment c : comment.getComments()) {
					User user= c.getUser();
					String id = user.getScreenName();
					if (map.containsKey(id)) {
						UserCount userCount = map.get(id);
						userCount.setCount(userCount.getCount() + 1);
						map.put(id, userCount);
					} else {
						UserCount userCount = new UserCount(1, user.getId(), user.getScreenName(), user.getavatarLarge());
						map.put(id, userCount);
					}

				}

			}

			// 对map中数据按value进行降序排序
			list = new ArrayList<Map.Entry<String, UserCount>>(
					map.entrySet());
			Collections.sort(list,
					new Comparator<Map.Entry<String, UserCount>>() {
						public int compare(Map.Entry<String, UserCount> o1,
								Map.Entry<String, UserCount> o2) {
							return (o2.getValue().getCount() - o1.getValue().getCount());
						}
					});

			
		} catch (WeiboException e) {
			logger.error(e.getMessage(), e);
		}
		
		return list;
	}
	
public ArrayList<Map.Entry<String, UserCount>> getMentions(String token) {
		
		ArrayList<Map.Entry<String, UserCount>> list =  null;
		// 实例化两个用来获取数据的类
		Timeline tl = new Timeline();
		tl.client.setToken(token);
		try {
//			Long loopTimes = getLoopTimes(cm);
			//测试用，只循环2次，读取400条
			Long loopTimes = 2L;
			HashMap<String, UserCount> map = new HashMap<String, UserCount>();
			for (int i = 1; i <= loopTimes; i++) {
				StatusWapper status = tl.getMentions(new Paging(i, COUNTS_PER_PAGE),
						0, 0, 0);
				for (Status s : status.getStatuses()) {
					User user= s.getUser();
					String id = user.getScreenName();
					if (map.containsKey(id)) {
						UserCount userCount = map.get(id);
						userCount.setCount(userCount.getCount() + 1);
						map.put(id, userCount);
					} else {
						UserCount userCount = new UserCount(1, user.getId(), user.getScreenName(), user.getavatarLarge());
						map.put(id, userCount);
					}

				}

			}

			// 对map中数据按value进行降序排序
			list = new ArrayList<Map.Entry<String, UserCount>>(
					map.entrySet());
			Collections.sort(list,
					new Comparator<Map.Entry<String, UserCount>>() {
						public int compare(Map.Entry<String, UserCount> o1,
								Map.Entry<String, UserCount> o2) {
							return (o2.getValue().getCount() - o1.getValue().getCount());
						}
					});

			
		} catch (WeiboException e) {
			logger.error(e.getMessage(), e);
		}
		
		return list;
	}
	
	@SuppressWarnings("unused")
	private Long getLoopTimes(Comments cm) throws WeiboException {
		CommentWapper comment = cm.getCommentToMe();
		Long totalNumber = comment.getTotalNumber();
		return totalNumber/COUNTS_PER_PAGE + 1;
	}
}
