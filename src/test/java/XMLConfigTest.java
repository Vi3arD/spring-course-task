import org.example.connector.XMLConfigPostgresConnector;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class XMLConfigTest {
    @Test
    void shouldCreate() {
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        final XMLConfigPostgresConnector bean = context.getBean(XMLConfigPostgresConnector.class);
        assertNotNull(bean.getDataSource());
        assertEquals("postgres", bean.getLogin());
        assertEquals("changeme", bean.getPassword());
    }
}
