package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.auth.Auth;
import org.example.exception.ErroConsultaGitHubException;
import org.example.models.GitHubUser;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Gson GSON = new GsonBuilder()
            .serializeNulls() // mantém nulos visíveis se quiser exibir "(sem informação)"
            .create();

    public static void main(String[] args) {
        // consulta do nome de usuário pela linha de comando ou utiliza direto o "torvalds"
        String username = (args.length > 0) ? args[0] : "torvalds";

        try {
            Auth auth = new Auth();
            Gson GSON = new Gson();

            String json = auth.consultarUsuarios(username);
            GitHubUser user = GSON.fromJson(json, GitHubUser.class);

            System.out.println("=== Perfil do GitHub ===");
            System.out.println("Login:          " + auth.safe(user.login()));
            System.out.println("Nome:           " + auth.safe(user.name()));
            System.out.println("Bio:            " + auth.safe(user.bio()));
            System.out.println("Localização:    " + auth.safe(user.location()));
            System.out.println("Criado em:      " + auth.safe(user.created_at()));
            System.out.println("Repositórios:   " + auth.safeInt(user.public_repos()));
            System.out.println("Seguidores:     " + auth.safeInt(user.followers()));
            System.out.println("Seguindo:       " + auth.safeInt(user.following()));
            System.out.println("URL do perfil:  " + auth.safe(user.html_url()));

        } catch (ErroConsultaGitHubException e) {
            System.out.println("Ops! Não achei esse usuário no GitHub. Verifique o nome e tente novamente.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}