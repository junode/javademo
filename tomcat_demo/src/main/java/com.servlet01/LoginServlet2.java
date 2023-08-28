package com.servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet2 extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException,IOException
    {

        resp.setContentType("text/html;charset=gb2312");

        PrintWriter out=resp.getWriter();

        out.println("<form method=post action=portal>");

        out.println("<table>");

        out.println("<tr>");
        out.println("<td>请输入用户名</td>");
        out.println("<td><input type=text name=username></td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>请输入密码</td>");
        out.println("<td><input type=password name=password></td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td><input type=reset value=重填></td>");
        out.println("<td><input type=submit value=登录></td>");
        out.println("</tr>");

        out.println("</table>");
        out.println("</form>");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException,IOException {
        doGet(req,resp);
    }
}