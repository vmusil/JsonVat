package com.app.service;

import com.app.AppTestConfig;
import com.app.model.Country;
import com.app.utils.CommonUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by vmusil on 30-Sep-2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfig.class})
public class JsonVatTest {

    @Autowired
    private JsonVatServiceI service;

    @Test
    public void verifySortingOfStandardVAT() {
        final List<Country> countriesSortedByStandardVat = CommonUtils.sortCountriesByStandardVat(service.getEUCountries(), new Date());

        Assert.assertEquals("First country has to have lowest standard VAT",
                (Object)countriesSortedByStandardVat.get(0).getPeriods().get(0).getRates().getStandard(), 1F);

        Assert.assertEquals(
                (Object)countriesSortedByStandardVat.get(1).getPeriods().get(0).getRates().getStandard(), 3F);
        Assert.assertEquals(
                (Object)countriesSortedByStandardVat.get(2).getPeriods().get(0).getRates().getStandard(), 5F);
    }
}
