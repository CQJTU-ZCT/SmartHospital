package com.cqjtu.controller;


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
    public LoginMessage login(String username, String password,HttpServletRequest request){
        if(username == null || username.length() <=0
                ||password == null || password.length() <=0){
            return LoginMessage.getParaErrorMessage();
        }
        Users user = userService.findUserByUsername(username);
        if(user == null){
            return LoginMessage.getUserNotExistMessage();
        }
        if(!user.getPassword().equals(password)){
            return LoginMessage.getErrorPasswordMessage();
        }
        LoginMessage successMessage = LoginMessage.getSuccessMessage();
        if (request.getHeader(originalUrl) != null){
            successMessage.put(originalUrl,request.getHeader(originalUrl));
        }
        user.setPassword("********刮开查看密码*****");
        successMessage.put("user",user);

        String token = Token.getToken32WithoutLine();
        /**
         * 添加token到缓存，这里可以考虑替换成redis
         */
        TokenData.addToken(token,user);
        successMessage.put("token",token);
        return  successMessage;
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
