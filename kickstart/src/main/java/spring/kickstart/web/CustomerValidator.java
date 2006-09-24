package spring.kickstart.web;

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
public class CustomerValidator implements Validator {
    private final Log log = LogFactory.getLog(CustomerValidator.class);
    private MessageSource messageSource;

    @Required
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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
