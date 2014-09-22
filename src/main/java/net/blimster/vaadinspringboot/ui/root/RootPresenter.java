/*
 * Vaadin Spring Boot
 * (c) 2014 by Oliver Damm
 */
package net.blimster.vaadinspringboot.ui.root;

import net.blimster.vaadinspringboot.base.mvp.Presenter;

/**
 * @author Oliver Damm
 */
public interface RootPresenter extends Presenter<RootView>
{

    public void setContent(Presenter<?> presenter);

}
