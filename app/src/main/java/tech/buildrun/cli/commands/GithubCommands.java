package tech.buildrun.cli.commands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import tech.buildrun.cli.client.GithubClient;
import tech.buildrun.cli.client.dto.CreateRepositoryRequest;
import tech.buildrun.cli.config.ConfigName;
import tech.buildrun.cli.config.GeneralConfig;

@ShellComponent
public class GithubCommands {

    private final GithubClient githubClient;

    public GithubCommands(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    @ShellMethod(key = "newrepo", value = "Creates a new repository in the github")
    public void newRepo(@ShellOption(value = "name", help = "Name of the repository") String repoName) {
        var personalAccessToken = GeneralConfig.get(ConfigName.GITUHB_PERSONAL_ACCESS_TOKEN);
        if (personalAccessToken.isEmpty()) {
            System.out.println(ConfigName.GITUHB_PERSONAL_ACCESS_TOKEN.name() + " config is not set.");
            return;
        }

        System.out.println("\uD83E\uDE84 Creating repository...");

        var resp = githubClient.createRepo(
                "bearer " + personalAccessToken.get(),
                null,
                new CreateRepositoryRequest(repoName, "Repository generated from Build & Run CLI", true)
        );

        System.out.println("===================================================");
        System.out.println("\uD83C\uDF8A Yeah! Repository has been created successfuly!");
        System.out.println("RepoId: " + resp.id());
        System.out.println("URL: " + resp.htmlUrl());
        System.out.println("===================================================");
    }
}
