package org.example.exception;

import java.io.IOException;

public class InvalidGithubSearch extends RuntimeException {
    public InvalidGithubSearch(String message) {
        super(message);
    }
}
