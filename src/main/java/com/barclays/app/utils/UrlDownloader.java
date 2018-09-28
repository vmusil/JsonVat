package com.barclays.app.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by vmusil on 27-Sep-2018.
 */
public final class UrlDownloader {


    public static final String getContentFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();

        String doc;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            doc = readContent(br);
        }

        return doc;
    }

    private static String readContent(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();

        int c;
        while ((c = br.read()) != -1) {
            sb.append((char)c);
        }

        return sb.toString();
    }

}
