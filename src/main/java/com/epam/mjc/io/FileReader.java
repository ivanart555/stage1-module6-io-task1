package com.epam.mjc.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FileReader {
    Logger logger = LoggerFactory.getLogger("com.epam.mjc.io.FileReader");

    public Profile getDataFromFile(File file) {

        List<String> content = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line = br.readLine();

            while (line != null) {
                content.add(line);
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            logger.warn("File " + "(Path:" + file.getPath() + ")" + " not found!");
        } catch (IOException e) {
            logger.warn("Something went wrong with IO!");
        }

        ContentParser contentParser = new ContentParser();
        Map<String, String> contentMap = contentParser.parseContent(content);

        Profile profile = new Profile();
        profile.setName(contentMap.get("name"));
        profile.setEmail(contentMap.get("email"));

        try {
            profile.setAge(Integer.parseInt(contentMap.get("age")));
            profile.setPhone(Long.parseLong(contentMap.get("phone")));
        } catch (NumberFormatException e) {
            logger.warn("Failed to parse numeric attributes for Profile!");
        }

        return profile;
    }
}
