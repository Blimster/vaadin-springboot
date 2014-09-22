/*
 * Vaadin Spring Boot
 * (c) 2014 by Oliver Damm
 */
package net.blimster.vaadinspringboot.base.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UICreateEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;

/**
 * @author Oliver Damm
 */
public class SpringVaadinUiProvider extends UIProvider
{

    private static final long serialVersionUID = 1L;

    private final ApplicationContext context;
    private final Map<String, Class<? extends UI>> uis;

    public SpringVaadinUiProvider(final ApplicationContext context)
    {
        this.context = context;
        this.uis = new HashMap<String, Class<? extends UI>>();
    }

    @Override
    public UI createInstance(final UICreateEvent event)
    {

        final UI bean = this.context.getBean(event.getUIClass());
        return bean;
    }

    @Override
    public Class<? extends UI> getUIClass(final UIClassSelectionEvent event)
    {

        final String requestedPath = event.getRequest()
                .getPathInfo();

        searchForSpringAnnotatedUIs(requestedPath);

        final Class<? extends UI> result = this.uis.get(requestedPath);
        if (result != null)
        {
            return result;
        }

        if (this.uis.size() == 1)
        {
            final Class<? extends UI> uiType = this.uis.values()
                    .iterator()
                    .next();
            return uiType;
        }
        if (this.uis.size() > 2)
        {
            throw new IllegalStateException("No unambiguous UI found annotated with " + SpringUI.class.getSimpleName() + " for path '" + requestedPath
                    + "'! Found UIs: " + this.uis.keySet());
        }

        throw new IllegalStateException("No UI found annotated with " + SpringUI.class.getSimpleName() + " for path '" + requestedPath + "'!");
    }

    private void searchForSpringAnnotatedUIs(final String requestedPath)
    {
        if (this.uis.isEmpty())
        {
            final String[] beanNamesForUi = this.context.getBeanNamesForType(SpringVaadinUI.class);
            for (final String beanNameForUi : beanNamesForUi)
            {
                @SuppressWarnings("unchecked")
                final Class<? extends UI> uiType = (Class<? extends UI>) this.context.getType(beanNameForUi);
                if (uiType.isAnnotationPresent(SpringUI.class))
                {
                    final SpringUI springUI = uiType.getAnnotation(SpringUI.class);
                    final String path = springUI.value() != null && springUI.value()
                            .trim()
                            .isEmpty() == false ? springUI.value() : "/";
                    if (this.uis.containsKey(path))
                    {
                        throw new IllegalStateException("Multiple UIs for path '" + path + " 'found: " + uiType + ", " + this.uis.get(path));
                    }
                    this.uis.put(path, uiType);
                }
            }
        }
    }

}
