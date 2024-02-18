package tech.buildrun.cli.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class GeneralConfig {

    private static final ObjectMapper mapper = new ObjectMapper();
    public static final String USER_HOME_FOLDER = System.getProperty("user.home");
    public static final String CONFIG_FOLDER = USER_HOME_FOLDER + "/.buildrun";
    public static final String CONFIG_FILE = CONFIG_FOLDER + "/config.json";

    public static Optional<String> get(ConfigName configName) {
        var config = getConfig();

        return StringUtils.hasText(config.get(configName.name()).asText()) ?
                Optional.of(config.get(configName.name()).asText()) : Optional.empty();
    }

    public static void updateConfigFile(String configFile, String token) {
        try {
            ObjectNode objectNode = getConfig();
            objectNode.put(ConfigName.GITUHB_PERSONAL_ACCESS_TOKEN.name(), token);
            var modifiedJson = mapper.writeValueAsString(objectNode);
            Files.writeString(Paths.get(configFile), modifiedJson);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static void createConfigFile(String configFile) {
        try {
            var fileExists = new File(configFile).exists();

            if (!fileExists) {
                System.out.println("Creating config file at: " + configFile);
                var created = new File(configFile).createNewFile();
                if (created) {
                    System.out.println("Config File created!");
                } else {
                    System.out.println("Config File has not been created!");
                }
            } else {
                System.out.println("Config File already exists!");
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static void createConfigFolder(String configFolder) {
        var folderExists = new File(configFolder).exists();

        if (!folderExists) {
            System.out.println("Creating config folder at: "+ configFolder);
            var created = new File(configFolder).mkdir();
            if (created) {
                System.out.println("Config Folder created!");
            } else {
                System.out.println("Config Folder has not been created!");
            }
        } else {
            System.out.println("Config Folder already exists!");
        }
    }

    public static ObjectNode getConfig() {
        try {
            var json = Files.readString(Paths.get(CONFIG_FILE));
            if (!StringUtils.hasText(json)) {
                json = "{}";
            }

            return mapper.readValue(json, ObjectNode.class);

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
