package net.blimster.vaadinspringboot.service.salutation;

import org.springframework.stereotype.Service;

/**
 * @author Oliver Damm
 */
@Service
public class SalutationServiceImpl implements SalutationService
{

    @Override
    public String getSalutation(String name)
    {
        return "Hello " + name + "!";
    }

}
