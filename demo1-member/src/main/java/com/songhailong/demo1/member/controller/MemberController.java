package com.songhailong.demo1.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
@ResponseBody
public class MemberController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "membercenter: Hello";
    }

    @GetMapping("/get")
    public Object get() {
        String url = String.format("http://%s/hello", "user-center-service");
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    @GetMapping("/hotel")
    public Object hotel() {
        String url = String.format("http://%s/health", "node-service");
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }
    @GetMapping("/test")
    public Object test() {
        String url = String.format("http://%s/index", "node-service");
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    private void httpURLGETCase() {
        String methodUrl = "http://192.168.0.100:8188/api/health";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String line = null;
        try {
            URL url = new URL(methodUrl);
            connection = (HttpURLConnection) url.openConnection();// 根据URL生成HttpURLConnection
            connection.setRequestMethod("GET");// 默认GET请求
            connection.connect();// 建立TCP连接
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 发送http请求
                StringBuilder result = new StringBuilder();
                // 循环读取流
                while ((line = reader.readLine()) != null) {
                    result.append(line).append(System.getProperty("line.separator"));// "\n"
                }
                System.out.println(result.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.disconnect();
        }
    }

}
