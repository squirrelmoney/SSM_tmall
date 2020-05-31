package com.lnsf.tmall.controller;
import com.lnsf.tmall.pojo.Orderitem;
import com.lnsf.tmall.pojo.User;
import com.lnsf.tmall.service.OrderitemService;
import com.lnsf.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
@Controller
public class Fore_User_Controller {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderitemService orderitemService;
    /*注册页面*/
    @RequestMapping(value = "signup" ,method = RequestMethod.GET)
    public String starting(Model m){
        return "fore/register";
    }

    /*注册*/
    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String register(Model m, User user){
        if (userService.insert(user)!=0)
            return "redirect:/home";
        return "fore/register";
    }
    /*检查用户名是否与数据库用户冲突*/
    @RequestMapping("CheckUsername")
    public @ResponseBody User CheckUsername(@RequestBody User user){
        User record=userService.selectByName(user.getName());
        System.out.println(record.getName());
        return record;
    }

    /*登陆*/
    @RequestMapping(value = "signin",method = RequestMethod.GET)
    public String tologining(){
        return "fore/login";
    }

    @RequestMapping(value = "signout")
    public String signout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/signin";
    }

    //是否记住密码
    boolean isMe;
    /*登陆*/
    @RequestMapping(value = "signin",method = RequestMethod.POST)
    public String logining(User user, HttpServletResponse response, HttpServletRequest request,
                           @RequestParam(required = false) String isMemory){
        if(isMemory==null){
            isMe=false;
        }else{
            isMe=true;
        }
        User loginUser=userService.selectByName(user.getName());
        if(loginUser!=null&&loginUser.getPassword().equals(user.getPassword())){
            HttpSession session=request.getSession();
            session.setAttribute("user", loginUser);
            try {
                isMemory(isMe, loginUser, request, response);
            } catch (UnsupportedEncodingException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
            int countCart=orderitemService.countCart(loginUser.getUid());
            session.setAttribute("cartTotalItemNumber",countCart);
            return "redirect:/home";

        }else{

            return "ok";

        }
    }



    /*
     是否记忆密码
     **/
    private void isMemory(boolean isMe,User user,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
        if(isMe){

            Cookie username=new Cookie("username", user.getName());

            username.setPath("/");
            URLEncoder.encode(username.toString(), "utf-8");
            Cookie password=new Cookie("password",user.getPassword());

            password.setPath("/");
            URLEncoder.encode(password.toString(), "utf-8");
            response.addCookie(username);
            response.addCookie(password);
        }else{
            for (Cookie cookie :request.getCookies()) {
                if(cookie.getName().equals("username")|cookie.getName().equals("password")){

                    cookie.setMaxAge(0);;
                    /*cookie.setmaxage设置为0时，会马上在浏览器上删除指定的cookie
                    cookie.setmaxage设置为-1时，代表关闭当前浏览器即失效*/

                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }

        }

    }


    /*检查是否已登陆*/
    @RequestMapping(value = "forecheckLogin")
    public @ResponseBody String Checklogin(HttpServletRequest request){
        HttpSession session=request.getSession();
        if (session.getAttribute("user")!=null){
            return "success";
        }else {
            return "fail";
        }
    }


    @RequestMapping(value = "signHere",method = RequestMethod.POST)
    public @ResponseBody String signHere(User user,HttpServletRequest request){
        User loginUser=userService.selectByName(user.getName());
        if(loginUser!=null&&loginUser.getPassword().equals(user.getPassword())){
            HttpSession session=request.getSession();
            session.setAttribute("user", loginUser);
            int countCart=orderitemService.countCart(loginUser.getUid());
            session.setAttribute("cartTotalItemNumber",countCart);
            return "success";
        }else{
            return "fail";
        }
    }
}
