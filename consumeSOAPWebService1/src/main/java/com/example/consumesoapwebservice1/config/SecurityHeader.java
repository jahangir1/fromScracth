package com.example.consumesoapwebservice1.config;

import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import javax.xml.soap.MimeHeaders;
import javax.xml.transform.TransformerException;
import java.io.IOException;

@Component
public class SecurityHeader implements WebServiceMessageCallback {

   String oAuthToken;

    public String getoAuthToken() {
        return oAuthToken;
    }

    public void setoAuthToken(String oAuthToken) {
        this.oAuthToken = oAuthToken;
    }

    public SecurityHeader(String oAuthToken) {
        this.oAuthToken = oAuthToken;
    }

    public SecurityHeader( ) {
       super();
    }



    @Override
    public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
        System.out.println("In SecurityHeader >>> doWithMessage ");
        try {
            System.out.println("Value in  this.oAuthToken >> " + this.oAuthToken);
                if (message instanceof SaajSoapMessage) {
                SaajSoapMessage soapMessage = (SaajSoapMessage) message;
                MimeHeaders mimeHeader = soapMessage.getSaajMessage().getMimeHeaders();
//                mimeHeader.setHeader("Authorization", "Bearer F3wfQJz4NwzsXee1RgwS0vzFGcZG");
                    mimeHeader.setHeader("Authorization", this.oAuthToken);
                    mimeHeader.setHeader("Content-Type", "text/xml");
                mimeHeader.setHeader("SOAPAction","http://tempuri.org/IWaselAddressService/GetIndividualWaselAddress");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
