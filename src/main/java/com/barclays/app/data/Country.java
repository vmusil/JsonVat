package com.barclays.app.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;
import java.util.List;

/**
 * Created by vmusil on 27-Sep-2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    public static final Comparator STANDARD_VAT_COMPARATOR = new Comparator<Country>() {

        @Override
        public int compare(Country c1, Country c2) {
            if (c1.getPeriods() == null || c1.getPeriods().isEmpty()) {
                return 0;

            } else if (c2.getPeriods() == null || c2.getPeriods().isEmpty()) {
                return -1;
            }

            return c1.getPeriods().get(0).getRates().getStandard().compareTo(
                    c2.getPeriods().get(0).getRates().getStandard()
            );
        }
    };

    @JsonProperty("name")
    private String name;

    @JsonProperty("code")
    private String code;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("periods")
    private List<Period> periods;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }
}
