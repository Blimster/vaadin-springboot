package net.blimster.vaadinspringboot.ui.greet;

import com.vaadin.spring.annotation.UIScope;
import net.blimster.vaadinspringboot.base.mvp.View;
import net.blimster.vaadinspringboot.service.salutation.SalutationService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Oliver Damm
 */
@UIScope
@Named
public class GreetPresenterImpl implements GreetPresenter, GreetView.Observer
{

    @Inject
    private GreetView view;

    @Inject
    private SalutationService salutationService;

    @PostConstruct
    public void init()
    {
        view.setObserver(this);
    }

    @Override
    public View getView()
    {
        return this.view;
    }

    @Override
    public void textEntered(String text)
    {
        view.setGreeting(salutationService.getSalutation(text));
    }

}
