package com.barclays.app.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vmusil on 28-Sep-2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {

    @JsonProperty("standard")
    private Float standard;

    @JsonProperty("super_reduced")
    private Float superReduced;

    @JsonProperty("reduced")
    private Float reduced;

    @JsonProperty("reduced1")
    private Float reduced1;

    @JsonProperty("reduced2")
    private Float reduced2;

    @JsonProperty("parking")
    private Float parking;

    public Float getStandard() {
        return standard;
    }

    public void setStandard(Float standard) {
        this.standard = standard;
    }

    public Float getSuperReduced() {
        return superReduced;
    }

    public void setSuperReduced(Float superReduced) {
        this.superReduced = superReduced;
    }

    public Float getReduced() {
        return reduced;
    }

    public void setReduced(Float reduced) {
        this.reduced = reduced;
    }

    public Float getReduced1() {
        return reduced1;
    }

    public void setReduced1(Float reduced1) {
        this.reduced1 = reduced1;
    }

    public Float getReduced2() {
        return reduced2;
    }

    public void setReduced2(Float reduced2) {
        this.reduced2 = reduced2;
    }

    public Float getParking() {
        return parking;
    }

    public void setParking(Float parking) {
        this.parking = parking;
    }
}
