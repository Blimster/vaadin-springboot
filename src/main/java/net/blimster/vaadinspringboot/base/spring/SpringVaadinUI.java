/*
 * Vaadin Spring Boot
 * (c) 2014 by Oliver Damm
 */
package net.blimster.vaadinspringboot.base.spring;

import javax.annotation.PostConstruct;

import com.vaadin.ui.UI;

/**
 * @author Oliver Damm
 */
public abstract class SpringVaadinUI extends UI
{

    private static final long serialVersionUID = 1L;

    private static ThreadLocal<UI> uiCurrentlyInCreation = new ThreadLocal<UI>();

    protected SpringVaadinUI()
    {
        final UI currentUI = SpringVaadinUI.uiCurrentlyInCreation.get();
        if (currentUI != null)
        {
            throw new IllegalStateException("there is already a UI (" + currentUI + ") set for the current thread " + Thread.currentThread()
                    .getName() + "!");
        }
        SpringVaadinUI.uiCurrentlyInCreation.set(this);
    }

    @PostConstruct
    public void postConstruct()
    {
        SpringVaadinUI.uiCurrentlyInCreation.remove();
    }

    public static UI getUiCurrentInCreation()
    {
        return SpringVaadinUI.uiCurrentlyInCreation.get();
    }

}
