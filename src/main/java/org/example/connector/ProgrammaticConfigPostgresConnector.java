package org.example.connector;

import lombok.Getter;

import javax.sql.DataSource;

@Getter
public class ProgrammaticConfigPostgresConnector {
    private final String login;
    private final String password;
    private final DataSource dataSource;

    public ProgrammaticConfigPostgresConnector(String login, String password, DataSource dataSource) {
        this.login = login;
        this.password = password;
        this.dataSource = dataSource;
    }
}
