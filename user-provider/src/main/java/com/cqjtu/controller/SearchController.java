package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.Users;
import com.cqjtu.modelexp.UsersDetailExp;
import com.cqjtu.modelexp.UsersExp;
import com.cqjtu.service.SearchService;
import com.cqjtu.tools.LoggerTool;
import com.cqjtu.tools.RegularTool;
import com.cqjtu.tools.ValidateAdminTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 用户查询控制器
 *
 * @author Tangyu
 * @date 2018/4/21
 */
@RestController
@RequestMapping("/users")
public class SearchController {

    //idCard accountStatusId roleId realname phone mail

    @Value("${hospitalAdmin.code}")
    private String adminCode;

    @Autowired
    private SearchService searchService;

    /**
     * 按照idCard查找
     * @param request
     * @param idCard
     * @return
     */
    @RequestMapping(value = {"/idcard","/idcard/","/idcard/{idcard}","/idcard/{idcard}/"}, method = {RequestMethod.GET})
    public Message searchByIdCard(HttpServletRequest request, String idCard) {
        Message message = new Message();

        //首先判断当前用户角色
        //获取当前用户信息
        Users user = (Users) request.getAttribute("user");
        //打印当前用户信息
        LoggerTool.getLogger(this.getClass()).info(user.getIdCard() + "  " + user.getRealname());

        if (user.getRoleId() <= 1) {
            //普通用户，只能查询自己的信息
            try {
                UsersExp selectUser = searchService.selectUsersByIdCard(user.getIdCard());
                message.put("user", selectUser);
                message.setCode(200);
                message.setInfo("查询成功");
            } catch (Exception e) {
                message.setCode(500);
                message.setInfo("查询失败\n" + e.getMessage());
            }
        } else {
            //非普通用户，可查询任意用户信息
            //判断输入的idCard格式是否正确
            if (RegularTool.isIdCard(idCard)) {
                try {
                    UsersExp selectUsers = searchService.selectUsersByIdCard(idCard);
                    message.put("user", selectUsers);
                    message.setCode(200);
                    message.setInfo("查询成功");
                } catch (Exception e) {
                    message.setCode(500);
                    message.setInfo("查询失败\n" + e.getMessage());
                }
            } else {
                message.setCode(201);
                message.setInfo("身份证格式错误，查询失败");
            }
        }
        return message;
    }

    /**
     * 按照accountStatusId查找，仅限管理员使用
     * @param request
     * @param accoutStatusId
     * @return
     */
    @RequestMapping(value = {"/accountstatusid","/accountstatusid/","/accountstatusid/{accountstatusid}","/accountstatusid/{accountstatusid}/"}, method = {RequestMethod.GET})
    public Message searchByaccountStatusId(HttpServletRequest request, Integer accoutStatusId) {
        Message message = new Message();
        //获取当前用户信息
        Users users = (Users) request.getAttribute("user");
        LoggerTool.getLogger(this.getClass()).info(users.getIdCard() + users.getRealname());
        if (ValidateAdminTool.isAdmin(request, adminCode)) {
            //管理员 整型变量accountStatusId若为空，默认赋值为0
            try {
                //调用searchService，返回用户列表
                List<UsersExp> selectUsers = searchService.selectUsersByAccountStatusId(users.getAccountStatusId());
                message.put("user", selectUsers);
                message.setCode(200);
                message.setInfo("查询成功");
            } catch (Exception e) {
                message.setCode(500);
                message.setInfo("查询失败\n" + e.getMessage());
            }
        } else {
            //非管理员
            message.setCode(403);
            message.setInfo("非管理员，无权访问");
        }
        return message;
    }

    /**
     * 按照roleId查找，仅限管理员使用
     * @param request
     * @param roleId
     * @return
     */
    @RequestMapping(value = {"/roleid","/roleid/","/roleid/{roleid}","/roleid/{roleid}/"},method = {RequestMethod.GET})
    public Message searchByRoleId(HttpServletRequest request ,Integer roleId){
        Message message = new Message();
        Users user = (Users) request.getAttribute("user");
        LoggerTool.getLogger(this.getClass()).info(user.getIdCard() + user.getRealname());
        if (ValidateAdminTool.isAdmin(request,adminCode)){
            //管理员
            try {
                List<UsersExp> selectUsers = searchService.selectUsersByRoleId(roleId);
                message.put("user",selectUsers);
                message.setCode(200);
                message.setInfo("查询成功");
            }catch (Exception e){
                message.setCode(500);
                message.setInfo("查询失败");
            }
        }else {
            //非管理员
            message.setCode(403);
            message.setInfo("非管理员，无权访问");
        }
        return message;
    }

