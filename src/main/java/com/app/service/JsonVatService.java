package com.app.service;

import com.app.model.Country;
import com.app.utils.CommonUtils;
import com.app.utils.UrlDownloader;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by vmusil on 27-Sep-2018.
 */
@Service
public class JsonVatService implements JsonVatServiceI {

    private static final String JSONVAT_URL = "http://jsonvat.com/";

    /**
     *
     * @return List of EU countries, can be empty, never null
     * @throws IllegalStateException in case of no internet connectivity or JSON parsing issue.
     */
    @Override
    public List<Country> getEUCountries() {
        final List<Country> countries;

        try {
            String jsonResponse = UrlDownloader.getContentFromUrl(JSONVAT_URL);

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true); // otherwise the Country List fields fails when set (json root node is array)

            JsonNode tree = mapper.readTree(jsonResponse);
            JsonNode countriesJson = tree.at("/rates");

            countries = Arrays.asList(mapper.readValue(mapper.treeAsTokens(countriesJson), Country[].class));

        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Unexpected IOException has occurred: \n" + e.getMessage(), e);
        }

        return countries;
    }
}
