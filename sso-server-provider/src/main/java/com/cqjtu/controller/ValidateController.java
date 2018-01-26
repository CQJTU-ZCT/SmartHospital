package com.cqjtu.controller;

import javax.servlet.http.HttpServletRequest;

import com.cqjtu.messages.Message;
import com.cqjtu.messages.ValidateMessage;
import com.cqjtu.model.Users;
import com.cqjtu.tools.TokenData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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


	private String originalUrlString ="originalUrl";


	@RequestMapping(value = {"/validate","/validate/"},method = RequestMethod.GET)
	public ValidateMessage validateUser( String token,HttpServletRequest request,
										 String originalUrl) {
		ValidateMessage message;
		if (token == null || token.length() <=0){
			token = request.getHeader("token");
		}
		if (token == null){
			message = ValidateMessage.getFailMessage();
		}else {
			Users user = TokenData.validateToken(token);
			if (user == null) {
				message = ValidateMessage.getFailMessage();
			}else {
				// 防止密码泄露
				user.setPassword("********刮开查看密码*****");
				message = ValidateMessage.getSuccessMessage();
				message.put("user",user);
				if (originalUrl != null){
					message.put(originalUrlString,originalUrl);
				}
			}
		}
		return message;
	}




}
