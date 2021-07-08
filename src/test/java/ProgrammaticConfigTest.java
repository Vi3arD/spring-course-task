import org.example.config.DataSource;
import org.example.connector.ProgrammaticConfigPostgresConnector;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
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

        GenericBeanDefinition beanOtherDef = new GenericBeanDefinition();
        beanOtherDef.setBeanClass(PGSimpleDataSource.class);
        context.registerBeanDefinition("dataSource", beanOtherDef);

        GenericBeanDefinition gbd = new GenericBeanDefinition();
        gbd.setBeanClass(ProgrammaticConfigPostgresConnector.class);
        ConstructorArgumentValues cav = new ConstructorArgumentValues();
        cav.addIndexedArgumentValue(0, "${login}");
        cav.addIndexedArgumentValue(1, "${password}");
        cav.addIndexedArgumentValue(2, beanOtherDef);
        gbd.setConstructorArgumentValues(cav);
        context.registerBeanDefinition("programmaticConfigPostgresConnector", gbd);

        context.refresh();

        final ProgrammaticConfigPostgresConnector bean = context.getBean(ProgrammaticConfigPostgresConnector.class);
        assertNotNull(bean.getDataSource());
        assertEquals("postgres", bean.getLogin());
        assertEquals("changeme", bean.getPassword());
    }
}
