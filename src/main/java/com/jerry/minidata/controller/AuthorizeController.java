package com.jerry.minidata.controller;

import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sun.misc.BASE64Decoder;
import weibo4j.org.json.JSONObject;

@Controller
public class AuthorizeController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(AuthorizeController.class);
	
	@RequestMapping(value = "/index.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(@RequestParam(value="signed_request", required=false) String sign, ModelMap model, HttpSession session) {
		String token = decodeSignCode(sign);
		String sessionId = session.getId();
		
		//将token存入session中
		session.setAttribute("token", token);
		
		model.put("sessionId", sessionId);
		model.put("token", token);
		return "index";
	}
	
	/**
	 * 解码base64url 编码的sign_code 从中获取token
	 * 
	 * @author Jerry Weng
	 * @param signCode
	 * @return
	 */
	private String decodeSignCode(String signCode) {
		// 解析base64url
		int count = 0;
		String playLoad = null;
		StringTokenizer st = new StringTokenizer(signCode, ".");
		String token = null;
		while (st.hasMoreTokens()) {
			if (count == 1) {
				playLoad = st.nextToken();
				break;
			} else {
				st.nextToken();
			}

			count++;
		}

		BASE64Decoder decoder = new BASE64Decoder();
		playLoad = playLoad.replace("-", "+").replace("_", "/").trim();

		try {
			byte[] decodedPlayload = decoder.decodeBuffer(playLoad);
			playLoad = new String(decodedPlayload, "UTF8");

			JSONObject jObject = new JSONObject(playLoad);
			token = jObject.get("oauth_token").toString();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		
		return token;
	}

}
