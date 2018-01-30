package org.consume.com.api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ApiService {
    //    HttpURLConnection
    public String test() throws Exception {
        String strURL = "http://192.168.1.183:8080/account/page/1/5";
        URL url = new URL(strURL);
        HttpURLConnection httpConn = (HttpURLConnection)
                url.openConnection();
        httpConn.setRequestMethod("GET");
        httpConn.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpConn.getInputStream()));
        String line;
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        httpConn.disconnect();

        System.out.println(buffer.toString());
        return buffer.toString();
    }

    public String test2() throws Exception {
        String strURL = "http://219.148.38.131:9002/account/info";
        URL url = new URL(strURL);
        HttpURLConnection httpConn = (HttpURLConnection)
                url.openConnection();
        httpConn.setRequestMethod("POST");
        httpConn.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpConn.getInputStream()));
        String line;
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        httpConn.disconnect();

        System.out.println(buffer.toString());
        return null;
    }

    public String test3() throws Exception {
        String urlPath = "http://219.148.38.131:9002/account/people/password";

//        String param = "name=" + URLEncoder.encode("丁丁", "UTF-8");
//        //建立连接
//        URL url = new URL(urlPath);
//        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//        //设置参数
//        httpConn.setDoOutput(true);   //需要输出
//        httpConn.setDoInput(true);   //需要输入
//        httpConn.setUseCaches(false);  //不允许缓存
//        httpConn.setRequestMethod("POST");   //设置POST方式连接
//        //设置请求属性
//        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
//        httpConn.setRequestProperty("Charset", "UTF-8");
//        //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
//        httpConn.connect();
//        //建立输入流，向指向的URL传入参数
//        DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
//        dos.writeBytes(param);
//        dos.flush();
//        dos.close();
//        //获得响应状态
//        int resultCode = httpConn.getResponseCode();
//        if (HttpURLConnection.HTTP_OK == resultCode) {
//            StringBuffer sb = new StringBuffer();
//            String readLine = new String();
//            BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
//            while ((readLine = responseReader.readLine()) != null) {
//                sb.append(readLine).append("\n");
//            }
//            responseReader.close();
//            System.out.println(sb.toString());
//        }
        return null;
    }

    public void httpUrlConnetionGet() {
        try {
            //创建URL对象
            URL url = new URL("http://219.148.38.131:9003/info");//Get请求可以在Url中带参数： ①url + "?bookname=" + name;②url="http://www.baidu.com?name=zhang&pwd=123";
            //返回一个URLConnection对象，它表示到URL所引用的远程对象的连接
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //在这里设置一些属性，详细见UrlConnection文档，HttpURLConnection是UrlConnection的子类
            //设置连接超时为5秒
            httpURLConnection.setConnectTimeout(5000);
            //设定请求方式(默认为get)
            httpURLConnection.setRequestMethod("GET");
            //建立到远程对象的实际连接
            httpURLConnection.connect();
            //返回打开连接读取的输入流，输入流转化为StringBuffer类型，这一套流程要记住，常用
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line = null;
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                //转化为UTF-8的编码格式
                line = new String(line.getBytes("UTF-8"));
                stringBuffer.append(line);
            }
            System.out.println("Get请求返回的数据:" + stringBuffer.toString());
            bufferedReader.close();
            httpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void httpUrlConnectionPost() {
        try {
            //创建URL对象
            URL url = new URL("http://219.148.38.131:9003/info");
            //返回一个URLConnection对象，它表示到URL所引用的远程对象的连接
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //在这里设置一些属性，详细见UrlConnection文档，HttpURLConnection是UrlConnection的子类
            //设置连接超时为5秒
            httpURLConnection.setConnectTimeout(5000);
            //设定请求方式(默认为get)
            httpURLConnection.setRequestMethod("POST");
            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
            // http正文内，因此需要设为true, 默认情况下是false;
            httpURLConnection.setDoOutput(true);
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            httpURLConnection.setDoInput(true);
            // Post 请求不能使用缓存
            httpURLConnection.setUseCaches(false);


            //这边开始设置请求头
            // 设定传送的内容类型是可序列化的java对象(如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
            httpURLConnection.setRequestProperty("Content-type", "application/x-java-serialized-object");
            //方法setRequestProperty(String key, String value)设置一般请求属性。
            // 连接，从上述url.openConnection()至此的配置必须要在connect之前完成，
            httpURLConnection.connect();

            //这边设置请内容
            //getOutputStream()里默认就有connect（）了，可以不用写上面的连接
            //接下来我们设置post的请求参数，可以是JSON数据，也可以是普通的数据类型
            OutputStream outputStream = httpURLConnection.getOutputStream();
            /**
             * JSON数据的请求
             * outputStream.write(stringJson.getBytes(), 0, stringJson.getBytes().length);
             * outputStream.close();
             * **/
            /**
             * 字符串数据的请求
             DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
             String content = "username=" + username + "&password=" + password;
             dataOutputStream.writeBytes(content);
             dataOutputStream.flush();
             dataOutputStream.close();
             * **/
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
//            String content = "password=123&password1=123&idNumber=123&accountid=123";
//            dataOutputStream.writeBytes(content);
            dataOutputStream.flush();
            dataOutputStream.close();
            //读取返回的数据
            //返回打开连接读取的输入流，输入流转化为StringBuffer类型，这一套流程要记住，常用
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line = null;
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                //转化为UTF-8的编码格式
                line = new String(line.getBytes("UTF-8"));
                stringBuffer.append(line);
            }
            System.out.println("POST请求返回的数据:" + stringBuffer.toString());
            bufferedReader.close();
            httpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
