package com.app.utils;

import com.app.model.Country;
import com.app.model.Period;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by vmusil on 29-Jun-2019.
 */
public final class CommonUtils {

    private CommonUtils() {}

    public static List<Country> sortCountriesByStandardVat(final List<Country> countries, final Date byDate) {
        // Keep periods just for effective date
        CommonUtils.filterPeriodsByDate(countries, byDate);

        // sort by standard VAT ascending
        Collections.sort(countries, Country.STANDARD_VAT_COMPARATOR);

        return countries;
    }

    public static void filterPeriodsByDate(List<Country> euCountries, Date byDate) {
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

    private static void sortPeriodsByDate(List<Period> periods) { // from the newest to the oldest
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
