package com.servlet01;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @Auther: zwy
 * @Date: 2020/6/21
 * @Description:
 * @version:
 */
public class OutputInfoServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html;charset=gb2312");
        PrintWriter out = resp.getWriter();
        Enumeration headNames= req.getHeaderNames();

        out.println("<html><head>");
        out.println("<title>Info page</title");
        out.println("</head>");
        out.println("<body><center>");

        out.println("<table border=1 align=center");
        out.println("<caption>Servlet接受到的HTTP消息报头的信息</caption");
        out.println("<tr bgcolor=#999999>");
        out.println("<th>消息报头的名字</th>");
        out.println("<th>消息报头的值</th>");
        out.println("</ tr>");

        while(headNames.hasMoreElements()){
            String name = (String) headNames.nextElement();
            String value = req.getHeader(name);
            out.println("<tr>");
            out.println("<td>"+name+"</td>");
            out.println("<td>"+value+"</td>");
            out.println("</ tr>");
        }

        out.println("</table><p>");

        out.println("<table border=1 align=center");
        out.println("<caption>其他访问信息</cation>");

        out.println("<tr>");
        out.println("<td>客户端的IP地址</td>");
        out.println("<td>"+req.getRemoteAddr()+"</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>客户端的端口号</td>");
        out.println("<td>"+req.getRemotePort()+"</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>服务器的IP地址</td>");
        out.println("<td>"+req.getLocalAddr()+"</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>服务器的端口号</td>");
        out.println("<td>"+req.getLocalPort()+"</td>");
        out.println("</tr>");

        out.println("</table>");

        out.println("</center></body></html>");
        out.close();


    }
}
