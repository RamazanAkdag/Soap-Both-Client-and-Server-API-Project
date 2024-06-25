package com.id3.both_client_server_soap_service.server;

import com.id3.both_client_server_soap_service.FullCountryInfoResponse;
import com.id3.both_client_server_soap_service.ListOfCountryNamesByCodeResponse;
import com.id3.both_client_server_soap_service.client.CountryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    private final CountryClient countryClient;

    @Autowired
    public CountryService(CountryClient countryClient) {
        this.countryClient = countryClient;
    }

    public ListOfCountryNamesByCodeResponse getListOfCountryNamesByCode(){
        return countryClient.getListOfCountryNamesByCode();
    }

    public FullCountryInfoResponse getFullCountryInfo(String isoCode){
        return countryClient.getFullCountryInfo(isoCode);
    }
}
