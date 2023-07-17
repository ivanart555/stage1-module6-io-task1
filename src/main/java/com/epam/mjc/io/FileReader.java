package com.epam.mjc.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {

        List<String> content = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new java.io.FileReader(file))){
            String line = br.readLine();

            while(line != null) {
                content.add(line);
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Something went wrong with file operations.");
        }

        ContentParser contentParser = new ContentParser();
        Map<String, String > contentMap = contentParser.parseContent(content);

        Profile profile = new Profile();
        profile.setName(contentMap.get("name"));
        profile.setAge(Integer.parseInt(contentMap.get("age")));
        profile.setEmail(contentMap.get("email"));
        profile.setPhone(Long.parseLong(contentMap.get("phone")));

        return profile;
    }
}
