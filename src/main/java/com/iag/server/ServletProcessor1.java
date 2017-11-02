package com.iag.server;

import com.iag.server.request.Request;
import com.iag.server.response.Response;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by beishan on 2017/11/2.
 */
public class ServletProcessor1 {

    public void process(Request request, Response response){
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;
        System.out.println(servletName);
        try {
            // create a URLClassLoader
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            //String path = Constants.WEB_ROOT + File.separator + "server-1";
            String path = Constants.WEB_ROOT;
            File classpath = new File(path);

            System.out.println(classpath.getCanonicalPath() + File.separator);
            String repository = (new URL("file", null, classpath.getCanonicalPath() + File.separator)).toString();
            System.out.println(repository);
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        }catch (IOException e){
            System.out.println(e.toString());
        }

        Class myClass = null;

        try {
            myClass = loader.loadClass(servletName);
        }catch (ClassNotFoundException e){
            System.out.println(e.toString());
        }

        Servlet servlet = null;
        try{
            servlet = (Servlet) myClass.newInstance();
            servlet.service((ServletRequest) request, (ServletResponse) response);
        }catch (Exception e){
            System.out.println(e.toString());
        }catch (Throwable e){
            System.out.println(e.toString());
        }
    }
}
