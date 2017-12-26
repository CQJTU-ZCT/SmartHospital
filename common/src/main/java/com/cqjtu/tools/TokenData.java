package com.cqjtu.tools;

import com.cqjtu.domain.User;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <p>ClassName:TokenData</p>
 * <p>Description:全局的令牌数据</p> 
 * @author ZJH
 * @date 2017年5月29日上午11:39:58
 */
public class TokenData {
	private TokenData() {

	}

	// 存储Token的MAP
	private static Map<String, User> dataMap;

	static {
		dataMap = new HashMap<String, User>();
	}

	/**
	 * 
	 * <p>MethodName: addToken</p>
	 * <p>Description:添加令牌</p> 
	 * <p>@param token
	 * <p>@param user   </p> 
	 * <p>void  </p> 
	 * @author ZJH
	 * @date 2017年5月29日上午11:32:29
	 */
	public static void addToken(String token, User user) {
		dataMap.put(token, user);
	}

	/**
	 * 
	 * <p>MethodName: validateToken</p>
	 * <p>Description:验证令牌是否有效</p> 
	 * <p>@param token
	 * <p>@return 有效则返回user对象，无效则返回null  </p> 
	 * <p>User  </p> 
	 * @author ZJH
	 * @date 2017年5月29日上午11:42:05
	 */
	public static User validateToken(String token) {
		return dataMap.get(token);
	}

	/**
	 * 
	 * <p>MethodName: removeToken</p>
	 * <p>Description:移除令牌</p> 
	 * <p>@param token   </p> 
	 * <p>void  </p> 
	 * @author ZJH
	 * @date 2017年5月29日上午11:44:06
	 */
	public static void removeToken(String token) {
		dataMap.remove(token);
	}

}
