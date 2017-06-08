/*
 * Vaadin Spring Boot
 * (c) 2014 by Oliver Damm
 */
package net.blimster.vaadinspringboot.ui.root;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import net.blimster.vaadinspringboot.base.mvp.View;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Named;

/**
 * @author Oliver Damm
 */
@Scope("prototype")
@Named
public class RootViewImpl implements RootView
{

    private VerticalLayout layoutRoot;

    @PostConstruct
    public void init()
    {
        this.layoutRoot = new VerticalLayout();
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
