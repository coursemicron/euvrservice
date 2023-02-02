package com.euvatrates.euvrservice.services;

import com.euvatrates.euvrservice.entities.CountryRateData;
import com.euvatrates.euvrservice.entities.EVRResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EVRDataService {
    @Autowired
    private EVRDataService self;
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${evr.url}")
    private String evrResourceUrl="";
    @Value("${evr.limit}")
    private int limit=3;

    @Cacheable(value = "evrJsonCache")
    public EVRResponseData getCompleteJson(){
        ResponseEntity<EVRResponseData> response  = restTemplate.getForEntity(evrResourceUrl, EVRResponseData.class);
        return response.getBody();
    }

    public EVRResponseData getClonedJson(){
        if(null!=self)
        return self.getCompleteJson().clone();
        else return getCompleteJson();
    }

    public EVRResponseData getEvrForCountriesWithHighestStandardRate(){
        EVRResponseData evrResponseData = getClonedJson();
        Map<String, CountryRateData> rates = evrResponseData.getRates();
        Comparator<CountryRateData> byStandardRate = (CountryRateData obj1, CountryRateData obj2) -> obj2.getStandard_rate().compareTo(obj1.getStandard_rate());
        LinkedHashMap<String, CountryRateData> sortedRateMap = rates.entrySet().stream()
                .sorted(Map.Entry.<String, CountryRateData>comparingByValue(byStandardRate))
                .limit(limit)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        evrResponseData.setRates(sortedRateMap);
        return evrResponseData;
    }

    public EVRResponseData getEvrForCountriesWithLowestReducedVATRate(){
        EVRResponseData evrResponseData = getClonedJson();
        Map<String, CountryRateData> rates = evrResponseData.getRates();
        Comparator<CountryRateData> byStandardRate = (CountryRateData obj1, CountryRateData obj2) -> obj1.getReduced_rate().compareTo(obj1.getReduced_rate());
        LinkedHashMap<String, CountryRateData> sortedRateMap = rates.entrySet().stream()
                .sorted(Map.Entry.<String, CountryRateData>comparingByValue(byStandardRate))
                .limit(limit)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        evrResponseData.setRates(sortedRateMap);
        return evrResponseData;
    }
}
