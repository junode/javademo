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
 * @Date: 2020/6/20
 * @Description:
 * 1 在创建该java_util模块的时候，注意选择maven的类型为maven-archetype-info类型。
 * 若是在编写servlet实例时发现未有导入HttpServlet，则是maven依赖加载没有成功，
 * 可以将依赖删除，更新后再添加上去，若还是不行，那么就重建java_util模块。
 *
 * 2 若是项目启动后访问jsp和html页面报404，则可能是在第一步创建的时候maven类型
 * 选择错误；也有可能是路径访问错误，通过edit configuration页面查看deployment
 * 下的访问路径；还有可能是jsp/html页面放在了WEB-INF目录下，将其移出WEB-INF目录。
 *
 */
public class demo01 extends HttpServlet {
    private static final long serialVersionUID = -4751096228274971485L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        resp.getWriter().println("hello world");

        System.out.println(req.getContextPath()); // /util_skills
        System.out.println(req.getAuthType()); // null
        System.out.println(req.getCookies()); // null
        System.out.println();
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            System.out.println(parameterNames.nextElement());
        }
        System.out.println();
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            System.out.println(headerNames.nextElement());
        }
        System.out.println();
        System.out.println(req.getRemoteUser());
        System.out.println(req.getRequestURI());
        System.out.println(req.getRequestURL());
        System.out.println(req.getPathInfo());
        System.out.println(req.getProtocol());

        // 得到PrintWriter对象。Servlet使用输出流来产生响应对象
        PrintWriter out = resp.getWriter();
        // 向客户端发送字符数据
        out.println("Hello world<br />");
        out.println("傲来雾，花果香，定海一棒万妖朝 <br />");
        out.println("东海外，水帘中，齐天比高仙折腰 <br />");
        out.close();
    }

    public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        doGet(req,resp);
    }

    @Override
    public void init() throws ServletException {
        System.out.println("Servlet " + this.getServletName() + " has started");
    }

    @Override
    public void destroy() {
        System.out.println("Servlet " + this.getServletName() + " has stopped");
    }
}
