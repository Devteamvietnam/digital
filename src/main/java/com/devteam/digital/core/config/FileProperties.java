package com.devteam.digital.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileProperties {

    /**
     * File size limit
     */
    private Long maxSize;

    /**
     * Avatar size limit
     */
    private Long avatarMaxSize;

    private Path mac;

    private Path linux;

    private Path windows;

    public Path getPath() {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith(String.valueOf(Ostype.WINDOWS))) {
            return windows;
        } else if (os.toLowerCase().startsWith(String.valueOf(Ostype.MAC))) {
            return mac;
        }
        return linux;
    }

    @Getter
    @Setter
    public static class Path{

        private String path;

        private String avatar;
    }
}
