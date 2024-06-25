package com.id3.both_client_server_soap_service.client;

import com.id3.both_client_server_soap_service.FullCountryInfo;
import com.id3.both_client_server_soap_service.FullCountryInfoResponse;
import com.id3.both_client_server_soap_service.ListOfCountryNamesByCode;
import com.id3.both_client_server_soap_service.ListOfCountryNamesByCodeResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Repository
public class CountryClient extends WebServiceGatewaySupport {

    public ListOfCountryNamesByCodeResponse getListOfCountryNamesByCode(){

        ListOfCountryNamesByCode request = new ListOfCountryNamesByCode();

        ListOfCountryNamesByCodeResponse response = (ListOfCountryNamesByCodeResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso",
                        request, new SoapActionCallback("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfCountryNamesByCode"));

        return response;
    }

    public FullCountryInfoResponse getFullCountryInfo(String isoCode){
        FullCountryInfo request = new FullCountryInfo();
        request.setSCountryISOCode(isoCode);

        FullCountryInfoResponse response = (FullCountryInfoResponse) getWebServiceTemplate().marshalSendAndReceive(
                "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso",
                request,
                new SoapActionCallback("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfCountryNamesByCode")
        );

        return response;

    }

}
