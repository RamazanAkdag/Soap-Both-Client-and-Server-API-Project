package com.id3.both_client_server_soap_service;

import com.id3.both_client_server_soap_service.consume.CountryClient;
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
			System.err.println(response.getListOfCountryNamesByCodeResult().tCountryCodeAndName.get(0).sName);
		};
	}


}
