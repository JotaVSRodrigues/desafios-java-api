package org.example.auth;

import org.example.exception.ErroConsultaGitHubException;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Auth {
    private String endereco;

    public Auth() {
    }

    public String consultarUsuarios(String username) throws IOException, InterruptedException {
        // formata o nome de usuário no padrão UTF-8 de URL
        String encoded = URLEncoder.encode(username, StandardCharsets.UTF_8);
        String url = "https://api.github.com/users/" + encoded;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/vnd.github+json")
                .header("User-Agent", "JavaHttpClient")
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        int code = response.statusCode();

        if (code == 404){
            throw new ErroConsultaGitHubException("Usuário '" + username + "' não encontrado (HTTP 404).");
        }

        if (code >= 400){
            throw new RuntimeException("Falha ao consultar Github: HTTP" + code + " - " + response.body());
        }
        return response.body();
    }

    public String safe(String s) {
        return (s == null || s.isBlank()) ? "(sem informação)" : s;
    }

    public String safeInt(Integer i) {
        return (i == null) ? "(sem informação)" : i.toString();
    }
}
