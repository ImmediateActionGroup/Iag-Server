package com.iag.server.classloader;

import com.iag.server.TestClassloader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by beishan on 2017/11/2.
 */
public class MyClassloader {

    public static void main(String[] args) {
        URLStreamHandler streamHandler = null;


        File classpath = new File("E:\\mywork\\Iag-Server\\webroot\\server-1");
        URL url = null;

        String repository = null;
        try {
            repository = (new URL("file", null, classpath.getCanonicalPath())).toString();

            System.out.println("repository : " + repository);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            url = new URL(null, repository, streamHandler);
            //url = file.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        URLClassLoader loader = new URLClassLoader(new URL[]{url});

        try {
            Class ps = loader.loadClass("TestClassloader");

            TestClassloader tcl = (TestClassloader) ps.newInstance();

            tcl.say();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
