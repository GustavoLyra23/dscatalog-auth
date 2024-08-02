package com.devsuperior.dscatalog.services.validations.insert;

import com.devsuperior.dscatalog.dto.error.FieldError;
import com.devsuperior.dscatalog.dto.User.UserInsertDto;
import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDto> {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserInsertDto userInsertDto, ConstraintValidatorContext context) {
        List<FieldError> fieldErrors = new ArrayList<>();


        User user = userRepository.findByEmail(userInsertDto.getEmail());
        if (user != null) {
            fieldErrors.add(new FieldError("email", "email ja existe"));
        }

        fieldErrors.forEach(fieldError -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fieldError.getMessage()).addPropertyNode(fieldError.getField())
                    .addConstraintViolation();
        });
        return fieldErrors.isEmpty();
    }
}

