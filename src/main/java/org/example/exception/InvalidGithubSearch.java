package org.example.exception;

import java.io.IOException;

public class InvalidGithubSearch extends IOException {
    public InvalidGithubSearch(String message) {
        super(message);
    }
}
