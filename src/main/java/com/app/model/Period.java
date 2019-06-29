package com.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vmusil on 28-Sep-2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Period {

    private static final DateFormat EFFECTIVE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @JsonProperty("effective_from")
    private String effectiveFrom;

    @JsonProperty("rates")
    private Rates rates;

    public String getEffectiveFrom() {
        return effectiveFrom;
    }

    /**
     *
     * @return NULL in case of bad Date format
     */
    public Date getEffectiveFromDate() {
        try {
            return EFFECTIVE_DATE_FORMAT.parse(effectiveFrom);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setEffectiveFrom(String effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }
}
