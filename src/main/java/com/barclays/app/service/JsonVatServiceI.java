package com.barclays.app.service;

import com.barclays.app.data.Country;

import java.util.List;

/**
 * Created by vmusil on 30-Sep-2018.
 */
public interface JsonVatServiceI {
    List<Country> getEUCountries();
}
