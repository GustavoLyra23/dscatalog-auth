package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.services.validations.UserInsertValid;

@UserInsertValid
public class UserInsertDto extends UserDto {

    private String password;

    public UserInsertDto() {
    }

    public UserInsertDto(User user) {
        super(user);
        password = user.getPassword();
    }

    public String getPassword() {
        return password;
    }
}