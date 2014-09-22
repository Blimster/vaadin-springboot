/*
 * Vaadin Spring Boot
 * (c) 2014 by Oliver Damm
 */
package net.blimster.vaadinspringboot;

import javax.servlet.Servlet;

import net.blimster.vaadinspringboot.base.spring.SpringVaadinServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.RegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Oliver Damm
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class VaadinSpringBootApplication extends SpringBootServletInitializer
{

    @Bean
    public RegistrationBean vaadinSpringBootServlet(final ApplicationContext context)
    {
        final Servlet servlet = new SpringVaadinServlet(context);
        return new ServletRegistrationBean(servlet, "/*");
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application)
    {
        return application.sources(VaadinSpringBootApplication.class);
    }

    public static void main(final String... args)
    {
        SpringApplication.run(VaadinSpringBootApplication.class, args);
    }

}
