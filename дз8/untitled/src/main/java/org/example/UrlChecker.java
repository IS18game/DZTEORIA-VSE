package org.example;

import java.net.HttpURLConnection;
import java.net.URL;

public class UrlChecker implements Runnable {
    private final String url;

    public UrlChecker(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            conn.setRequestMethod("GET");
            int code = conn.getResponseCode();
            long duration = System.currentTimeMillis() - start;
            System.out.printf("URL: %-50s | Status: %d | Time: %d ms%n", url, code, duration);
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - start;
            System.out.printf("URL: %-50s | ERROR: %s | Time: %d ms%n", url, e.getClass().getSimpleName(), duration);
        }
    }
}