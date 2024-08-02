package com.devsuperior.dscatalog.services.validations.update;

import com.devsuperior.dscatalog.dto.error.FieldError;
import com.devsuperior.dscatalog.dto.User.UserUpdateDto;
import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDto> {

    @Autowired
    private HttpServletRequest request;


    @Autowired
    private UserRepository userRepository;


    @Override
    public void initialize(UserUpdateValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserUpdateDto userUpdateDto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long userId = Long.parseLong(uriVars.get("id"));

        List<FieldError> fieldErrors = new ArrayList<>();


        User user = userRepository.findByEmail(userUpdateDto.getEmail());
        if (user != null && user.getId() != userId) {
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


