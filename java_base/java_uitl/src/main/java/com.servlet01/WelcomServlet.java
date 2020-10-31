package com.servlet01;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther: zwy
 * @Date: 2020/6/21
 * @Description: com.servlet01
 * @version:
 */
public class WelcomServlet extends HttpServlet {

    private String greeting;

    public void init() {
        greeting = getInitParameter("greeting");
//        greeting  = getServletConfig().getInitParameter("greeting");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("gb2312");
        String username = req.getParameter("username");
        String welcomInfo = greeting + " , " + username;
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>");
        out.println("Welcome Page");
        out.println("</head></title>");
        out.println("<body>");
        out.println(welcomInfo);
        out.println("</body></html>");
        out.close();
    }

    public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        doGet(req,resp);
    }
}
