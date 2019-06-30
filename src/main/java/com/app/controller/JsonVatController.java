package com.app.controller;

import com.app.model.Country;
import com.app.service.JsonVatServiceI;
import com.app.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by vmusil on 27-Sep-2018.
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/countries/vat")
public class JsonVatController {

    @Autowired
    private JsonVatServiceI jsonVatService;

    private static final String DEFAULT_COUNT = "3";

    @GetMapping("/lowest")
    private @ResponseBody
    ResponseEntity<String> printCountriesWithLowestVat(@RequestParam(defaultValue = DEFAULT_COUNT, name = "count") int count) {
        final List<Country> sortedCountries = getSortedCountries();

        final StringBuilder output = new StringBuilder();
        output
                .append("*********************************************************************")
                .append("\n")
                .append("Countries with lowest standard VAT rate for today within the EU are: ")
                .append("\n");

        for (int i = 0; count > 0 && i < sortedCountries.size(); --count, ++i) {
            Country country = sortedCountries.get(i);

            printCountryStandardVat(output, country);
        }

        return ResponseEntity.ok(output.toString().replaceAll("\n", "</br>"));
    }


    @GetMapping("/highest")
    private @ResponseBody
    ResponseEntity<String> printCountriesWithHighestVat(@RequestParam(defaultValue = DEFAULT_COUNT, name = "count") int count) {
        final List<Country> sortedCountries = getSortedCountries();

        final StringBuilder output = new StringBuilder();
        output
                .append("**********************************************************************")
                .append("\n")
                .append("Countries with highest standard VAT rate for today within the EU are: ")
                .append("\n");

        for (int i = sortedCountries.size() - 1; count > 0 && i >= 0; --count, --i) {
            Country country = sortedCountries.get(i);

            printCountryStandardVat(output, country);
        }

        return ResponseEntity.ok(output.toString().replaceAll("\n", "</br>"));
    }

    private List<Country> getSortedCountries() {
        final Date vatEffectiveFromDate = new Date();
        final List<Country> euCountries = jsonVatService.getEUCountries();
        return CommonUtils.sortCountriesByStandardVat(euCountries, vatEffectiveFromDate);
    }

    private void printCountryStandardVat(final StringBuilder output, final Country country) {
        output.append(
                String.format("Country: %s (standard VAT rate: %s)\n",
                        country.getName(), country.getPeriods().get(0).getRates().getStandard()));
    }
}
