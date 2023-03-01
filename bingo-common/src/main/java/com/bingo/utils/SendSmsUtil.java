package com.bingo.utils;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.bingo.constant.AliConfigConstant;
import com.google.gson.Gson;
import darabonba.core.client.ClientOverrideConfiguration;

import java.util.concurrent.CompletableFuture;

public class SendSmsUtil {

    /**
     * 发送短信
     */
    public static void sendMsg(String phone) throws Exception {
        String accessKeyId = AliConfigConstant.ACCESS_KEY_ID;
        String accessKeySecret = AliConfigConstant.ACCESS_KEY_SECRET;
        String signName = AliConfigConstant.SIGN_NAME;
        String templateCode = AliConfigConstant.TEMPLATE_CODE;

        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(accessKeyId)
                .accessKeySecret(accessKeySecret)
                .build());

        AsyncClient client = AsyncClient.builder()
                .region("cn-beijing") // Region ID
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create().setEndpointOverride("dysmsapi.aliyuncs.com")
                )
                .build();

        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .signName(signName)
                .templateCode(templateCode)
                .phoneNumbers(phone)
                .templateParam("{\"code\":\"1234\"}")
                .build();

        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        SendSmsResponse resp = response.get();
        System.out.println(new Gson().toJson(resp));
        client.close();
    }

    /**
     * 生成6位数验证码
     */
    public static String generateCode() {
        return null;
    }


//    public static void main(String[] args) throws Exception {
//        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
//                .accessKeyId(AliConfigConstant.ACCESS_KEY_ID)
//                .accessKeySecret(AliConfigConstant.ACCESS_KEY_SECRET)
//                .build());
//
//        AsyncClient client = AsyncClient.builder()
//                .region("cn-beijing") // Region ID
//                .credentialsProvider(provider)
//                .overrideConfiguration(
//                        ClientOverrideConfiguration.create()
//                                .setEndpointOverride("dysmsapi.aliyuncs.com")
//                )
//                .build();
//
//        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
//                .signName("阿里云短信测试")
//                .templateCode("SMS_154950909")
//                .phoneNumbers("18809840129")
//                .templateParam("{\"code\":\"1234\"}")
//                .build();
//
//        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
//        SendSmsResponse resp = response.get();
//        System.out.println(new Gson().toJson(resp));
//        client.close();
//    }
}