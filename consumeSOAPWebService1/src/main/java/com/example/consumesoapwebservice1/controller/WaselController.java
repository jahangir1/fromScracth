package com.example.consumesoapwebservice1.controller;

import com.example.consumesoapwebservice1.stubs.GetIndividualWaselAddress;
import com.example.consumesoapwebservice1.stubs.GetIndividualWaselAddressResponse;
import com.example.consumesoapwebservice1.stubs.ItemChoiceType;
import com.example.consumesoapwebservice1.stubs.ObjectFactory;
import com.example.consumesoapwebservice1.waselclient.WaselClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WaselController {

    @Autowired
    WaselClient waselClient;

    @GetMapping("/getIndividualAddress/{nationalId}")
    public GetIndividualWaselAddressResponse getIndividualAddress(@PathVariable String nationalId) {
        System.out.println("In WaselController >> getIndividualAddress");
        ObjectFactory objectFactory = new ObjectFactory();
        GetIndividualWaselAddress requestBody = objectFactory.createGetIndividualWaselAddress();
        if(nationalId.startsWith("1")){
            requestBody.setIdentifierType(ItemChoiceType.NATIONAL_ID);
        }
        if(nationalId.startsWith("2")){
            requestBody.setIdentifierType(ItemChoiceType.IQAMA_NUMBER);
        }
        requestBody.setIdentifier(nationalId);
        System.out.println("Passed Id >>> " +nationalId);

        GetIndividualWaselAddressResponse individualAddressResponse = waselClient.getIndividualAddress("https://apiservices.balady.gov.sa/v1/gsb/wasel-address-service", requestBody);
        return individualAddressResponse;


    }
}
