import org.example.config.JavaConfigPostgresConnectorConfiguration;
import org.example.connector.JavaConfigPostgresConnector;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JavaConfigTest {
  @Test
  void shouldCreate() {
    final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfigPostgresConnectorConfiguration.class);
    final JavaConfigPostgresConnector bean = context.getBean(JavaConfigPostgresConnector.class);
    assertNotNull(bean.getDataSource());
    assertEquals("postgres", bean.getLogin());
    assertEquals("changeme", bean.getPassword());
  }
}
