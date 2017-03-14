import java.io.InputStream;

/**
 * Created by xueshan.wei on 3/14/2017.
 */
public class Request {
    private InputStream input;
    private String uri;

    public Request(InputStream input) {
        this.input = input;
    }

    public void parse(){

    }

    private String parseUri(String requestString){
        return "";
    }

    public String getUri(){
        return uri;
    }
}
