package com.barclays.app;

import com.barclays.app.controller.JsonVatController;
import com.barclays.app.data.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * Created by vmusil on 27-Sep-2018.
 */
public class App {

    @Autowired
    private JsonVatController jsonVatController;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = registerSpringAppContext();

        ctx.getBean(App.class)
                .printResult();
    }

    private static AnnotationConfigApplicationContext registerSpringAppContext() {
        // register beans scanning
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();

        return ctx;
    }

    private void printResult() {
        Date vatEffectiveFromDate = new Date();
        List<Country> sortedCountries = jsonVatController.getSortedCountriesByStandardVat(vatEffectiveFromDate);

        printCountriesWithLowestVat(sortedCountries, 3);
        printCountriesWithHighestVat(sortedCountries, 3);
    }

    private void printCountriesWithLowestVat(List<Country> sortedCountries, int count) {
        System.out.println("*********************************************************************");
        System.out.println("Countries with lowest standard VAT rate for today within the EU are: ");

        for (int i = 0; count > 0 && i < sortedCountries.size(); --count, ++i) {
            Country country = sortedCountries.get(i);

            printCountryStandardVat(country);
        }
    }

    private void printCountriesWithHighestVat(List<Country> sortedCountries, int count) {
        System.out.println("**********************************************************************");
        System.out.println("Countries with highest standard VAT rate for today within the EU are: ");

        for (int i = sortedCountries.size() - 1; count > 0 && i >= 0; --count, --i) {
            Country country = sortedCountries.get(i);

            printCountryStandardVat(country);
        }
    }

    private void printCountryStandardVat(Country country) {
        System.out.println(
                String.format("Country: %s (standard VAT rate: %s)",
                        country.getName(), country.getPeriods().get(0).getRates().getStandard()));
    }
}
