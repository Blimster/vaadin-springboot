/*
 * Vaadin Spring Boot
 * (c) 2014 by Oliver Damm
 */
package net.blimster.vaadinspringboot.ui.root;

import net.blimster.vaadinspringboot.base.mvp.View;
import net.blimster.vaadinspringboot.base.spring.SpringView;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Oliver Damm
 */
@SpringView
public class RootViewImpl implements RootView
{

    private final VerticalLayout layoutRoot;

    public RootViewImpl()
    {
        this.layoutRoot = new VerticalLayout();
        this.layoutRoot.setMargin(true);
        this.layoutRoot.addComponent(new Label("Hello, Vaadin + Spring Boot!"));
    }

    @Override
    public <C> C getComponent(final Class<C> type)
    {
        return type.cast(this.layoutRoot);
    }

    @Override
    public void setContent(final View content)
    {
        final Component component = content.getComponent(Component.class);

        this.layoutRoot.removeAllComponents();
        this.layoutRoot.addComponent(component);
    }
}
