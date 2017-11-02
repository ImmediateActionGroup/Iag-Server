package com.iag.server;

import com.iag.server.request.Request;
import com.iag.server.response.Response;

import java.io.IOException;

/**
 * Created by beishan on 2017/11/2.
 */
public class StaticResourceProcessor {

    public void process(Request request, Response response){
        try {
            response.sendStaticResource();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
