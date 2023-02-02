package com.euvatrates.euvrservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EVRResponseData implements Serializable,Cloneable {
    private String last_updated;
    private String disclaimer;
    private HashMap<String,CountryRateData> rates;

    @Override
    public EVRResponseData clone() {
        EVRResponseData evrResponseData = null;
        try {
            evrResponseData = (EVRResponseData) super.clone();
        } catch (CloneNotSupportedException e) {
            evrResponseData = new EVRResponseData(
                    this.getLast_updated(), this.getDisclaimer(), this.getRates());
        }
        return evrResponseData;
    }

}
