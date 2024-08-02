package com.devsuperior.dscatalog.dto.User;

import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.services.validations.update.UserUpdateValid;

@UserUpdateValid
public class UserUpdateDto extends UserDto {

    public UserUpdateDto() {
    }

    public UserUpdateDto(User user) {
        super(user);
    }

}