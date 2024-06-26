package com.id3.both_client_server_soap_service.server;

import com.example.soapservice.LanguageListType;
import com.id3.both_client_server_soap_service.ListOfCountryNamesByCodeResponse;
import com.example.soapservice.CountryLanguageInfosRequest;
import com.example.soapservice.CountryLanguageInfosResponse;
import com.id3.both_client_server_soap_service.TCountryCodeAndName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Optional;

@Endpoint
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/soapservice";

    private final CountryService countryService;

    @Autowired
    public CountryEndpoint(CountryService countryService) {
        this.countryService = countryService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CountryLanguageInfosRequest")
    @ResponsePayload
    public CountryLanguageInfosResponse getCountryLanguageInfos(@RequestPayload CountryLanguageInfosRequest request){
        ListOfCountryNamesByCodeResponse res1 = countryService.getListOfCountryNamesByCode();
        var countries = res1.getListOfCountryNamesByCodeResult().getTCountryCodeAndName();

        Optional<TCountryCodeAndName> countryOpt = countries.stream()
                .filter(country -> country.getSISOCode().equalsIgnoreCase(request.getCountryCode()))
                .findFirst();

        CountryLanguageInfosResponse response = new CountryLanguageInfosResponse();

        if(countryOpt.isPresent()){
            var country = countryOpt.get();
            var fullCountryInfo = countryService.getFullCountryInfo(country.getSISOCode());
            System.err.println("COUNTRY : " + country.getSName() + "----------");

            response.setCountryName(country.getSName());
            response.setLanguages(new LanguageListType());

            var languages = fullCountryInfo.getFullCountryInfoResult().getLanguages().getTLanguage();
            for(var lang : languages){
                System.err.println("language : " + lang.getSName());

                response.getLanguages().getLanguage().add(lang.getSName());
            }
        }
        return response;

    }


}
