package tech.buildrun.cli.client;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.PostExchange;
import tech.buildrun.cli.client.dto.CreateRepositoryRequest;
import tech.buildrun.cli.client.dto.CreateRepositoryResponse;

public interface GithubClient {


    @PostExchange(value = "/user/repos")
    public CreateRepositoryResponse createRepo(@RequestHeader(name = "Authorization") String personalAccessToken,
                                               @RequestHeader(name = "X-GitHub-Api-Version", defaultValue = "2022-11-28") String githubApiVersion,
                                               @RequestBody CreateRepositoryRequest body);
}
