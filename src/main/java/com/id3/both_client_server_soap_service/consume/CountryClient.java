package com.id3.both_client_server_soap_service.consume;

import com.id3.both_client_server_soap_service.ListOfCountryNamesByCode;
import com.id3.both_client_server_soap_service.ListOfCountryNamesByCodeResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


public class CountryClient extends WebServiceGatewaySupport {

    public ListOfCountryNamesByCodeResponse getListOfCountryNamesByCode(){

        ListOfCountryNamesByCode request = new ListOfCountryNamesByCode();

        ListOfCountryNamesByCodeResponse response = (ListOfCountryNamesByCodeResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso",
                        request, new SoapActionCallback("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfCountryNamesByCode"));

        return response;
    }

}
