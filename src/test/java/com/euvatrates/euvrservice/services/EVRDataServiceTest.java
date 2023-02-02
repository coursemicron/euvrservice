package com.euvatrates.euvrservice.services;

import com.euvatrates.euvrservice.entities.EVRResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class EVRDataServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    EVRDataService evrDataService = new EVRDataService();
    private Resource expectedResult = new ClassPathResource("testdata.json");

    @Test
    void getEvrForCountriesWithHighestStandardRate() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        String expectedJson = Files
                .lines(expectedResult.getFile().toPath())
                .collect(Collectors.joining());
        EVRResponseData expectedData = objectMapper.readValue(expectedJson,EVRResponseData.class);
        Mockito
                .when(restTemplate.getForEntity(
                        anyString(), anyObject()))
          .thenReturn(new ResponseEntity(expectedData, HttpStatus.OK));

        System.out.println(expectedData.getRates());


        EVRResponseData actualResponse = evrDataService.getEvrForCountriesWithHighestStandardRate();
        System.out.println(actualResponse);

        assert actualResponse.getRates().size()==expectedData.getRates().size();
        assert actualResponse.getRates().containsKey("HU");
        assert actualResponse.getRates().containsKey("DK");
        assert actualResponse.getRates().containsKey("HR");
    }

    @Test
    void getEvrForCountriesWithLowestReducedVATRate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String expectedJson = Files
                .lines(expectedResult.getFile().toPath())
                .collect(Collectors.joining());
        EVRResponseData expectedData = objectMapper.readValue(expectedJson,EVRResponseData.class);
        Mockito
                .when(restTemplate.getForEntity(
                        anyString(), anyObject()))
                .thenReturn(new ResponseEntity(expectedData, HttpStatus.OK));

        System.out.println(expectedData.getRates());


        EVRResponseData actualResponse = evrDataService.getEvrForCountriesWithLowestReducedVATRate();
        System.out.println(actualResponse);

        assert actualResponse.getRates().size()==expectedData.getRates().size();
        assert actualResponse.getRates().containsKey("DE");
        assert actualResponse.getRates().containsKey("BE");
        assert actualResponse.getRates().containsKey("FI");
    }
}