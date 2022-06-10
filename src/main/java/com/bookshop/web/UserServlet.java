package com.bookshop.web;

import com.bookshop.pojo.User;
import com.bookshop.service.impl.UserServiceImpl;
import com.bookshop.utils.WebUtils;
import com.google.code.kaptcha.servlet.KaptchaServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserServiceImpl userService=new UserServiceImpl();
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取请求参数
        /*String username = req.getParameter("username");
        String password = req.getParameter("password");*/
        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());
        //2.调用service处理业务
        boolean loginuser= false;
        try {
            loginuser = userService.userLogIn(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(loginuser==false){
            //把错误的信息，和回显的表单项信息，保存到request域中
            req.setAttribute("msg","用户名或者密码错误");
            req.setAttribute("username",user.getUsername());
            //登录失败跳回登录页面
            System.out.println("用户名或密码错误！");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            //将用户信息不全
            user=userService.buQuanUser(user);
            //保存用户登录之后的信息到session域中
            req.getSession().setAttribute("user",user);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }
    //注销，用户登出
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.getSession().invalidate();
        //重定向到首页
        resp.sendRedirect(req.getContextPath());
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        /*String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        */
        String code = req.getParameter("code");
        //获取验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());
        //2.检查验证码是否正确----验证码写死
        if(token!=null&&token.equalsIgnoreCase(code)){

            //1.检查用户名是否正确
            try {
                if(userService.existUserName(user.getUsername())){
                    System.out.println("用户名["+user.getUsername()+"]已存在");
                    //把回显信息保存到request域中
                    req.setAttribute("msg","用户名已存在！");
                    req.setAttribute("username",user.getUsername());
                    req.setAttribute("email",user.getEmail());
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
                }else{
                    userService.registUser(user);
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            //把回显信息保存到request域中
            req.setAttribute("msg","验证码错误！");
            req.setAttribute("username",user.getUsername());
            req.setAttribute("email",user.getEmail());
            //不正确，跳回注册页面
            System.out.println("验证码["+code+"]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }
/*    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            Method declaredMethod = UserServlet.class.getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            declaredMethod.invoke(this,req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/
}
