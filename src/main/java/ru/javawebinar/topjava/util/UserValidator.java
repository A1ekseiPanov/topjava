package ru.javawebinar.topjava.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.to.UserTo;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserTo.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserTo userTo = (UserTo) target;
        if (repository.getByEmail(userTo.getEmail()) != null) {
            errors.rejectValue("email", "", "User with this email already exists");
        }
    }
}
