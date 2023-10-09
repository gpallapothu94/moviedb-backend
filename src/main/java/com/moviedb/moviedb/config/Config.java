package com.moviedb.moviedb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "moviedb")
@Data
public class Config {
    public String url;
    public String token;

    public String getBearerToken() {
        return "Bearer " + this.getToken();
    }
}
