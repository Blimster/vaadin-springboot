/*
 * Vaadin Spring Boot
 * (c) 2014 by Oliver Damm
 */
package net.blimster.vaadinspringboot.ui.root;

import javax.inject.Inject;

import net.blimster.vaadinspringboot.base.mvp.Presenter;
import net.blimster.vaadinspringboot.base.mvp.View;
import net.blimster.vaadinspringboot.base.spring.SpringPresenter;

/**
 * @author Oliver Damm
 */
@SpringPresenter
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
