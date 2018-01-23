package com.cqjtu.controller;

import javax.servlet.http.HttpServletRequest;

import com.cqjtu.messages.ValidateMessage;
import com.cqjtu.model.Users;
import com.cqjtu.tools.TokenData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * <p>ClassName:ValidateController</p>
 * <p>Description:用户合法性验证</p> 
 * @author ZJH
 * @date 2017年5月29日下午12:15:49
 */
@RestController
public class ValidateController {


	private String originalUrl ="originalUrl";


	@RequestMapping("/validate/{token}")
	public ValidateMessage validateUser(@PathVariable("token") String token,HttpServletRequest request) {
		Users user = TokenData.validateToken(token);
		if (user == null) {
			ValidateMessage failMessage = ValidateMessage.getFailMessage();
			if (request.getHeader(originalUrl) != null){
				failMessage.put(originalUrl,request.getHeader(originalUrl));
			}
			return failMessage;
		}
		// 防止密码泄露
		user.setPassword("********刮开查看密码*****");
		ValidateMessage message = ValidateMessage.getSuccessMessage();
		message.put("user",user);
		if (request.getHeader(originalUrl) != null){
			message.put(originalUrl,request.getHeader(originalUrl));
		}

		return message;
	}


	/**
	 * @param request
	 * @return
	 */
	@RequestMapping("/validate")
	public ValidateMessage validateUserWithoutToken(HttpServletRequest request) {
		String token = request.getHeader("token");
		ValidateMessage message;
		if (token == null){
			 message = ValidateMessage.getFailMessage();
			if (request.getHeader(originalUrl)!=null){
				message.put(originalUrl,request.getHeader(originalUrl));
			}
		}else {
			Users user = TokenData.validateToken(token);
			if (user == null) {
				message = ValidateMessage.getFailMessage();
				if (request.getHeader(originalUrl)!=null){
					message.put(originalUrl,request.getHeader(originalUrl));
				}
			}else {
				// 防止密码泄露
				user.setPassword("********刮开查看密码*****");
				message = ValidateMessage.getSuccessMessage();
				message.put("user",user);
				if (request.getHeader(originalUrl)!=null){
					message.put(originalUrl,request.getHeader(originalUrl));
				}
			}
		}
		return message;
	}


}
