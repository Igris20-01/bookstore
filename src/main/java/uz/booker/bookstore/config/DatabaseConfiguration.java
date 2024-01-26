package uz.booker.bookstore.config;

import jakarta.activation.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories({"uz.booker.bookstore.repository"})
@EnableJpaAuditing
@EnableTransactionManagement
public class DatabaseConfiguration {

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource(){
        return (DataSource) DataSourceBuilder.create().build();
    }


}
