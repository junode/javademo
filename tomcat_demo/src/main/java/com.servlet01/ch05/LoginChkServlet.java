package com.servlet01.ch05;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Auther: zwy
 * @Date: 2020/6/27
 * @Description: com.servlet01.ch05
 * @version:
 */
public class LoginChkServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("gb2312");
        String name = req.getParameter("user");
        String pwd = req.getParameter("password");

        if(null == name || null == pwd || name.equals("") || pwd.equals("")){
            resp.sendRedirect(resp.encodeRedirectURL("login10"));
            return;
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("user",name);
            resp.sendRedirect(resp.encodeRedirectURL("greet"));
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
