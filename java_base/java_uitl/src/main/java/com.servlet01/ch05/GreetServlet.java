package com.servlet01.ch05;

import com.servlet01.ch05.util.OutputSessionInfo;

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
public class GreetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(resp.encodeRedirectURL("login10"));
        } else {
            resp.setContentType("text/html;charset=gb2312");
            PrintWriter out = resp.getWriter();
            out.println("<html><head><title><欢迎页面</title></head");
            out.println("<body>");

            OutputSessionInfo.getSessionInfo(out, session);

            out.println("<p>");
            out.println("欢迎你，" + user + "<p>");
            out.println("<a href="+resp.encodeURL("login10")+">重新登录</a>");
            out.println("<a href="+resp.encodeURL("logout10")+">注销</a>");
            out.println("</body></html>");
            out.close();
        }
    }
}
