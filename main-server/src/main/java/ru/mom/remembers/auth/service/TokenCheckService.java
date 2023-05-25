package ru.mom.remembers.auth.service;

public interface TokenCheckService {
    boolean checkToken(String token);

    String getSubject(String token);
}
