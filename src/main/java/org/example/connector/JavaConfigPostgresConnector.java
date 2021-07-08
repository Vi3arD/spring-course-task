package org.example.connector;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;

@Getter
@RequiredArgsConstructor
public class JavaConfigPostgresConnector {

    private final String login;
    private final String password;
    private final DataSource dataSource;

}
