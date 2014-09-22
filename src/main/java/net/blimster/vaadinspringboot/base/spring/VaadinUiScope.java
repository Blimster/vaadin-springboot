/*
 * Vaadin Spring Boot
 * (c) 2014 by Oliver Damm
 */
package net.blimster.vaadinspringboot.base.spring;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.Scope;

import com.vaadin.server.ClientConnector.DetachEvent;
import com.vaadin.server.ClientConnector.DetachListener;
import com.vaadin.ui.UI;

/**
 * @author Oliver Damm
 */
public class VaadinUiScope implements Scope, DetachListener, BeanFactoryPostProcessor
{

    private static final long serialVersionUID = 1L;

    public static final String NAME = "ui";

    private final Map<UI, Map<String, Object>> objectMap = Collections.synchronizedMap(new HashMap<UI, Map<String, Object>>());
    private final Map<UI, Map<String, Runnable>> destructionCallbackMap = Collections.synchronizedMap(new HashMap<UI, Map<String, Runnable>>());

    @Override
    public Object get(final String name, final ObjectFactory<?> objectFactory)
    {

        final UI inCurrentCreationUi = SpringVaadinUI.getUiCurrentInCreation();
        final UI ui = inCurrentCreationUi != null ? inCurrentCreationUi : UI.getCurrent();

        Map<String, Object> uiSpace = this.objectMap.get(ui);

        if (uiSpace == null)
        {
            ui.addDetachListener(this);
            uiSpace = Collections.synchronizedMap(new LinkedHashMap<String, Object>());
            this.objectMap.put(ui, uiSpace);
        }

        Object bean = uiSpace.get(name);
        if (bean == null)
        {
            bean = objectFactory.getObject();
            uiSpace.put(name, bean);
        }
        return bean;
    }

    @Override
    public Object remove(final String name)
    {
        final UI ui = UI.getCurrent();
        final Map<String, Runnable> destructionSpace = this.destructionCallbackMap.get(ui);
        if (destructionSpace != null)
        {
            destructionSpace.remove(name);
        }
        final Map<String, Object> uiSpace = this.objectMap.get(ui);
        if (uiSpace == null)
        {
            return null;
        }
        return uiSpace.remove(name);
    }

    @Override
    public void registerDestructionCallback(final String name, final Runnable callback)
    {
        final UI ui = UI.getCurrent();
        Map<String, Runnable> destructionSpace = this.destructionCallbackMap.get(ui);
        if (destructionSpace == null)
        {
            destructionSpace = Collections.synchronizedMap(new HashMap<String, Runnable>());
            this.destructionCallbackMap.put(ui, destructionSpace);
        }
        destructionSpace.put(name, callback);
    }

    @Override
    public Object resolveContextualObject(final String key)
    {
        return null;
    }

    @Override
    public String getConversationId()
    {
        final UI ui = UI.getCurrent();
        return ui == null ? null : ui.getId();
    }

    @Override
    public void detach(final DetachEvent event)
    {
        final UI ui = (UI) event.getSource();

        final Map<String, Runnable> destructionSpace = this.destructionCallbackMap.remove(ui);
        if (destructionSpace != null)
        {
            for (final Runnable runnable : destructionSpace.values())
            {
                runnable.run();
            }
        }

        this.objectMap.remove(ui);
    }

    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeansException
    {
        beanFactory.registerScope(VaadinUiScope.NAME, this);
    }

}