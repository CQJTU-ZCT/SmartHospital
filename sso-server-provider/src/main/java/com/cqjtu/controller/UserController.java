package com.cqjtu.controller;


import com.cqjtu.service.UserService;
import com.cqjtu.domain.User;
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
@RequestMapping("/")
public class UserController {


    @Autowired
    private UserService userService;



    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/login/{originalUrl}",method = RequestMethod.POST)
    public LoginMessage login(String username, String password, @PathVariable("originalUrl")  String originalUrl){
        if(username == null || username.length() <=0
                ||password == null || password.length() <=0){
            return LoginMessage.getParaErrorMessage();
        }
        User user = userService.findUserByUsername(username);
        if(user == null){
            return LoginMessage.getUserNotExistMessage();
        }
        if(!user.getPassword().equals(password)){
            return LoginMessage.getErrorPasswordMessage();
        }
        LoginMessage suceesssMessage = LoginMessage.getSuceesssMessage();
        suceesssMessage.setOriginalUrl(originalUrl);
        user.setPassword("********刮开查看密码*****");
        suceesssMessage.setUser(user);


        String token = Token.getToken32WithoutLine();
        /**
         * 添加token到缓存，这里可以考虑替换成redis
         */
        TokenData.addToken(token,user);
        suceesssMessage.setToken(token);
        return  suceesssMessage;
    }


    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public LoginMessage loginWithoutOriginalUrl(String username, String password){
        if(username == null || username.length() <=0
                ||password == null || password.length() <=0){
            return LoginMessage.getParaErrorMessage();
        }
        User user = userService.findUserByUsername(username);
        if(user == null){
            return LoginMessage.getUserNotExistMessage();
        }
        if(!user.getPassword().equals(password)){
            return LoginMessage.getErrorPasswordMessage();
        }
        LoginMessage suceesssMessage = LoginMessage.getSuceesssMessage();
        user.setPassword("********刮开查看密码*****");
        suceesssMessage.setUser(user);


        String token = Token.getToken32WithoutLine();
        /**
         * 添加token到缓存，这里可以考虑替换成redis
         */
        TokenData.addToken(token,user);
        suceesssMessage.setToken(token);
        return  suceesssMessage;
    }




    /**
     * 登出
     * @return
     */
    @RequestMapping(value = "/logout/{token}",method = RequestMethod.GET)
    public LogoutMessage logout(@PathVariable("token") String token){
        if (token == null || token.length() != 32 ){
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
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public LogoutMessage logoutWithOutToken(HttpServletRequest request){
        String token = request.getHeader("token");
        if (token == null || token.length() != 32 ){
            return LogoutMessage.getParaErrorMessage();
        }
        if (TokenData.validateToken(token) == null){
            return LogoutMessage.getUserNotLoginMessage();
        }
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
