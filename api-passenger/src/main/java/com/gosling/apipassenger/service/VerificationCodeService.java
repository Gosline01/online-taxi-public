package com.gosling.apipassenger.service;

import com.gosling.apipassenger.remote.ServiceVerificationcodeClient;
import com.gosling.internalcommon.dto.ResponseResult;
import com.gosling.internalcommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeService {

    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;

    // 乘客验证码前缀
    private String verificationCodePrefix = "passenger-verification-code-";

    @Autowired
    private StringRedisTemplate redisTemplate;

    public ResponseResult generateCode(String passengerPhone) {

        // 调用验证码服务，获取验证码
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();

        // key, value, 过期时间
        String key = verificationCodePrefix + passengerPhone;
        // 存入redis
        redisTemplate.opsForValue().set(key, numberCode+"",2, TimeUnit.MINUTES);

        // 通过短信服务商，将对应验证码发送到手机上，阿里短信服务，腾讯短信通，华信，容联

        return ResponseResult.success("");
    }
}