    /**
     * 按照realname查找
     * @param request
     * @param realname
     * @return
     */
    @RequestMapping(value = {"/realname","/realname/","/realname/{realname}","/realname/{realname}/"}, method = {RequestMethod.GET})
    public Message searchByRealname(HttpServletRequest request, String realname) {
        Message message = new Message();

        Users users = (Users) request.getAttribute("user");
        LoggerTool.getLogger(this.getClass()).info(users.getIdCard() + users.getRealname());

        if (users.getRoleId() <= 1) {
            //普通用户
            try {
                List<UsersExp> selectUser = searchService.selectUsersByRealname(users.getRealname());
                message.put("user", selectUser);
                message.setCode(200);
                message.setInfo("查询成功");
            } catch (Exception e) {
                message.setCode(500);
                message.setInfo("查询失败\n" + e.getMessage());
            }
        } else {
            //非普通用户
            if (realname.trim().length() > 0) {
                try {
                    List<UsersExp> selectUsers = searchService.selectUsersByRealname(realname);
                    message.put("user", selectUsers);
                    message.setCode(200);
                    message.setInfo("查询成功");
                } catch (Exception e) {
                    message.setCode(500);
                    message.setInfo("查询失败\n" + e.getMessage());
                }
            } else {
                //姓名输入为空，无法查找
                message.setCode(201);
                message.setInfo("请输入姓名");
            }
        }
        return message;
    }

    /**
     * 按照phone查找
     * @param request
     * @param phone
     * @return
     */
    @RequestMapping(value = {"/phone","/phone/","/phone/{phone}","/phone/{phone}/"}, method = {RequestMethod.GET})
    public Message searchByPhone(HttpServletRequest request, String phone) {
        Message message = new Message();

        Users users = (Users) request.getAttribute("user");
        LoggerTool.getLogger(this.getClass()).info(users.getIdCard() + "  " + users.getRealname());

        if (users.getRoleId() <= 1) {
            //普通用户
            try {
                UsersExp selectUser = searchService.selectUsersByPhone(users.getPhone());
                message.put("user", selectUser);
                message.setCode(200);
                message.setInfo("查询成功");
            } catch (Exception e) {
                message.setCode(500);
                message.setInfo("查询失败\n" + e.getMessage());
            }
        } else {
            //非普通用户
            if (RegularTool.isPhone(phone)) {
                try {
                    UsersExp selectUsers = searchService.selectUsersByPhone(phone);
                    message.put("user", selectUsers);
                    message.setCode(200);
                    message.setInfo("查询成功");
                } catch (Exception e) {
                    message.setCode(500);
                    message.setInfo("查询失败\n" + e.getMessage());
                }
            } else {
                message.setCode(201);
                message.setInfo("电话号码格式错误，查询失败");
            }
        }
        return message;
    }

    /**
     * 按照mail查找
     * @param request
     * @param mail
     * @return
     */
    @RequestMapping(value = {"/mail","/mail/","/mail/{mail}","/mail/{mail}/"}, method = {RequestMethod.GET})
    public Message searchByMail(HttpServletRequest request, String mail) {
        Message message = new Message();

        Users users = (Users) request.getAttribute("user");
        LoggerTool.getLogger(this.getClass()).info(users.getIdCard() + users.getRealname());

        if (users.getRoleId() <= 1) {
            //普通用户
            try {
                UsersExp selectUser = searchService.selectUsersByMail(users.getMail());
                message.put("user", selectUser);
                message.setCode(200);
                message.setInfo("查询成功");
            } catch (Exception e) {
                message.setCode(500);
                message.setInfo(e.getMessage());
            }
        } else {
            //非普通用户
            if (RegularTool.isMail(mail)) {
                try {
                    UsersExp selectUsers = searchService.selectUsersByMail(mail);
                    message.put("user", selectUsers);
                    message.setCode(200);
                    message.setInfo("查询成功");
                } catch (Exception e) {
                    message.setCode(500);
                    message.setInfo(e.getMessage());
                }
            } else {
                message.setCode(201);
                message.setInfo("邮箱格式错误");
            }
        }
        return message;
    }


    @RequestMapping(value = {"/detail","/detail/"})
    public Message searchUsersDetailExpBuIdCard(HttpServletRequest request){
        Message message =  new Message();
        if ( request.getAttribute("user") == null){
            message.setInfo("未授权");
            message.setCode(401);
        }else {
            Users users = (Users) request.getAttribute("user");
            UsersDetailExp usersDetailExp = searchService.selectUsersDetailExpByIdCard(users.getIdCard());
            message.put("userDetail",usersDetailExp);
            message.setCode(200);
            message.setInfo("查询用户详细信息成功");
        }
        return  message;
    }
}
