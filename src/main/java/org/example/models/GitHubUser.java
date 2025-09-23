package org.example.models;

public record GitHubUser(
        String login,
        String name,
        String bio,
        String html_url,
        String location,
        String created_at,
        Integer public_repos,
        Integer followers,
        Integer following
) {
}
