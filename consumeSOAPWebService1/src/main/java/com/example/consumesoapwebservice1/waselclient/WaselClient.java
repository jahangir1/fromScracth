package com.example.consumesoapwebservice1.waselclient;

import com.example.consumesoapwebservice1.config.SecurityHeader;
import com.example.consumesoapwebservice1.feignClients.GetToken;
import com.example.consumesoapwebservice1.service.WaselService;
import com.example.consumesoapwebservice1.stubs.GetIndividualWaselAddress;
import com.example.consumesoapwebservice1.stubs.GetIndividualWaselAddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;

@Component
public class WaselClient extends WebServiceGatewaySupport {

    @Autowired
    SecurityHeader securityHeader;
    @Autowired
    GetToken getTokenClass;

    @Autowired
    WaselService waselService;

       public GetIndividualWaselAddressResponse getIndividualAddress(String url, Object request){
           System.out.println("----> " +request.getClass().getName());
           System.out.println("In WaselClient >>> getIndividualAddress");
          String token = getTokenClass.getToken();

       JAXBElement<GetIndividualWaselAddressResponse> res = null ;
       SecurityHeader requestCallback = new SecurityHeader(token);


       Object obj = getWebServiceTemplate().marshalSendAndReceive(url, request, requestCallback);
           System.out.println("request >>. " + request.getClass());
           GetIndividualWaselAddress requestbody = (GetIndividualWaselAddress) request;
           String identifier = requestbody.getIdentifier();
           waselService.saveAddress((GetIndividualWaselAddressResponse)obj, identifier);
       return (GetIndividualWaselAddressResponse)obj;
   }
}
