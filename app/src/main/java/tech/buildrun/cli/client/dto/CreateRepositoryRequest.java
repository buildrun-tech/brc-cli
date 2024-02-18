package tech.buildrun.cli.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateRepositoryRequest(String name, String description, @JsonProperty("private") boolean isPrivate) {
}
