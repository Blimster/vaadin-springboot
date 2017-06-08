package net.blimster.vaadinspringboot.ui.greet;

import net.blimster.vaadinspringboot.base.mvp.ObservableView;

/**
 * @author Oliver Damm
 */
public interface GreetView extends ObservableView<GreetView.Observer>
{

    public void setGreeting(String greeting);

    public static interface Observer
    {

        void textEntered(String text);

    }

}
