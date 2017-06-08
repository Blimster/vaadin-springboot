/*
 * Vaadin Spring Boot
 * (c) 2014 by Oliver Damm
 */
package net.blimster.vaadinspringboot;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import net.blimster.vaadinspringboot.ui.greet.GreetPresenter;
import net.blimster.vaadinspringboot.ui.root.RootPresenter;

import javax.inject.Inject;

/**
 * @author Oliver Damm
 */
@SpringUI(path = "")
@Theme("custom_valo")
public class VaadinSpringBootUi extends UI
{

    private static final long serialVersionUID = 1L;

    @Inject
    private RootPresenter rootPresenter;

    @Inject
    private GreetPresenter greetPresenter;

    @Override
    protected void init(final VaadinRequest request)
    {
        rootPresenter.setContent(greetPresenter);

        setContent(this.rootPresenter.getView()
                .getComponent(Component.class));
    }

}
