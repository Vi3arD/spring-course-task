package org.example.annotationway;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//import javax.sql.DataSource;

@Component
@Getter
public class AnnotationConfigPostgresConnector {

    private final String login;
    private final String password;
    private final DataSource dataSource;

    public AnnotationConfigPostgresConnector(@Value("${login}") String login, @Value("${password}") String password, DataSource dataSource) {
        this.login = login;
        this.password = password;
        this.dataSource = dataSource;
    }

}
