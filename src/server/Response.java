package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by xueshan.wei on 3/14/2017.
 */
public class Response {
    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;

    public Response(OutputStream output){
        this.output = output;
    }
    public void setRequest(Request request){
        this.request = request;
    }

    public void sendStaticResource() throws IOException{
        byte [] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try{
            File file = new File(MyServer.WEB_ROOT, request.getUri());
            System.out.println("Request Uri:" + request.getUri());
            System.out.println("Webroot = " + MyServer.WEB_ROOT);
            System.out.println("File Exist : " + file.exists());
            System.out.println(file.length());

            String message = "HTTP/1.1 200 OK\r\n" +
                    "Content-type: text/html\r\n" +
                    "Content-Length: 14\r\n" +
                    "\r\n" +
                    "<h1>hahah</h1>";

            if(file.exists()){
                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1){
                    System.out.println("file output ......." + bytes.toString());
                    output.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
                //output.write(message.getBytes());
            }else{
                //文件不存在
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                output.write(errorMessage.getBytes());
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        finally {
            if(fis != null){
                fis.close();
            }
        }
    }
}
