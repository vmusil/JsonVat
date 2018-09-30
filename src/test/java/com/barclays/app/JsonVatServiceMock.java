package com.barclays.app;

import com.barclays.app.data.Country;
import com.barclays.app.data.Period;
import com.barclays.app.data.Rates;
import com.barclays.app.service.JsonVatServiceI;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by vmusil on 30-Sep-2018.
 */
@Service
public class JsonVatServiceMock implements JsonVatServiceI {

    @Override
    public List<Country> getEUCountries() {
        return Arrays.asList(
                createCountry("Czech Republic", "CZ", "2015-01-01", 5),
                createCountry("Slovak Republic", "SK", "2017-01-01", 3),
                createCountry("Poland", "PL", "2018-01-01", 1)
        );
    }

    private Country createCountry(String countryName, String countryCode, String effectiveDate, float standardVat) {
        Country c1 = new Country();
        c1.setCode(countryCode);
        c1.setCountryCode(countryCode);
        c1.setName(countryName);

        Period p1 = new Period();
        p1.setEffectiveFrom(effectiveDate);
        Rates r1 = new Rates();
        r1.setStandard(standardVat);
        p1.setRates(r1);
        c1.setPeriods(Arrays.asList(p1));

        return c1;
    }
}
