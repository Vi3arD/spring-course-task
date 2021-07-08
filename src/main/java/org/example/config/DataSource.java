package org.example.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.stereotype.Component;

@Component
public class DataSource {
    public DataSource() {
        new PGSimpleDataSource();
    }
}
