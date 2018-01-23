package com.cqjtu.tools;


import com.cqjtu.model.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

	/**
	 * 存储Token的MAP
	 */
	private static Map<String, Users> dataMap;

	private static Map<String,Long> dataLife;

	static {
		dataMap = new HashMap<String, Users>();
		dataLife = new HashMap<>();
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
	public static void addToken(String token, Users user) {
		dataMap.put(token, user);
		dataLife.put(token,System.currentTimeMillis());
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
	public static Users validateToken(String token) {
		if (dataMap.containsKey(token)){
			//如果存在，则在每次验证时重新更新时间
			dataLife.replace(token,System.currentTimeMillis());
		}
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
		dataLife.remove(token);
		dataMap.remove(token);
	}

	public static class TokenLife extends Thread{
		@Override
		public void run() {
			while (true){
				try {
					sleep(5000);
					LoggerTool.getLogger(TokenData.class).info("用户令牌生命周期检测");
					ArrayList<String> keys = new ArrayList<>();
					Set<Map.Entry<String, Long>> entries = dataLife.entrySet();
					for (Map.Entry<String , Long> entry : entries){
						LoggerTool.getLogger(TokenData.class).info("用户令牌："+entry.getKey()+"   上次操作时间："+entry.getValue());
						if (System.currentTimeMillis()>entry.getValue()+30*60*1000){
							//超过三十分钟没有操作
							keys.add(entry.getKey());
						}
					}
					for (String key :keys){
						LoggerTool.getLogger(TokenData.class).info("删除用户令牌："+key);
						dataLife.remove(key);
						dataMap.remove(key);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
