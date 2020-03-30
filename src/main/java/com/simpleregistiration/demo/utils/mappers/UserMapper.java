package com.simpleregistiration.demo.utils.mappers;


import com.simpleregistiration.demo.dtos.WritableRegister;
import com.simpleregistiration.demo.models.User;

public final class UserMapper {

    public static User writableRegisterToUser(WritableRegister writableRegister) {
        if (writableRegister == null) {
            return null;
        } else {
            User user = new User();
            user.setPassword(writableRegister.getPassword());
            user.setEmail(writableRegister.getEmail());
            user.setName(writableRegister.getName());
            user.setSurname(writableRegister.getSurname());
            return user;
        }
    }

}