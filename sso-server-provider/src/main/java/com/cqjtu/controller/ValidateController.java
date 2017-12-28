package com.cqjtu.controller;

import javax.servlet.http.HttpServletRequest;

import com.cqjtu.domain.User;
import com.cqjtu.messages.ValidateMessage;
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

	//originalUrl:.+  是为了防止值中含有.或者。
	@RequestMapping("/validate/{token}/{originalUrl:.+}")
	public ValidateMessage validateUser(@PathVariable("token") String token,@PathVariable("originalUrl") String originalUrl) {
		User user = TokenData.validateToken(token);
		if (user == null) {
			ValidateMessage failMeassage = ValidateMessage.getFailMeassage();
			failMeassage.put("originalURL",originalUrl);
			return failMeassage;
		}
		user.setPassword("********刮开查看密码*****");
		ValidateMessage message = ValidateMessage.getSuccessMeassage();
		message.put("user",user);
		message.put("originalUrl",originalUrl);
		// 防止密码泄露
		return message;
	}



	//originalUrl:.+  是为了防止值中含有.或者。
	@RequestMapping("/validate/{originalUrl:.+}")
	public ValidateMessage validateUserWithoutToken(@PathVariable("originalUrl") String originalUrl,HttpServletRequest request) {
		String token = request.getHeader("token");
		User user = TokenData.validateToken(token);
		if (user == null) {
			ValidateMessage failMeassage = ValidateMessage.getFailMeassage();
			failMeassage.put("originalUrl",originalUrl);
			return failMeassage;
		}
		user.setPassword("********刮开查看密码*****");
		ValidateMessage successMeassage = ValidateMessage.getSuccessMeassage();
		successMeassage.put("user",user);
		successMeassage.put("originalUrl",originalUrl);
		// 防止密码泄露
		return successMeassage;
	}


	@RequestMapping("/validate")
	public ValidateMessage validateUserWithoutTokenandOriginalUrl(HttpServletRequest request) {
		String token = request.getHeader("token");
		User user = TokenData.validateToken(token);
		if (user == null) {
			ValidateMessage failMeassage = ValidateMessage.getFailMeassage();
			return failMeassage;
		}
		user.setPassword("********刮开查看密码*****");
		ValidateMessage successMeassage = ValidateMessage.getSuccessMeassage();
		successMeassage.put("user",user);
		// 防止密码泄露
		return successMeassage;
	}


}
