/*
 * Vaadin Spring Boot
 * (c) 2014 by Oliver Damm
 */
package net.blimster.vaadinspringboot;

import javax.inject.Inject;

import net.blimster.vaadinspringboot.base.spring.SpringUI;
import net.blimster.vaadinspringboot.base.spring.SpringVaadinUI;
import net.blimster.vaadinspringboot.ui.root.RootPresenter;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;

/**
 * @author Oliver Damm
 */
@SpringUI
@Theme("custom_valo")
public class VaadinSpringBootUi extends SpringVaadinUI
{

    private static final long serialVersionUID = 1L;

    @Inject
    private RootPresenter rootPresenter;

    @Override
    protected void init(final VaadinRequest request)
    {
        setContent(this.rootPresenter.getView()
                .getComponent(Component.class));
    }

}
