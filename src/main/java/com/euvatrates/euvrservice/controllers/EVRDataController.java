package com.euvatrates.euvrservice.controllers;

import com.euvatrates.euvrservice.entities.EVRResponseData;
import com.euvatrates.euvrservice.services.CachingService;
import com.euvatrates.euvrservice.services.EVRDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/evrservice/", produces = MediaType.APPLICATION_JSON_VALUE)
public class EVRDataController {

    @Autowired
    private EVRDataService evrDataService;
    @Autowired
    private CachingService cachingService;
    @GetMapping("/list/all")
    public EVRResponseData listAll(){
        return evrDataService.getCompleteJson();
    }
    @GetMapping("/list/with_highest_standard_rate")
    public EVRResponseData listHighestStandardRate(){
        return evrDataService.getEvrForCountriesWithHighestStandardRate();
    }

    @GetMapping("/list/with_lowest_reduced_vat_rate")
    public EVRResponseData listLowestReducedVatRate(){
        return evrDataService.getEvrForCountriesWithLowestReducedVATRate();
    }
    @GetMapping("/clearAllCaches")
    public void clearAllCaches() {
        cachingService.evictAllCacheValues();
    }
}
