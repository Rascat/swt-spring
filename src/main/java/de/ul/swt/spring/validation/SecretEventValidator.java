package de.ul.swt.spring.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.google.common.base.Strings;

import de.ul.swt.spring.domain.SecretEvent;

public class SecretEventValidator implements Validator {

    @Override
    public boolean supports(final Class<?> clazz) {
        return SecretEvent.class.equals(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        SecretEvent event = (SecretEvent) target;

        final String phrase = event.getPhrase();
        if (!Strings.isNullOrEmpty(phrase)) {
            if (!phrase.toLowerCase().contains("x")) {
                errors.rejectValue("phrase", "SWT0001", "Phrase must contain an 'x'");
            }
        }
    }

}
