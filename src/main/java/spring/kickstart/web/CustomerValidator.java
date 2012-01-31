package spring.kickstart.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.beans.factory.annotation.Required;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import spring.kickstart.domain.Customer;

/**
 * @author mraible
 */
@Component
public class CustomerValidator implements Validator {
    private final Log log = LogFactory.getLog(CustomerValidator.class);
    @Autowired
    private MessageSource messageSource;

    public boolean supports(Class clazz) {
        return clazz.equals(Customer.class);
    }

    public void validate(Object obj, Errors errors) {
        log.debug("entering 'validate' method...");

        String arg1 = messageSource.getMessage("customer.name", null, LocaleContextHolder.getLocale());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
            "name", "errors.required", new Object[] {arg1}, "Value required.");
    }
}
