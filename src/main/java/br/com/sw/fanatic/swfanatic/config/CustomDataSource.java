package br.com.sw.fanatic.swfanatic.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomDataSource {

    @Bean
    @ConditionalOnProperty("app.datasource")
    public javax.sql.DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }
}
