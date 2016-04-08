/*
 * Vaadin Spring Boot
 * (c) 2014 by Oliver Damm
 */
package net.blimster.vaadinspringboot;

import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.server.SpringVaadinServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.RegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Oliver Damm
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableVaadin
public class VaadinSpringBootApplication extends SpringBootServletInitializer
{

    @Bean
    public RegistrationBean vaadinSpringBootServlet()
    {
        final SpringVaadinServlet servlet = new SpringVaadinServlet();
        return new ServletRegistrationBean(servlet, "/*");
    }

    public static void main(final String... args)
    {
        SpringApplication.run(VaadinSpringBootApplication.class, args);
    }

}
