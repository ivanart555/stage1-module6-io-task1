package com.epam.mjc.io;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ContentParser {

    public Map<String, String> parseContent(List<String> content) {
        Map<String, String> parsed = new HashMap<>();

        for (String str : content) {
            String[] keyValue = str.split(": ");
            parsed.put(keyValue[0].toLowerCase(Locale.ROOT), keyValue[1]);
        }

        return parsed;

    }


}
