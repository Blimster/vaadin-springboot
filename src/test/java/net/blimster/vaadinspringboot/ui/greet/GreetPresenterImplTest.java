package net.blimster.vaadinspringboot.ui.greet;

import net.blimster.vaadinspringboot.service.salutation.SalutationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Oliver Damm
 */
public class GreetPresenterImplTest
{

    @Mock
    private GreetView view;

    @Mock
    private SalutationService salutationService;

    @InjectMocks
    private GreetPresenterImpl greetPresenter;

    @Before
    public void before()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTextEntered()
    {
        Mockito.when(salutationService.getSalutation("Test")).thenReturn("Hello, Test!");
        greetPresenter.textEntered("Test");
        Mockito.verify(view).setGreeting("Hello, Test!");
    }

}
