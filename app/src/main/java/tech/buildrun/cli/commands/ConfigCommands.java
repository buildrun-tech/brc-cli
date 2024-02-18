package tech.buildrun.cli.commands;

import org.jline.reader.LineReader;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.StringUtils;
import tech.buildrun.cli.config.GeneralConfig;

@ShellComponent
public class ConfigCommands {

    private LineReader lineReader;

    public ConfigCommands(@Lazy LineReader lineReader) {
        this.lineReader = lineReader;
    }

    @ShellMethod(key = "configure", value = "Configure the Build & Run CLI with yours credentials")
    public void configure() {
        System.out.println("⚙\uFE0F Configuring Build & Run CLI");

        System.out.println("Github Personal Access Token: ");

        var token = lineReader.readLine();
        if (!StringUtils.hasText(token)) {
            System.out.println("Github Personal Access Token is required");
            return;
        }

        GeneralConfig.createConfigFolder(GeneralConfig.CONFIG_FOLDER);
        GeneralConfig.createConfigFile(GeneralConfig.CONFIG_FILE);
        GeneralConfig.updateConfigFile(GeneralConfig.CONFIG_FILE, token);

        System.out.println("Build & Run CLI has been successfully configured!");
    }

    @ShellMethod(key = "myconfig", value = "Print your current config for Build & Run CLI")
    public void myconfig() {

        var configs = GeneralConfig.getConfig();

        configs.fields().forEachRemaining(entry -> {
            System.out.println(entry.getKey() + "=" + entry.getValue().asText());
        });
    }


}
