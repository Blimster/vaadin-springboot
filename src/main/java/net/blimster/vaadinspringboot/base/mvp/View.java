/*
 * Vaadin Spring Boot
 * (c) 2014 by Oliver Damm
 */
package net.blimster.vaadinspringboot.base.mvp;

/**
 * @author Oliver Damm
 */
public interface View
{

    public <C> C getComponent(Class<C> type);

}
