import org.example.connector.ProgrammaticConfigPostgresConnector;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProgrammaticConfigTest {
    @Test
    void shouldCreate() {
        final GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean(PropertySourcesPlaceholderConfigurer.class, () -> {
            final PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
            configurer.setLocations(new ClassPathResource("db.properties"));
            return configurer;
        });

        RuntimeBeanReference ref = new RuntimeBeanReference(PGSimpleDataSource.class);

        context.registerBean("dataSource", PGSimpleDataSource.class);
        context.registerBean(ProgrammaticConfigPostgresConnector.class,
                "${login}", "${password}", ref
        );

        context.refresh();

        final ProgrammaticConfigPostgresConnector bean = context.getBean(ProgrammaticConfigPostgresConnector.class);
        assertNotNull(bean.getDataSource());
        assertEquals("postgres", bean.getLogin());
        assertEquals("changeme", bean.getPassword());
    }
}
