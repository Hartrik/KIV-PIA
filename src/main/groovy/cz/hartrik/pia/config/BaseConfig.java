package cz.hartrik.pia.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Basic configuration.
 *
 * @author Patrik Harag
 * @version 2017-04-14
 */
@Configuration
@ComponentScan(basePackages = {
        "cz.hartrik.pia.config",
        "cz.hartrik.pia.controller",
        "cz.hartrik.pia.service",
})
public class BaseConfig {

}
