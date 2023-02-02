package com.euvatrates.euvrservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryRateData implements Serializable{
    private String country;
    private String standard_rate;
    private String reduced_rate;
    private String reduced_rate_alt;
    private String super_reduced_rate;
    private String parking_rate;
}
