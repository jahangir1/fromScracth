package com.example.consumesoapwebservice1.feignClients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@FeignClient(url="https://apiservices.balady.gov.sa", value = "wasel-feign-client", path = "/oauth/v1", configuration = CoreFeignConfiguration.class)
public interface AccessTokenFiegnClient {

    @RequestMapping(value = "/token", method = RequestMethod.POST, consumes = APPLICATION_FORM_URLENCODED_VALUE)
    ResultData getToken(@RequestHeader("authorization") String authorization, @RequestHeader("content-type") String contentType
            , @RequestHeader("cache-control") String cacheControl, @RequestBody Map<String, ?> form);

}
