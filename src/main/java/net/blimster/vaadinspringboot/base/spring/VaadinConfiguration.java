/*
 * Vaadin Spring Boot
 * (c) 2014 by Oliver Damm
 */
package net.blimster.vaadinspringboot.base.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Oliver Damm
 */
@Configuration
public class VaadinConfiguration
{

    @Bean
    public static VaadinUiScope uiScope()
    {
        return new VaadinUiScope();
    }

}
