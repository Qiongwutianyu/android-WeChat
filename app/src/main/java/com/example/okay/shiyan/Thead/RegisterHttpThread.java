package com.example.okay.shiyan.Thead;


import com.example.okay.shiyan.utils.HttpMethod;
import com.example.okay.shiyan.utils.MyProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 注册
 */
@SuppressWarnings("all")
public class RegisterHttpThread extends Thread{
    private String name;
    private String password;
    private String sex;
    private boolean isOk;
    public RegisterHttpThread() {
    }
    public RegisterHttpThread(String name, String password, String sex){
        this.name=name;
        this.password=password;
        this.sex=sex;
    }

    @Override
    public void run() {
        try {
            URL url=new URL(MyProperties.URL+"/reg");
            //获取连接
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            //设置请求方法
            httpURLConnection.setRequestMethod(String.valueOf(HttpMethod.POST));
            String body="name="+URLEncoder.encode(name,"utf-8")
                    +"&password="+URLEncoder.encode(password,"utf-8")
                    +"&sex="+URLEncoder.encode(sex,"utf-8");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.getOutputStream().write(body.getBytes());
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode()==200){
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader  inputStreamReader=new InputStreamReader(inputStream,"utf-8");
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String temp;
                StringBuffer stringBuffer=new StringBuffer();
                while((temp=bufferedReader.readLine()) !=null){
                    stringBuffer.append(temp);
                }
                isOk=stringBuffer.toString().equals("yes");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/**
 * 返回注册结果
 * @returnSS
 */
    public boolean isOk() {
        return isOk;
    }
}
