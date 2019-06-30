package com.app.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by vmusil on 27-Sep-2018.
 */
public final class UrlDownloader {


    public static final String getContentFromUrl(final String url) throws IOException {
        final InputStream is = new URL(url).openStream();

        String doc;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            doc = readContent(br);
        }

        return doc;
    }

    private static String readContent(final BufferedReader br) throws IOException {
        final StringBuilder sb = new StringBuilder();

        int c;
        while ((c = br.read()) != -1) {
            sb.append((char)c);
        }

        return sb.toString();
    }

}
