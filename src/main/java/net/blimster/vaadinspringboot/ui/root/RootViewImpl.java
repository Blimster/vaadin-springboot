/*
 * Vaadin Spring Boot
 * (c) 2014 by Oliver Damm
 */
package net.blimster.vaadinspringboot.ui.root;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import net.blimster.vaadinspringboot.base.mvp.View;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

/**
 * @author Oliver Damm
 */
@Scope("prototype")
@Named
public class RootViewImpl implements RootView
{

    private final VerticalLayout layoutRoot;

    public RootViewImpl()
    {
        final TextField textFieldFocusBug = new TextField("Focus Bug");

        final HorizontalLayout layoutWithoutMargin = new HorizontalLayout();
        layoutWithoutMargin.addComponent(textFieldFocusBug);

        this.layoutRoot = new VerticalLayout();
        this.layoutRoot.addComponent(new Label("Hello, Vaadin + Spring Boot!"));
        this.layoutRoot.addComponent(layoutWithoutMargin);
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
