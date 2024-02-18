package tech.buildrun.cli.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateRepositoryResponse(String id, @JsonProperty("html_url") String htmlUrl) {
}
