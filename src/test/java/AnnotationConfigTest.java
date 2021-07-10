import org.example.annotationway.AnnotationConfigPostgresConnector;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AnnotationConfigTest {
    @Test
    void shouldCreate() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example.annotationway");
        final AnnotationConfigPostgresConnector bean = context.getBean(AnnotationConfigPostgresConnector.class);
        assertNotNull(bean.getDataSource());
        assertEquals("postgres", bean.getLogin());
        assertEquals("changeme", bean.getPassword());
    }
}
