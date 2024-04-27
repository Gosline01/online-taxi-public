package com.gosling.serviceverificationcode.controller;

import com.gosling.internalcommon.dto.ResponseResult;
import com.gosling.internalcommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size) {
        System.out.println("size: "+ size);
        // 生成验证码
        double random = (Math.random()*9 + 1) * (Math.pow(10, size-1));
        System.out.println(random);
        int resultInt = (int) random;
        JSONObject result = new JSONObject();
        System.out.println("src number code:"+resultInt);
        result.put("code", 1);
        result.put("message", "success");
        JSONObject data = new JSONObject();
        data.put("numberCode", resultInt);
        result.put("data", data);

        //定义返回值
        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(resultInt);

        return  ResponseResult.success(response);
    }

    public static void main(String[] args) {
        // 获取随机数
        double random = (Math.random()*9 + 1) * (Math.pow(10, 5));
        System.out.println(random);
        int resultInt = (int) random;
        System.out.println("src number code:"+resultInt);
    }
}
