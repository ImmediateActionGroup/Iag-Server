package com.iag.server;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by beishan on 2017/11/2.
 */
public class PrimitiveServlet implements Servlet{

    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("primitive servlet init ......");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("from service");

        PrintWriter out = servletResponse.getWriter();
        out.println("Hello, Roses are red.");
        out.print("Violets are blue");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        System.out.println("primitive servlet destory ......");
    }
}
