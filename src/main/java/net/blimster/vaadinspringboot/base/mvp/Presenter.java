/*
 * Vaadin Spring Boot
 * (c) 2014 by Oliver Damm
 */
package net.blimster.vaadinspringboot.base.mvp;

/**
 * @author Oliver Damm
 */
public interface Presenter<V extends View>
{

    public View getView();

}
