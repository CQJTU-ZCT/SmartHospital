package com.cqjtu.controller;


import com.cqjtu.messages.Message;
import com.cqjtu.service.UserService;
import com.cqjtu.model.Users;
import com.cqjtu.messages.LoginMessage;
import com.cqjtu.messages.LogoutMessage;
import com.cqjtu.tools.ImageCut;
import com.cqjtu.tools.Token;
import com.cqjtu.tools.TokenData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/2.
 */
@RestController
public class UserController {


    private int tokenLength=32;

    @Autowired
    private UserService userService;


    private String originalUrl ="originalUrl";


    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public LoginMessage login(String username, String password, HttpServletRequest request){
        LoginMessage message = null;
        if(username == null || username.length() <=0
                ||password == null || password.length() <=0){
            message= LoginMessage.getParaErrorMessage();
        }else {

            //限制不允许重复登录
            if (TokenData.isLogin(username,request.getSession().getId())){
                message = LoginMessage.getRepeatLoginMessage();
                String tokenByUsername = TokenData.getTokenByUsername(username);
                Users userByToken = TokenData.getUserByToken(tokenByUsername);
                message.put("user",userByToken);
                message.put("token",tokenByUsername);
            }else {
                Users user = userService.findUserByUsername(username);
                if(user == null){
                    message = LoginMessage.getUserNotExistMessage();
                }else{
                    if(!user.getPassword().equals(password)){
                        message=  LoginMessage.getErrorPasswordMessage();
                    }else {
                        message = LoginMessage.getSuccessMessage();
                        if (request.getHeader(originalUrl) != null){
                            message.put(originalUrl,request.getHeader(originalUrl));
                        }
                        user.setPassword("********刮开查看密码*****");
                        message.put("user",user);

                        String token = Token.getToken32WithoutLine();
                        /**
                         * 添加token到缓存，这里可以考虑替换成redis
                         */
                        TokenData.addToken(token,user,request.getSession().getId());
                        message.put("token",token);
                    }
                }
            }
        }

        return  message;
    }







    /**
     * 登出
     * @return
     */
    @RequestMapping(value= "/signout/{token}",method = RequestMethod.GET)
    public LogoutMessage logout(@PathVariable("token") String token){
        if (token == null || token.length() != tokenLength ){
            return LogoutMessage.getParaErrorMessage();
        }
        if (TokenData.validateToken(token) == null){
            return LogoutMessage.getUserNotLoginMessage();
        }
        TokenData.removeToken(token);
        return  LogoutMessage.getSuccessMessage();
    }


    /**
     * 登出 token放在了头部
     * @return
     */
    @RequestMapping(value= "/signout",method = RequestMethod.GET)
    public LogoutMessage logoutWithOutToken(HttpServletRequest request){
        String token = request.getHeader("token");
        if (token == null || token.length() != tokenLength ){
            return LogoutMessage.getParaErrorMessage();
        }
        if (TokenData.validateToken(token) == null){
            return LogoutMessage.getUserNotLoginMessage();
        }
        TokenData.removeToken(token);
        return  LogoutMessage.getSuccessMessage();
    }

    @RequestMapping("/getHeadPhoto/{username}")
    public void getPhoto(HttpServletResponse response,@PathVariable("username") String username){
        System.out.println(username);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            BufferedImage bufferedImage =
                    ImageCut.scale(this.getClass().getResource("/images/test.jpg").getFile(),
                            64, 64, true);
            ImageIO.write(bufferedImage ,"jpg", outputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
