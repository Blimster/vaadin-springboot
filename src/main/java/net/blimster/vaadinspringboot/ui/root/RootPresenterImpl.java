/*
 * Vaadin Spring Boot
 * (c) 2014 by Oliver Damm
 */
package net.blimster.vaadinspringboot.ui.root;

import com.vaadin.spring.annotation.UIScope;
import net.blimster.vaadinspringboot.base.mvp.Presenter;
import net.blimster.vaadinspringboot.base.mvp.View;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Oliver Damm
 */
@UIScope
@Named
public class RootPresenterImpl implements RootPresenter
{

    @Inject
    private RootView view;

    @Override
    public View getView()
    {
        return this.view;
    }

    @Override
    public void setContent(final Presenter<?> presenter)
    {
        this.view.setContent(presenter.getView());
    }

}
