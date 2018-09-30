package com.barclays.app.controller;

import com.barclays.app.data.Country;

import java.util.Date;
import java.util.List;

/**
 * Created by vmusil on 30-Sep-2018.
 */
public interface JsonVatControllerI {

    /**
     *
     * @param vatEffectiveFromDate
     * @return EU countries sorted by standard VAT ascending, effective since the given day (inclusive)
     */
    List<Country> getSortedCountriesByStandardVat(Date vatEffectiveFromDate);
}
