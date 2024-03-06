package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonConfig {
    ObjectMapper objectMapper = new ObjectMapper();
    public Config jsonFileToPojo(String path) throws IOException, InterruptedException {
        File file = new File(path);
        if(file.exists()) {
            System.out.println("File found");
        } else {
            System.out.println("No found");
        }

        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(path));
            String fileContent = new String(fileBytes);

            System.out.println(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Config config = null;
        try {
            config = objectMapper.readValue(file, Config.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return config;
    }
}
