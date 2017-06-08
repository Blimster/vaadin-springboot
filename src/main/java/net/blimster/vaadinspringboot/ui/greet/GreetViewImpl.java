package net.blimster.vaadinspringboot.ui.greet;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Named;

/**
 * @author Oliver Damm
 */
@Scope("prototype")
@Named
public class GreetViewImpl implements GreetView
{

    private VerticalLayout layoutRoot;
    private Label label;
    private GreetView.Observer observer;

    @PostConstruct
    public void init()
    {
        label = new Label();

        TextField textField = new TextField();

        Button button = new Button("Ok");
        button.addClickListener((e) -> observer.textEntered(textField.getValue()));

        HorizontalLayout horizontalLayout = new HorizontalLayout(textField, button);
        horizontalLayout.setSpacing(true);

        this.layoutRoot = new VerticalLayout();
        this.layoutRoot.setMargin(true);
        this.layoutRoot.setSpacing(true);
        this.layoutRoot.addComponent(horizontalLayout);
        this.layoutRoot.addComponent(label);
    }

    @Override
    public <C> C getComponent(final Class<C> type)
    {
        return type.cast(this.layoutRoot);
    }

    @Override
    public void setGreeting(String greeting)
    {
        label.setValue(greeting);
    }

    @Override
    public void setObserver(GreetView.Observer observer)
    {
        this.observer = observer;
    }

}
