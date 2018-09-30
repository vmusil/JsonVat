package com.barclays.app.controller;

import com.barclays.app.data.Country;
import com.barclays.app.data.Period;
import com.barclays.app.service.JsonVatService;
import com.barclays.app.service.JsonVatServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;

/**
 * Created by vmusil on 27-Sep-2018.
 */
@Controller
public class JsonVatController implements JsonVatControllerI {

    @Autowired
    private JsonVatServiceI jsovVatService;

    @Override
    public List<Country> getSortedCountriesByStandardVat(Date byDate) {
        List<Country> euCountries = jsovVatService.getEUCountries();

        // Keep periods just for effective date
        filterPeriodsByDate(euCountries, byDate);

        // sort by standard VAT ascending
        Collections.sort(euCountries, Country.STANDARD_VAT_COMPARATOR);

        return euCountries;
    }

    private void filterPeriodsByDate(List<Country> euCountries, Date byDate) {
        if (byDate == null) {
            throw new IllegalArgumentException("Invalid date for getting VATs ('" + byDate + "')");
        }

        for (Iterator<Country> it = euCountries.iterator(); it.hasNext(); ) {
            Country country = it.next();

            sortPeriodsByDate(country.getPeriods());

            Period periodEffectiveForDate = null;

            for (Period period : country.getPeriods()) {
                Date periodEffectiveFrom = period.getEffectiveFromDate();

                if (periodEffectiveFrom != null && !periodEffectiveFrom.after(byDate)) {
                    periodEffectiveForDate = period;
                    break;
                }
            }

            if (periodEffectiveForDate == null) {
                it.remove(); // remove the country from the list because it doesn't have any valid (effective) VATs
            } else {
                country.setPeriods(Arrays.asList(periodEffectiveForDate)); // leave there just the actual period we are interested in
            }
        }
    }

    private void sortPeriodsByDate(List<Period> periods) { // from the newest to the oldest
        Collections.sort(periods, (p1, p2) -> {
            Date effectiveFromDate1 = p1.getEffectiveFromDate();
            Date effectiveFromDate2 = p2.getEffectiveFromDate();

            if (effectiveFromDate1 == null) {
                return -1;
            } else if (effectiveFromDate2 == null) {
                return 0;
            }

            return effectiveFromDate2.compareTo(effectiveFromDate1);
        });
    }
}
