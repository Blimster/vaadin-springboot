/*
 * Vaadin Spring Boot
 * (c) 2014 by Oliver Damm
 */
package net.blimster.vaadinspringboot.ui.root;

import net.blimster.vaadinspringboot.base.mvp.View;

/**
 * @author Oliver Damm
 */
public interface RootView extends View
{

    public void setContent(View content);

}
