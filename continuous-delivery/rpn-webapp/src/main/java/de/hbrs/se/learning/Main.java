package de.hbrs.se.learning;

import de.hbrs.se.learning.service.RpnExecutor;
import de.hbrs.se.learning.service.RpnParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class Main {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public RpnExecutor rpnExecutor() {
        return new RpnExecutor(new RpnParser());
    }
}
