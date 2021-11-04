package com.devteam.digital.config;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.devteam.digital.converter.AccountConverter;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.Location;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zaxxer.hikari.HikariConfig;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.DefaultObjectWrapper;


@Configuration
@EnableWebSecurity
@ComponentScan("com.devteam")
@PropertySources({
        @PropertySource("classpath:application.yaml"),
        @PropertySource(value = "file:./application_override.yaml", ignoreResourceNotFound = true)
})
public class BeanConfigurations {

    private static final Logger log = LoggerFactory.getLogger(BeanConfigurations.class);


    @Bean
    @Primary
    public ObjectMapper serializingObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new JsonDateTimeDeserializer());
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        javaTimeModule.addDeserializer(LocalDate.class, new JsonDateDeserializer());

        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

    @Bean
    public org.springframework.web.filter.CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Qualifier("fmConfig")
    public freemarker.template.Configuration getFreeMarkerConfiguration() {
        freemarker.template.Configuration config = new freemarker.template.Configuration(freemarker.template.Configuration.getVersion());


        File customTemplate = new File("./templates/email");
        FileTemplateLoader ftl = null;
        if (customTemplate.exists()) {
            try {
                ftl = new FileTemplateLoader(customTemplate);
            }	catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        ClassTemplateLoader ctl = new ClassTemplateLoader(getClass(), "/templates/email");

        TemplateLoader[] loaders = null;
        if (ftl != null) {
            loaders = new TemplateLoader[]{ftl, ctl};
        } else {
            loaders = new TemplateLoader[]{ctl};
        }

        MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
        config.setTemplateLoader(mtl);
        config.setObjectWrapper(new DefaultObjectWrapper());
        config.setDefaultEncoding("UTF-8");
        config.setLocalizedLookup(false);
        config.setTemplateUpdateDelayMilliseconds(6000);
        return config;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

    @Value("${db.driver}")
    private String DB_DRIVER;

    @Value("${db.password}")
    private String DB_PASSWORD;

    @Value("${db.url}")
    private String DB_URL;

    @Value("${db.username}")
    private String DB_USERNAME;


    @Bean
    public DataSource datasource() {

        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(20);
        config.setConnectionTimeout(300000);
        config.setConnectionTimeout(120000);
        config.setLeakDetectionThreshold(300000);

        return DataSourceBuilder.create()
                .driverClassName(DB_DRIVER)
                .url(DB_URL)
                .username(DB_USERNAME)
                .password(DB_PASSWORD)
                .build();
    }

    @Bean(initMethod = "migrate")
    @PostConstruct
    Flyway flyway() {

        String location = null;
        if (DB_DRIVER.contains("h2") || DB_DRIVER.contains("mariadb")) {
            location = "classpath:db/migration/h2";
        } else if (DB_DRIVER.contains("postgres")) {
            location = "classpath:db/migration/postgres";
        } else {
            throw new RuntimeException("Unsupported database driver found in configuration - " + DB_DRIVER);
        }
        System.out.println("set flyway location to : " + location);

        Flyway flyway = Flyway.configure().dataSource(datasource()).locations(new Location(location)).baselineOnMigrate(true).validateOnMigrate(true).load();
        flyway.repair();
        flyway.migrate();
        return flyway;
    }


    @Bean
    public AccountConverter getUserConverter() {
        return new AccountConverter();
    }

}
