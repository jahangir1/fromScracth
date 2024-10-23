package com.example.consumesoapwebservice1.feignClients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GetToken {

    @Autowired
    AccessTokenFiegnClient accessTokenFiegnClient;

    public String getToken() {
        System.out.println("In GetToken >>> getToken");

        String authorization = "Basic QlluNXBaZms5Uzk3SDU0RkNpR1hFcnFsQTFBaEFnNHg6bkM3V3JBeDU0REd3SmJHQQ==";
        String contentType ="application/x-www-form-urlencoded";
        String cacheControl = "no-cache";
        Map<String, String> form = new HashMap<>();
        form.put("grant_type","client_credentials");
        ResultData data = accessTokenFiegnClient.getToken(authorization, contentType, cacheControl, form);
        System.out.println("Access_token >> " + data.getAccess_token());
        return "Bearer "+data.getAccess_token();
    }

    public GetToken() {
    }
}
