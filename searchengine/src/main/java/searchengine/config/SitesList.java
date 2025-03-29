package searchengine.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter

@Configuration
@ConfigurationProperties(prefix = "indexing-settings")
public class SitesList {
    @Autowired
    private List<Site> sites;

}
