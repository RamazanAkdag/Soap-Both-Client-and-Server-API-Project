package com.id3.both_client_server_soap_service;

import com.id3.both_client_server_soap_service.client.CountryClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BothClientServerSoapServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(BothClientServerSoapServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(CountryClient countryClient) {
		return args -> {
			ListOfCountryNamesByCodeResponse response = countryClient.getListOfCountryNamesByCode();
			String isocode = response.getListOfCountryNamesByCodeResult().tCountryCodeAndName.get(0).sisoCode;
			System.err.println(isocode);

			FullCountryInfoResponse response2 = countryClient.getFullCountryInfo(isocode);

			System.err.println();
			System.err.println(response2.fullCountryInfoResult.sCapitalCity);
			System.err.println(response2.fullCountryInfoResult.sName);
			System.err.println(response2.fullCountryInfoResult.languages.tLanguage.get(0).sName);
		};
	}


}
