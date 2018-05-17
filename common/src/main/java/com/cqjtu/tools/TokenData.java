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
	 * 存储Token的MAP key token，value user
	 */
	private static Map<String, Users> dataMap;

	/**
	 * 存放token生命周期的MAP key token ,value 上次操作的时间
	 */
	private static Map<String,Long> dataLife;

	/**
	 * 存放用户登录信息的MAP key token,key sessionID
	 */
	private static Map<String , String > loginInfo;


	static {
		dataMap = new HashMap<>();
		dataLife = new HashMap<>();
		loginInfo = new HashMap<>();
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
	public static void addToken(String token, Users user,String sessionID) {
		synchronized (TokenData.class){
			dataMap.put(token, user);
			dataLife.put(token,System.currentTimeMillis());
			loginInfo.put(token,sessionID);
		}
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
			synchronized (TokenData.class){
				dataLife.replace(token,System.currentTimeMillis());
			}
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
		synchronized (TokenData.class){
			dataLife.remove(token);
			dataMap.remove(token);
			loginInfo.remove(token);
		}
	}

	/**
	 * 根据token 返回user
	 * @param token
	 * @return
	 */
	public static Users getUserByToken(String token){
		return dataMap.get(token);
	}

	/**
	 * 根据username 返回 token
	 * @param username
	 * @return
	 */
	public static String getTokenByUsername(String username){
		Set<String> keySet = dataMap.keySet();
		String token = null;
		for (String key :keySet){
			Users user = dataMap.get(key);
			if (user.getIdCard().equals(username)){
				token = key;
				break;
			}
		}
		return  token;
	}


	/**
	 * 判断用户是否已经登录
	 * @param username
	 * @return
	 */
	public  static boolean isLogin(String username,String sessionID){
		boolean result = false;
		Set<String> keySet = dataMap.keySet();
		for (String key :keySet){
			String idCard = dataMap.get(key).getIdCard();
			if (idCard.equals(username)){
				if (loginInfo.get(key).equals(sessionID)){
					//如果是同一个会话重复登录
					result = true;
				}else {
					//如果不是同一个会话登录，异地登录，则这次登录为准，清除之前的登录信息
					synchronized (TokenData.class){
						dataMap.remove(key);
						dataLife.remove(key);
						loginInfo.remove(key);
					}
					result = false;
				}
				break;
			}
		}
		return  result;
	}



	public static class TokenLife extends Thread{

		@Override
		public void run() {
			while (true){
				try {
					sleep(5000);
					LoggerTool.getLogger(TokenData.class).info("用户令牌生命周期检测");
					ArrayList<String> keys = new ArrayList<>();
					synchronized (TokenData.class){
						Set<Map.Entry<String, Long>> entries =dataLife.entrySet();
						for (Map.Entry<String , Long> entry : entries){
							LoggerTool.getLogger(TokenData.class).info("用户令牌："+entry.getKey()
									+"   上次操作时间："+entry.getValue()
									+"	 上次操作sessionID："+loginInfo.get(entry.getKey()));
							if (System.currentTimeMillis()>entry.getValue()+30*60*1000){
								//超过三十分钟没有操作
								keys.add(entry.getKey());
							}
						}
						for (String key :keys){
							LoggerTool.getLogger(TokenData.class).info("删除用户令牌："+key);
							synchronized (TokenData.class){
								dataMap.remove(key);
								dataLife.remove(key);
								loginInfo.remove(key);
							}
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
