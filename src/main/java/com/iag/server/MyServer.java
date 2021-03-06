package com.iag.server;

import com.iag.server.request.Request;
import com.iag.server.response.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xueshan.wei on 3/14/2017.
 */
public class MyServer {
    //public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    // shutdown command
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String [] args){
        MyServer server = new MyServer();
        server.await();
    }

    public void await(){
        ServerSocket serverSocket = null;
        int port = 8081;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));

        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        while (!shutdown){
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;

            try{
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                // create  a server.Request Object and parse it
                Request request = new Request(input);
                request.parse();

                //create server.Response object
                Response response = new Response(output);
                response.setRequest(request);
                //response.sendStaticResource();

                /**
                 * check if this is a request for a servlet or a static resource
                 */

                if(request.getUri().startsWith("/servlet/")){
                    ServletProcessor1 processor = new ServletProcessor1();
                    processor.process(request, response);
                }else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }
                //关闭 socket
                socket.close();

                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }
}
