package util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtils {
    public static HttpURLConnection conn=null;
    public static String post(String path,String method,String params)throws Exception{
        URL url = new URL(path);
        conn= (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);// 提交模式
        conn.setDoOutput(true);// 是否输入参数
        byte[] bypes = params.getBytes();
        conn.getOutputStream().write(bypes);// 输入参数
        InputStream inStream=conn.getInputStream();

        ByteArrayOutputStream out=new ByteArrayOutputStream();
        byte[] buffer=new byte[1024];
        int len =0;
        while((len=inStream.read(buffer,0,buffer.length))!=-1){
            out.write(buffer,0,len);
        }

        byte[] bytes=out.toByteArray();
        inStream.close();
        out.close();
        return new String(bytes);
}
    public static void close(){
        conn.disconnect();
    }


}
