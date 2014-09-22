/*
 * Vaadin Spring Boot
 * (c) 2014 by Oliver Damm
 */
package net.blimster.vaadinspringboot.base.spring;

import org.springframework.context.ApplicationContext;

import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;

/**
 * @author Oliver Damm
 */
public class SpringVaadinServlet extends VaadinServlet
{

    private static final long serialVersionUID = 1L;

    private final ApplicationContext context;

    public SpringVaadinServlet(final ApplicationContext context)
    {
        this.context = context;
    }

    @Override
    protected VaadinServletService createServletService(final DeploymentConfiguration deploymentConfiguration) throws ServiceException
    {

        final VaadinServletService service = super.createServletService(deploymentConfiguration);

        service.addSessionInitListener(new SessionInitListener()
        {

            private static final long serialVersionUID = 1L;

            @Override
            public void sessionInit(final SessionInitEvent event) throws ServiceException
            {
                event.getSession()
                        .addUIProvider(new SpringVaadinUiProvider(SpringVaadinServlet.this.context));
            }

        });

        return service;
    }

}
