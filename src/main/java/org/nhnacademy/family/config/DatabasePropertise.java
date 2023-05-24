package org.nhnacademy.family.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:/db.properties")
@Getter
public class DatabasePropertise {

    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.driverClassName}")
    private String driverClassName;
    @Value("${db.url}")
    private String url;
    @Value("${db.initialSize}")
    private Integer initialSize;
    @Value("${db.maxTotal}")
    private Integer maxTotal;
    @Value("${db.maxIdle}")
    private Integer maxIdle;
    @Value("${db.minIdle}")
    private Integer minIdle;
    @Value("${db.maxWaitMillis}")
    private Integer maxWaitMillis;
    @Value("${db.validationQuery}")
    private String validationQuery;
    @Value("${db.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${db.testOnReturn}")
    private boolean testOnReturn;
    @Value("${db.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${db.platform}")
    private String platform;
}
