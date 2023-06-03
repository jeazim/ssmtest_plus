package com.atzhi.controller;

import com.atzhi.pojo.Course;
import com.atzhi.pojo.Result;
import com.atzhi.pojo.User;
import com.atzhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    Result result;

//    //获取用户名
//    @GetMapping("/get_name")
//    @ResponseBody
//    public String get_name(HttpSession session)
//    {
//        User user = (User) session.getAttribute("user");
//        return user.getUsername();
//    }

    //获取用户权限
    @GetMapping("/get_pri")
    @ResponseBody
    public int get_pri(HttpSession session)  {
        User user = (User) session.getAttribute("user");
        System.out.println("-----------user.getPri()="+user.getPri()+"----------------------");
        return user.getPri();
    }

    // 获取用户名
    @GetMapping("/get_name")
    @ResponseBody
    public String get_name(HttpSession session)  {
        User user = (User) session.getAttribute("user");
        return user.getUsername();
    }

    //登录页面
    @GetMapping("/login_page")
    public String login_page() {
        //System.out.println("success");
        return "login.html";
    }
    //注册界面
    @GetMapping("/register_page")
    public String register_page()
    {
        return "register.html";
    }
    //退出并返回登录页面
    @GetMapping("/exit_page")
    public String exit_page(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login_page";
    }

    //登录逻辑处理
    @PostMapping("/login_solve")
    @ResponseBody
    public Result login_solve(@RequestBody User user, HttpServletRequest request) {
        System.out.println(user);
        System.out.println("-------------------------login_solve-------------------------------------------");
//        System.out.println("user");
        User res=userService.selectByUsername(user.getUsername());
        System.out.println("res="+res);
//        System.out.println("user");
        //判断验证码
        if(user.getInputCaptcha().equals(user.getCaptcha()))
        {
            // 判断密码是否正确
            if (user.getPassWord().equals(res.getPassWord())) {
//                System.out.println("1");
                result.setMsg("success");
//                user.setUsername(new String(user.getUsername().getBytes("UTF-8"), "ISO-8859-1"));
                HttpSession session = request.getSession();
                session.setAttribute("user", res);
            } else {
                result.setMsg("false");
            }
        }
        else {
            result.setMsg("false");
        }
        System.out.println("----------------------------------------return");
        result.setCode(200);
        result.setData(user);
        return result;
    }

    @PostMapping("/register_solve")
    @ResponseBody
    public Result register_solve(@RequestBody User user) {
        System.out.println("--------------------------------------------------"+user);
//        System.out.println("user");
        List<User> users=userService.selectAllUsers();

        result.setMsg("success");
        for(User res :users)
        {
            if(res.getUsername().equals(user.getUsername()))
            {
                result.setMsg("false");
                break;
            }
        }
        if (result.getMsg().equals("success")){
            userService.addUser(user);
        }

        System.out.println("----------------------------------------return");
        result.setCode(200);
        return result;
    }


}
