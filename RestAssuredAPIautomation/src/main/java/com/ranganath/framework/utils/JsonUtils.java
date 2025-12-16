package com.ranganath.framework.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class JsonUtils {

    public static Object[][] getUsersFromJson(String path) {

        InputStream is = JsonUtils.class.getResourceAsStream(path);
        if (is == null) {
            throw new RuntimeException("Test data file not found: " + path);
        }

        String jsonStr;
        try (Scanner scanner = new Scanner(is, StandardCharsets.UTF_8)) {
            jsonStr = scanner.useDelimiter("\\A").next();
        }

        JSONObject root = new JSONObject(jsonStr);
        JSONArray usersArray = root.getJSONArray("users");

        Object[][] data = new Object[usersArray.length()][1];

        for (int i = 0; i < usersArray.length(); i++) {
            data[i][0] = usersArray.getJSONObject(i);
        }

        return data;
    }
}