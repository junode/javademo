package com.servlet01.ch05;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther: zwy
 * @Date: 2020/6/27
 * @Description: com.servlet01.ch05
 * @version:
 */
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=gb2312");

        HttpSession session = req.getSession();
        session.invalidate();

        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>退出登录</title></head>");
        out.println("<body>");
        out.println("已经退出登录<br>");
        out.println("<a href="+resp.encodeURL("login10")+">重新登录</a>");
        out.println("</body></html>");
        out.close();
    }
}
